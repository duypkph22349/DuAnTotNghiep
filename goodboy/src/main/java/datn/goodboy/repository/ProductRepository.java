package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
