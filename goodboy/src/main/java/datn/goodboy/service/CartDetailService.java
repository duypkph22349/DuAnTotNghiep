package datn.goodboy.service;


import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;

    public Page<CartDetail> getPage(Pageable pageable){
        return cartDetailRepository.findAll(pageable);
    }

    public ArrayList<CartDetail> getAllCartDetail(){
        return (ArrayList<CartDetail>) cartDetailRepository.findAll();
    }


    public CartDetail saveCart(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    public void deleteCart(int id) {
        if(cartDetailRepository.existsById(id)){
            cartDetailRepository.deleteById(id);
        }
    }

    public  CartDetail  findByIdCart(int id) {
        return cartDetailRepository.findById(id).get();
    }

    public BigDecimal getTotal(List<CartDetail> list){

        return cartDetailRepository.getTotal(list);

    }
}
