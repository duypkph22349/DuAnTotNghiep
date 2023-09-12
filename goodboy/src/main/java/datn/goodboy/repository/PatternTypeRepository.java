package datn.goodboy.repository;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.PatternType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternTypeRepository extends JpaRepository<PatternType, Integer> {
}
