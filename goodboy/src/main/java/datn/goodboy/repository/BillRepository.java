package datn.goodboy.repository;

import datn.goodboy.model.entity.Bill;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillRepository extends JpaRepository<Bill, Integer> {
  Page<Bill> findByDeletedFalse(Pageable pageable);

  List<Bill> findAll();

  Optional<Bill> findByCode(String code);

  @Query("SELECT b FROM Bill b WHERE LOWER(b.code) LIKE CONCAT(LOWER(:code), '%') or  LOWER(b.customer.name) LIKE CONCAT(LOWER(:code), '%')  AND b.deleted = false")
  Page<Bill> searchBillByCodeAndDeletedFalse(Pageable pageable, @Param("code") String code);

  @Query("SELECT b FROM Bill b WHERE MONTH(b.createdAt) = :month and b.deleted=false ORDER BY YEAR(b.createdAt) DESC, b.createdAt DESC")
  Page<Bill> findAllByMonthSortedByLatest(int month, Pageable pageable);

  @Query("SELECT b FROM Bill b WHERE b.status = :status")
  Page<Bill> searchBillByStatus(Pageable pageable, @Param("status") int status);
}
