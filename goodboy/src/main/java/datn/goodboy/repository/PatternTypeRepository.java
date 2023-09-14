package datn.goodboy.repository;

import datn.goodboy.model.entity.PatternType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatternTypeRepository extends JpaRepository<PatternType, Integer> {
    Page<PatternType> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
