package datn.goodboy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.VoucherRequest;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
  @Query(value = "SELECT new datn.goodboy.model.request.VoucherRequest(voucher.id ,voucher.name,voucher.start_time ,voucher.end_time ,voucher.quantily ,voucher.discount ,voucher.status ,voucher.types ,voucher.max_discount ,voucher.min_order) FROM Voucher voucher where voucher.id = :id")
  Optional<VoucherRequest> getResponse(@Param("id") int id);
  @Query("SELECT voucher FROM Voucher voucher " +
       "WHERE voucher.deleted = false " +
       "AND voucher.start_time < CURRENT_TIMESTAMP " +
       "AND (voucher.end_time > CURRENT_TIMESTAMP OR voucher.end_time IS NULL) " +
       "AND voucher.status = 1")
Page<Voucher> getAbleVoucher(Pageable pageable);
}
