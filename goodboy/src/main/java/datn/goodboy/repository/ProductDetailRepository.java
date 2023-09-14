package datn.goodboy.repository;

import datn.goodboy.model.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    Page<ProductDetail> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
