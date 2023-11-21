package datn.goodboy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.ImageProduct;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
    Page<ImageProduct> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = "SELECT image FROM ImageProduct image WHERE image.idProduct.id = :idproduct")
    List<ImageProduct> getAllImageInProductDetail(@Param("idproduct") Integer idProductDetail);
}
