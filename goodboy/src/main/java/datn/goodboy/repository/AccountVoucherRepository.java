package datn.goodboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import datn.goodboy.model.entity.AccountVoucher;

public interface AccountVoucherRepository extends JpaRepository<AccountVoucher, Integer> {

}
