package datn.goodboy.service;

import datn.goodboy.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
}
