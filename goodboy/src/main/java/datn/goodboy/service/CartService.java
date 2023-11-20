package datn.goodboy.service;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.repository.CartDetailRepository;
import datn.goodboy.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    private CartDetailRepository cartRepository;

    @Autowired
    private CartRepository cartRp;

    public Page<CartDetail> getPage(Pageable pageable){
        return cartRepository.findAll(pageable);
    }

    public ArrayList<CartDetail> getAllCart(){
        return (ArrayList<CartDetail>) cartRepository.findAll();
    }


    public CartDetail saveCart(CartDetail cart) {

        return cartRepository.save(cart);
    }

    public void addGioHang(Cart cart) {

         cartRp.save(cart);
    }

    public void deleteCart(int id) {

        cartRepository.deleteById(id);
    }

    public Optional<CartDetail> findByIdCart(int id) {

        return cartRepository.findById(id);
    }

//    public Cart findByMaNV(String maNV) {
//        Employee employee = new Employee();
//        employee.setCode(maNV);
//        return cartRp.findByMaNV(employee);
//    }




}
