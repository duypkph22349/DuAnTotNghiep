package datn.goodboy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.AccountVoucher;
import datn.goodboy.repository.AccountVoucherRepository;

@Service
public class AccountVoucherService {
  // Declare the repository as final to ensure its immutability
  private final AccountVoucherRepository accountVoucherRepository;

  // Use constructor-based dependency injection
  @Autowired
  public AccountVoucherService(AccountVoucherRepository accountVoucherRepository) {
    this.accountVoucherRepository = accountVoucherRepository;
  }

  public List<AccountVoucher> getAllAccountVouchers() {
    return accountVoucherRepository.findAll();
  }

  public Optional<AccountVoucher> getAccountVoucherById(int id) {
    return accountVoucherRepository.findById(id);
  }

  public AccountVoucher saveAccountVoucher(AccountVoucher voucherdetail) {
    return accountVoucherRepository.save(voucherdetail);
  }

  public void deleteAccountVoucher(int id) {
    accountVoucherRepository.deleteById(id);
  }
}
