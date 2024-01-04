package datn.goodboy.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datn.goodboy.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query("SELECT ca FROM Category ca WHERE  ca.deleted = false")
  List<Category> getCategoryAble();

  @Query("SELECT ca FROM Category ca WHERE ca.status = 1 AND ca.deleted = false")
  List<Category> getCategoryList();

  @Query("SELECT new map(e.id as key, e.name as value) FROM Category e")
  List<Map<Integer, String>> getComboBoxMap();
}
