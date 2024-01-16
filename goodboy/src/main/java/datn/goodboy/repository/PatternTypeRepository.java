package datn.goodboy.repository;

import datn.goodboy.model.entity.PatternType;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatternTypeRepository extends JpaRepository<PatternType, Integer> {
    @Query("SELECT b FROM PatternType b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<PatternType> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<PatternType> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT new map(e.id as key, e.name as value) FROM PatternType e  WHERE e.status = 1 AND e.deleted = false")
    List<Map<Integer, String>> getComboBoxMap();

    @Query("SELECT b FROM PatternType b WHERE b.status = 1 AND b.deleted = false")
    List<PatternType> getPatternTypeList();

}
