package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Page<Brand> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
