package datn.goodboy.repository;

import datn.goodboy.model.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Integer> {
}
