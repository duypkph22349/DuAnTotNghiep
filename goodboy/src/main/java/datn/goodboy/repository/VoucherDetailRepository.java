package datn.goodboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datn.goodboy.model.entity.VoucherDetail;

public interface VoucherDetailRepository extends JpaRepository<VoucherDetail, Integer> {

}
