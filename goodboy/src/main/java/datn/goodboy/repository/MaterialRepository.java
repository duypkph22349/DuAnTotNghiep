package datn.goodboy.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query("SELECT b FROM Material b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<Material> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Material> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT new map(e.id as key, e.name as value) FROM Material e  WHERE e.status = 1 AND e.deleted = false")
    List<Map<Integer, String>> getComboBoxMap();

    @Query("SELECT b FROM Material b WHERE b.status = 1 AND b.deleted = false")
    List<Material> getMaterialList();
}
