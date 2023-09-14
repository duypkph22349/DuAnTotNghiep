package datn.goodboy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
  public List<AccountVoucher> addVoucherToAccounts(UUID idVoucher, List<UUID> idAccount) {
    List<AccountVoucher> list = new ArrayList<>();
    for (UUID idacc : idAccount) {
      AccountVoucher vcacc = new AccountVoucher();
      vcacc.setId_account(idacc);
      vcacc.setId_voucher(idVoucher);
      vcacc.setStatus(1);
      list.add(vcacc);
    }
    return accountVoucherRepository.saveAll(list);
  }

  public List<AccountVoucher> removeVoucherToAccounts(List<Integer> idVcAcc) {
    List<AccountVoucher> listacc = accountVoucherRepository.findAllById(idVcAcc);
    listacc.forEach(acc -> acc.setStatus(0));
    return accountVoucherRepository.saveAll(listacc);
  }

  // user

}
