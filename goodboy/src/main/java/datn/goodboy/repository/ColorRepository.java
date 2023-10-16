package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Page<Color> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
