package datn.goodboy.service;

import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartDetailRepository cartRepository;

    @Autowired
    public CartService(CartDetailRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public Page<CartDetail> getPage(Pageable pageable){
        return cartRepository.findAll(pageable);
    }

    public ArrayList<CartDetail> getAllCart(){
        return (ArrayList<CartDetail>) cartRepository.findAll();
    }


    public CartDetail saveCart(CartDetail cart) {

        return cartRepository.save(cart);
    }

    public void deleteCart(int id) {

        cartRepository.deleteById(id);
    }

    public Optional<CartDetail> findByIdCart(int id) {

        return cartRepository.findById(id);
    }





}
