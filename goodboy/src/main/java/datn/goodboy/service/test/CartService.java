package datn.goodboy.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.CartDetailRepository;
import datn.goodboy.repository.CartRepository;
import datn.goodboy.repository.ProductDetailRepository;

@Service("testCartService")
public class CartService {
  @Autowired
  CartRepository cartRepository;
  @Autowired
  CartDetailRepository cartDetailRepository;
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  ProductDetailRepository productDetailRepository;
  @Autowired
  BillRepository billRepository;

  public Cart getCart() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUserName = authentication.getName();
      Account account = accountRepository.fillAcccoutbyEmail(currentUserName);
      Cart cart = null;
      if (account.getCustomer().getCart() == null) {
        cart = new Cart();
        cart.setCustomer(account.getCustomer());
        return cartRepository.save(cart);
      } else {
        return account.getCustomer().getCart();
      }
    } else {
      throw new RuntimeException("Vui lòng đăng nhập");
    }
  };

  public void deleteCartDetails(int idcartdetails) {
    cartDetailRepository.delete(cartDetailRepository.findById(idcartdetails).get());
  }

  public Cart addToCart(int idproduct, int quantity) {
    Cart cart = getCart();
    Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(idproduct);

    if (productDetailOptional.isPresent()) {
      ProductDetail productDetail = productDetailOptional.get();

      // Check if the Cart already contains a CartDetail with the same ProductDetail
      Optional<CartDetail> existingCartDetailOptional = cart.getCartDetails()
          .stream()
          .filter(cd -> cd.getProductDetail().equals(productDetail))
          .findFirst();

      if (existingCartDetailOptional.isPresent()) {
        // Cart already contains the same ProductDetail, update quantity
        CartDetail existingCartDetail = existingCartDetailOptional.get();
        existingCartDetail.setQuantity(existingCartDetail.getQuantity() + quantity);
        cartDetailRepository.save(existingCartDetail);
      } else {
        // Cart does not contain the ProductDetail, add a new CartDetail
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProductDetail(productDetail);
        cartDetail.setCart(cart);
        cartDetail.setQuantity(quantity);
        cartDetailRepository.save(cartDetail);
      }
    }
    return cart;
  }

  public CartDetail updateCart(int idcartdetails, int quantity) {
    Optional<CartDetail> cartDetailOptional = cartDetailRepository.findById(idcartdetails);
    if (cartDetailOptional.isPresent()) {
      CartDetail cartDetail = cartDetailOptional.get();
      cartDetail.setQuantity(quantity);
      if (cartDetail.getProductDetail().getQuantity() < quantity) {
        throw new RuntimeException("Sản phảm không chỉ còn: " + cartDetail.getProductDetail().getQuantity());
      }
      return cartDetailRepository.save(cartDetail);
    } else {
      throw new RuntimeException("Sản phẩm không tồn tại trong cart : " + idcartdetails);
    }
  }

  public Double calculateTotalPrice(List<Integer> idCartDetails) {
    return getCart().getCartDetails().stream()
        .filter(cartDetail -> idCartDetails.contains(cartDetail.getId()))
        .mapToDouble(CartDetail::getTotalMoney).sum();
  }

  public List<CartDetail> reorderBill(int idBill) {
    Cart cartExits = getCart();
    Optional<Bill> optionalBill = billRepository.findById(idBill);

    if (optionalBill.isPresent()) {
      Bill bill = optionalBill.get();
      List<CartDetail> results = new ArrayList<>();

      List<Integer> existingProductDetailIds = new ArrayList<>();
      for (CartDetail cartDetail : cartExits.getCartDetails()) {
        existingProductDetailIds.add(cartDetail.getProductDetail().getId());
      }

      for (BillDetail billDetail : bill.getBillDetail()) {
        int productId = billDetail.getProductDetail().getId();

        if (!existingProductDetailIds.contains(productId)) {
          CartDetail cartDetail = new CartDetail();
          cartDetail.setProductDetail(billDetail.getProductDetail());
          cartDetail.setQuantity(billDetail.getQuantity());
          cartDetail.setCart(cartExits);
          results.add(cartDetailRepository.save(cartDetail));
        } else {
          for (CartDetail existingCartDetail : cartExits.getCartDetails()) {
            if (existingCartDetail.getProductDetail().getId() == productId) {
              existingCartDetail.setQuantity(billDetail.getQuantity());
              results.add(cartDetailRepository.save(existingCartDetail));
              break;
            }
          }
        }
      }
      return results;
    }

    return null; // Return an empty list if the Bill is not present
  }

  public Bill getCheckOutPage(List<Integer> cartDetails) {
    return null;
  }

  public CartDetail getCartDetails(int id) {
    return cartDetailRepository.findById(id).get();
  }

}
