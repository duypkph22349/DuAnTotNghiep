package datn.goodboy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.AccountVoucher;
import datn.goodboy.model.request.AccountRequest;
import datn.goodboy.model.request.BillRequest;
import datn.goodboy.model.response.AccountResponse;
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

  // admin
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

  // manager
  public List<AccountVoucher> addVoucherToAccounts(UUID idVoucher, List<String> idAccount) {
    return null;
  }

  public List<AccountVoucher> removeVoucherToAccounts(UUID idVoucher, List<String> idAccount) {
    return null;
  }

  public AccountResponse addVoucher(AccountRequest request) {
    return null;
  }

  public AccountResponse updateVoucher(AccountRequest request) {
    return null;
  }

  // user
  public boolean useAccount(BillRequest request) {
    return true;
  }
}
