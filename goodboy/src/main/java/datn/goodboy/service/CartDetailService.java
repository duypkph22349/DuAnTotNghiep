package datn.goodboy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.repository.CartDetailRepository;

@Service
public class CartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;

    public Page<CartDetail> getPage(Pageable pageable) {
        return cartDetailRepository.findAll(pageable);
    }

    public ArrayList<CartDetail> getAllCartDetail() {
        return (ArrayList<CartDetail>) cartDetailRepository.findAll();
    }

    // public CartDetail findByMaGHAndMaCTSP(Cart cart, ProductDetail
    // productDetail){
    // return cartDetailRepository.findByMaGHAndMactsp(cart, productDetail);
    // }

    public CartDetail saveCart(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    public void deleteCart(int id) {
        if (cartDetailRepository.existsById(id)) {
            cartDetailRepository.deleteById(id);
        }
    }


    public CartDetail findByIdCart(int id) {
        return cartDetailRepository.findById(id).get();
    }

    public List<CartDetail> findAllByCartId(int cartId) {
        return cartDetailRepository.findAllByCartId(cartId);
    }

    public BigDecimal getTotal(List<CartDetail> cartDetails) {
        // Lấy danh sách id của Cart từ danh sách CartDetail
        List<Integer> cartIds = cartDetails.stream()
                .map(cartDetail -> cartDetail.getCart().getId())
                .collect(Collectors.toList());

        // Gọi phương thức từ repository để tính tổng giá
        return cartDetailRepository.getTotal(cartIds);
    }

    public Integer getQuantity(List<CartDetail> cartDetails) {
        // Lấy danh sách id của Cart từ danh sách CartDetail
        List<Integer> cartIds = cartDetails.stream()
                .map(cartDetail -> cartDetail.getCart().getId())
                .collect(Collectors.toList());

        // Gọi phương thức từ repository để tính tổng giá
        return cartDetailRepository.getQuantity(cartIds);
    }

//    public Integer getQuantity2(List<CartDetail> cartDetails) {
//        // Lấy danh sách id của Cart từ danh sách CartDetail
//        List<Integer> cartIds = cartDetails.stream()
//                .map(cartDetail -> cartDetail.getCart().getId())
//                .collect(Collectors.toList());
//
//        // Gọi phương thức từ repository để tính tổng giá
//        return cartDetailRepository.getQuantity2(cartIds);
//    }

//    public int getTotalCart(int soLuong) {
//        return cartDetailRepository.getTotalCart(soLuong);
//    }


}
