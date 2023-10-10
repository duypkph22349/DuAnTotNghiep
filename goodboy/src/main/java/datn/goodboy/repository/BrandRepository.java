package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("SELECT b FROM Brand b WHERE b.code LIKE %:keyword% OR b.name LIKE %:keyword%")
    Page<Brand> searchByKeyword(@Param("keyword") String keyword,Pageable pageable);

    Page<Brand> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
