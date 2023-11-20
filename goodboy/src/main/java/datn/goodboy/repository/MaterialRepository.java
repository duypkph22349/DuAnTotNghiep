package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Material;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query("SELECT b FROM Material b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<Material> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Material> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT new map(e.id as key, e.name as value) FROM Material e")
    List<Map<Integer, String>> getComboBoxMap();
}
