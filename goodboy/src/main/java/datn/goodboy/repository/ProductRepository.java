package datn.goodboy.repository;


import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
