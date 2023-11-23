package datn.goodboy.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.DTO.CartDetailDto;
import datn.goodboy.model.DTO.CartDto;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.ProductDetailRepository;
import datn.goodboy.service.serviceinterface.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductDetailRepository repo;


    @Override
    public CartDto updateCart(CartDto cart, Integer productId, Integer quantity, boolean isReplace) {
        ProductDetail product =  repo.findById(productId).get();

        HashMap<Integer, CartDetailDto> listDetail = cart.getListDetail();

        // Add new product
        if(!listDetail.containsKey(productId)) {

            CartDetailDto detail = new CartDetailDto();
            detail.setProductId(product.getId());
//            detail.setCateId(product.getCategory().getId());
            detail.setName(product.getName());
//            detail.setSlug(product.getSlug());
            detail.setQuantity(quantity);
//            detail.setImage(product.getImageProducts().getId());
            detail.setPrice((double) (quantity * product.getPrice()));

            listDetail.put(productId, detail);
        }else if(quantity > 0) {
            if(isReplace) { // Replace quantity cart
                listDetail.get(productId).setQuantity(quantity);
                listDetail.get(productId).setPrice((double) (quantity*product.getPrice()));
            }else { // Add 1 quantity into productQuantity
                Integer oldQuantity = listDetail.get(productId).getQuantity();
                Integer newQuantity = oldQuantity + quantity;
                Double newPrice = (double) (newQuantity * product.getPrice());
                listDetail.get(productId).setQuantity(newQuantity);
                listDetail.get(productId).setPrice(newPrice);
            }
        }else {
            listDetail.remove(productId);
        }

        cart.setTotalQuantity(getTotalQuantity(cart));
        cart.setTotalPrice(getTotalPrice(cart));
        return cart;
    }

    @Override
    public Integer getTotalQuantity(CartDto cart) {
        Integer totalQuantity = 0;
        HashMap<Integer, CartDetailDto> listDetail = cart.getListDetail();
        for (CartDetailDto detail : listDetail.values()) {
            totalQuantity += detail.getQuantity();
        }
        return totalQuantity;
    }

    @Override
    public Double getTotalPrice(CartDto cart) {
        Double totalPrice = 0D;
        HashMap<Integer, CartDetailDto> listDetail = cart.getListDetail();
        for (CartDetailDto detail : listDetail.values()) {
            totalPrice += detail.getPrice();
        }
        return totalPrice;
    }

    @Override
    public CartDto save(CartDto cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    // @Override
    // public CartDto save(CartDto cart) {
    //    CartDto dto =  repo.save(cart);
    //     return cart;
    // }
}
