package datn.goodboy.repository;

import datn.goodboy.model.entity.Origin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer> {
    Page<Origin> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
