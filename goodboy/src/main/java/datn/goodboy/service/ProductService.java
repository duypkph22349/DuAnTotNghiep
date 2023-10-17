package datn.goodboy.service;




import datn.goodboy.model.entity.PatternType;
import datn.goodboy.model.entity.Product;
import datn.goodboy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
<<<<<<< HEAD
//    @Autowired
//    private ProductRepository productRepository;
//
//    public Page<Product> findAll(Pageable pageable) {
//        return productRepository.findAllByOrderByCreatedAtDesc(pageable);
//    }
//
//    public Product add(Product origin) {
//        return productRepository.save(origin);
//    }
//
//    public Product update(Integer id, Product color) {
//        Product color1 = productRepository.findById(id).get();
//        color1.setName(color.getName());
//        color1.setUpdatedAt(color.getUpdatedAt());
//        color1.setStatus(color.getStatus());
//        return productRepository.save(color1);
//    }
=======
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Product add(Product origin) {
        return productRepository.save(origin);
    }

    public Product update(Integer id, Product color) {
        Product color1 = productRepository.findById(id).get();
        color1.setName(color.getName());
        color1.setUpdatedAt(color.getUpdatedAt());
        color1.setStatus(color.getStatus());
        return productRepository.save(color1);
    }
    public Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    public Page<Product> searchProductByKeyword(String keyword, Pageable pageable) {
        return productRepository.searchByKeyword(keyword, pageable);
    }
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
}
