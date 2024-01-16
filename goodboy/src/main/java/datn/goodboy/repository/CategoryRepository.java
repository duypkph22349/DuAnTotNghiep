package datn.goodboy.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datn.goodboy.model.entity.Category;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query("SELECT ca FROM Category ca WHERE  ca.deleted = false")
  List<Category> getCategoryAble();

  @Query("SELECT ca FROM Category ca WHERE ca.status = 1 AND ca.deleted = false")
  List<Category> getCategoryList();

  @Query("SELECT new map(e.id as key, e.name as value) FROM Category e  WHERE e.status = 1 AND e.deleted = false")
  List<Map<Integer, String>> getComboBoxMap();

  @Query("SELECT b FROM Category b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
  Page<Category> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

  Page<Category> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
