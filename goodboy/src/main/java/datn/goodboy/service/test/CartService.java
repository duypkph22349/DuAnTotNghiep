package datn.goodboy.service.test;

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
    }
    return null;
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
      return cartDetailRepository.save(cartDetail);
    } else {
      throw new RuntimeException("CartDetail not found with id: " + idcartdetails);
    }
  }

  public Bill getCheckOutPage(List<Integer> cartDetails) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUserName = authentication.getName();
      Account account = accountRepository.fillAcccoutbyEmail(currentUserName);
      Bill bill = new Bill();
      bill.setCustomer(account.getCustomer());
      bill.setCustomer_name(account.getCustomer().getName());
      bill.setPhone(account.getCustomer().getPhone());
      bill.setAddress(account.getCustomer().getAddress());
      bill.setStatus(0);
      bill.setLoaiDon(1);
      bill.setStatus_pay(0);
      List<BillDetail> billDetails = cartDetails.stream()
          .map(idcartdetails -> cartDetailRepository.findById(idcartdetails))
          .filter(Optional::isPresent)
          .map(Optional::get)
          .map(cartDetail -> {
            BillDetail billDetail = new BillDetail();
            billDetail.setProductDetail(cartDetail.getProductDetail());
            billDetail.setQuantity(cartDetail.getQuantity());
            billDetail
                .setTotalMoney(Double.valueOf(cartDetail.getProductDetail().getPrice() * cartDetail.getQuantity()));
            return billDetail;
          })
          .collect(Collectors.toList());
      bill.setBillDetail(billDetails);
      double totalMoney = bill.getBillDetail().stream().mapToDouble(BillDetail::getTotalMoney).sum();
      bill.setTotal_money(totalMoney);
      bill.setDeposit(0d);
      bill.setMoney_ship(0d);
      return bill;
    }
    return null;
  }
}
