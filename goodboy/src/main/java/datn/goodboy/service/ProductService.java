package datn.goodboy.service;




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
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> getPage(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public ArrayList<Product> getAll(){
        return (ArrayList<Product>) productRepository.findAll();
    }


    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findByIdProduct(int id) {
        return productRepository.findById(id);
    }
}
