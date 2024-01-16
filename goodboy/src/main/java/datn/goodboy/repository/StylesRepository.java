package datn.goodboy.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Styles;

@Repository
public interface StylesRepository extends JpaRepository<Styles, Integer> {
    @Query("SELECT b FROM Styles b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<Styles> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Styles> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT new map(e.id as key, e.name as value) FROM Styles e  WHERE e.status = 1 AND e.deleted = false")
    List<Map<Integer, String>> getComboBoxMap();

    @Query("SELECT b FROM Styles b WHERE b.status = 1 AND b.deleted = false")
    List<Styles> getStylesList();
}
