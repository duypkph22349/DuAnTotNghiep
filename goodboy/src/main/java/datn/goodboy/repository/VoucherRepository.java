package datn.goodboy.repository;

import java.util.List;
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
                     "AND voucher.status = 1" +
                     "AND voucher.quantily > 0")
       List<Voucher> getAbleVoucher();

       @Query("SELECT voucher FROM Voucher voucher " +
                     "WHERE ((" +
                     "    :status = 1 AND (LOWER(voucher.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(voucher.name) LIKE LOWER(CONCAT('%', :search, '%'))) "
                     +
                     "    AND voucher.deleted = false " +
                     "    AND voucher.start_time < CURRENT_TIMESTAMP " +
                     "    AND (voucher.end_time > CURRENT_TIMESTAMP OR voucher.end_time IS NULL) " +
                     ") OR (" +
                     "    :status = 0 AND (LOWER(voucher.code) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(voucher.name) LIKE LOWER(CONCAT('%', :search, '%')))" +
                     "))")
       Page<Voucher> searchStatus(Pageable pageable, @Param("search") String search, @Param("status") int status);

       @Query("SELECT voucher FROM Voucher voucher " +
               "WHERE voucher.code = :code ")
       List<Voucher> findVoucherByCode(@Param("code") String code);

       Optional<Voucher> findByCode(String code);
}
