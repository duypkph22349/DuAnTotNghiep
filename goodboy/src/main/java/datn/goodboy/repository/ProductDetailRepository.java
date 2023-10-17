package datn.goodboy.repository;

import datn.goodboy.model.entity.ProductDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    @Query("SELECT b FROM ProductDetail b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<ProductDetail> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<ProductDetail> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
