package datn.goodboy.service;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public Page<Cart> getPage(Pageable pageable){
        return cartRepository.findAll(pageable);
    }

    public ArrayList<Cart> getAllCart(){
        return (ArrayList<Cart>) cartRepository.findAll();
    }


    public Cart saveCart(Cart cart) {

        return cartRepository.save(cart);
    }

    public void deleteCart(int id) {

        cartRepository.deleteById(id);
    }

    public Optional<Cart> findByIdCart(int id) {

        return cartRepository.findById(id);
    }





}
