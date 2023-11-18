package datn.goodboy.repository;

import datn.goodboy.model.entity.Pay;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayRepository extends JpaRepository<Pay, Integer> {
  @Query(value = "SELECT pay FROM Pay pay where pay.payment_method = :name")
  Optional<Pay> getByNameMethod(@Param("name") String name);
}
