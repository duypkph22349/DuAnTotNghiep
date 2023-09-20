package datn.goodboy.repository;

import datn.goodboy.model.entity.Images;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Images,Integer> {
    Page<Images> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
