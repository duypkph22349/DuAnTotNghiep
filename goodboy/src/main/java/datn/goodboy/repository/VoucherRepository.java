package datn.goodboy.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.VoucherRequest;
import datn.goodboy.model.response.VoucherResponse;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
  @Query(value = "SELECT new datn.goodboy.model.response.VoucherResponse(voucher.id ,voucher.code ,voucher.name ,voucher.start_time ,voucher.end_time ,voucher.quantily ,voucher.discount ,voucher.status) FROM Voucher voucher")
  Page<VoucherResponse> getResponsePage(Pageable pageable);

  // @Query(value = "SELECT new datn.goodboy.model.request.VoucherRequest(voucher.id ,voucher.name ,voucher.start_time ,voucher.end_time ,voucher.quantily ,voucher.discount ,voucher.status) FROM Voucher voucher where voucher.id = :id")
  // Optional<VoucherRequest> getResponse(@Param("id") UUID id);
  
  @Query(value = "SELECT new datn.goodboy.model.request.VoucherRequest(voucher.id ,voucher.name ,voucher.code ,voucher.start_time ,voucher.end_time ,voucher.quantily ,voucher.discount ,voucher.status) FROM Voucher voucher where voucher.id = :id")
  Optional<VoucherRequest> getResponse(@Param("id") int id);
}
