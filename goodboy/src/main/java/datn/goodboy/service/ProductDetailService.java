package datn.goodboy.service;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.PatternType;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public ArrayList<ProductDetail> getAllProductDetail(){
        return (ArrayList<ProductDetail>) productDetailRepository.findAll();
    }

    public Optional<ProductDetail> getProductDetailById(Integer id){
        return productDetailRepository.findById(id);
    }

}
