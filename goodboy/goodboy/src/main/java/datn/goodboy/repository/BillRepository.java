package datn.goodboy.repository;

import datn.goodboy.model.entity.Bill;

import java.util.List;
import java.util.UUID;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Page<Bill> findByDeletedFalse(Pageable pageable);
    List<Bill> findAll();
    Page<Bill> searchBillByCodeAndDeletedFalse(Pageable pageable,String code);
    @Query("SELECT b FROM Bill b WHERE MONTH(b.createdAt) = :month and b.deleted=false ORDER BY YEAR(b.createdAt) DESC, b.createdAt DESC")
    Page<Bill> findAllByMonthSortedByLatest(int month, Pageable pageable);
}
