package datn.goodboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datn.goodboy.model.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}
