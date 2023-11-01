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
public class CartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    public CartDetailService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }


    public Page<CartDetail> getPage(Pageable pageable){
        return cartDetailRepository.findAll(pageable);
    }

    public ArrayList<CartDetail> getAllCart(){
        return (ArrayList<CartDetail>) cartDetailRepository.findAll();
    }


    public CartDetail saveCart(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    public void deleteCart(int id) {
        cartDetailRepository.deleteById(id);
    }

    public Optional<CartDetail> findByIdCart(int id) {
        return cartDetailRepository.findById(id);
    }
}
