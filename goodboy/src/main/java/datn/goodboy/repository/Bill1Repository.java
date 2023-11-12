package datn.goodboy.repository;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Bill1Repository extends JpaRepository<Bill, Integer> {
    Page<Bill> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Bill> findByCodeContains(String ma, Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE b.code LIKE %:keyword% OR b.status LIKE %:keyword% OR b.loaiDon LIKE %:keyword%")
    Page<Bill> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}