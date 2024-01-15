package datn.goodboy.repository;

import datn.goodboy.model.entity.Bill;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillRepository extends JpaRepository<Bill, Integer> {
  @Query("SELECT b FROM Bill b WHERE b.deleted = false AND b.status =:status ORDER BY b.createdAt DESC")
  Page<Bill> findByDeletedFalseOrderByStatus(Pageable pageable, int status);

  @Query("SELECT b FROM Bill b WHERE b.deleted = false ORDER BY b.createdAt DESC")
  Page<Bill> findByDeletedFalseOrderByCreateDateDesc(Pageable pageable);

  @Query("SELECT b FROM Bill b WHERE b.deleted = false AND b.status_pay =:status ORDER BY b.createdAt DESC")
  Page<Bill> findByStatusPay(Pageable pageable, int status);

  @Query("SELECT b FROM Bill b WHERE b.deleted = false AND b.loaiDon =:id ORDER BY b.createdAt DESC")
  Page<Bill> findByOrderType(Pageable pageable, int id);

  List<Bill> findAll();

  Optional<Bill> findByCode(String code);

  @Query("SELECT b FROM Bill b WHERE b.id =:id")
  Bill findStatusById(int id);

  @Query("SELECT b FROM Bill b WHERE LOWER(b.code) LIKE CONCAT(LOWER(:code), '%') or  LOWER(b.customer_name) LIKE CONCAT(LOWER(:code), '%')  AND b.deleted = false")
  Page<Bill> searchBillByCodeAndDeletedFalse(Pageable pageable, @Param("code") String code);

  @Query("SELECT b FROM Bill b WHERE MONTH(b.createdAt) = :month and b.deleted=false ORDER BY YEAR(b.createdAt) DESC, b.createdAt DESC")
  Page<Bill> findAllByMonthSortedByLatest(int month, Pageable pageable);

  @Query("SELECT b FROM Bill b WHERE b.deleted=false AND b.createdAt BETWEEN :startDate AND :endDate ORDER BY b.createdAt DESC")
  Page<Bill> filterDate(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

  @Query("SELECT b FROM Bill b WHERE b.status = :status")
  Page<Bill> searchBillByStatus(Pageable pageable, @Param("status") int status);

  @Query("SELECT b FROM Bill b WHERE b.status = 1")
  List<Bill> findBillByStatus1();

  @Query(value = """
    SELECT TOP 1 SUBSTRING(code, 3,len(code)) FROM bill order by id desc
    """, nativeQuery = true)
  Integer findNewCodeBill();
}
