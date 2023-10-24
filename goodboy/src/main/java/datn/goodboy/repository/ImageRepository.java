package datn.goodboy.repository;

import datn.goodboy.model.entity.Images;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer> {
    Page<Images> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = "SELECT image FROM Images image WHERE image.idProductDetail.id = :idproductdetail")
    List<Images> getAllImageInProductDetail(@Param("idproductdetail") Integer idProductDetail);
}
