package datn.goodboy.service;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.Product;
import datn.goodboy.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
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

    public List<Map<Integer, String>> getCombobox() {
        return productRepository.getComboBoxMap();
    }

    public void deleteProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            if (product.get().isDeleted()) {
                product.get().setDeleted(false);
            } else {
                product.get().setDeleted(true);
            }
            productRepository.save(product.get());
        }
    }
}
