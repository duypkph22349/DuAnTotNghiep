package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Page<Material> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
