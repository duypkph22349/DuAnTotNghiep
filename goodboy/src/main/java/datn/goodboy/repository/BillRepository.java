package datn.goodboy.repository;

import datn.goodboy.model.entity.Bill;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillRepository extends JpaRepository<Bill, Integer> {

}
