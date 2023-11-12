package datn.goodboy.repository;

import datn.goodboy.model.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bill1Repository extends JpaRepository<Bill, Integer> {
    Page<Bill> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Bill> findByCodeContains(String ma, Pageable pageable);
}