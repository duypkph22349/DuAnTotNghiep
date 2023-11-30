package datn.goodboy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datn.goodboy.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query("SELECT ca FROM Category ca WHERE  ca.deleted = false")
  List<Category> getCategoryAble();
}
