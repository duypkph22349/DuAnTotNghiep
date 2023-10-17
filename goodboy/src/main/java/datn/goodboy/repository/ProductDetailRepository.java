package datn.goodboy.repository;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.response.ProductDetailResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    @Query("SELECT b FROM ProductDetail b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<ProductDetail> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    Page<ProductDetail> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT new  datn.goodboy.model.response.ProductDetailResponse(entity.id,entity.code ,entity.name ,entity.price ,entity.quantity ,entity.description ,entity.idProduct.name ,entity.idPattern.name , entity.idColor.name , entity.idOrigin.name , entity.idBrand.name , entity.idMaterial.name , entity.idSize.name , entity.idStyles.name ,entity.status,entity.deleted) FROM ProductDetail entity")
    Page<ProductDetailResponse> getResponsePage(Pageable pageable);
}
