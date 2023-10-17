package datn.goodboy.repository;

import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query("SELECT b FROM Size b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<Size> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Size> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
