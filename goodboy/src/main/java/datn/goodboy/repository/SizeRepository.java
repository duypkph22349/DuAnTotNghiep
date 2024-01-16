package datn.goodboy.repository;

import datn.goodboy.model.entity.Size;

import java.util.List;
import java.util.Map;

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

    @Query("SELECT new map(e.id as key, e.name as value) FROM Size e  WHERE e.status = 1 AND e.deleted = false")
    List<Map<Integer, String>> getComboBoxMap();

    @Query("SELECT b FROM Size b WHERE b.status =1 AND b.deleted = false")
    List<Size> getSizeList();
}
