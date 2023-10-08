package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

    @Query("SELECT b FROM Color b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<Color> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Color> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
