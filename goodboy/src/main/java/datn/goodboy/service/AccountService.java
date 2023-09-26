package datn.goodboy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.request.AccountFillter;
import datn.goodboy.model.request.AccountRequest;
import datn.goodboy.model.response.AccountResponse;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;

@Service
public class AccountService implements PanigationInterface<AccountResponse> {
  @Autowired
  PasswordEncoder encoder;
  // Declare the repository as final to ensure its immutability
  private final AccountRepository accountRepository;

  // Use constructor-based dependency injection
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
  }

  public Optional<Account> getAccountById(UUID id) {
    return accountRepository.findById(id);
  }

  public Account saveAccount(Account account) {
    return accountRepository.save(account);
  }

  public void deleteAccount(UUID id) {
    accountRepository.deleteById(id);
  }

  public Account saveAccount(AccountRequest account) {
    Account acc = new Account();
    acc.setEmail(account.getEmail());
    acc.setId(account.getIdCustomer());
    acc.setPassword(encoder.encode(account.getPassword()));
    acc.setStatus(account.getStatus());
    return accountRepository.save(acc);
  }

  public AccountRequest getAccountRequetById(UUID id) {
    Optional<Account> acc = accountRepository.findById(id);
    if (acc.isPresent()) {
      return new AccountRequest(acc.get());
    }
    {
      throw new RuntimeException();
    }

  }

    // admin
    public Account updateAccount(AccountRequest account) {
      Optional<Account> accountupdate = accountRepository.findById(account.idCustomer);
      if (accountupdate.isPresent()) {
        Account account2 = accountupdate.get();
        account2.setId(account.idCustomer);
        account2.setEmail(account.email);
        return accountRepository.save(account2);
      } else {
        throw new RuntimeException();
      }
    }

    public Account createAccout(AccountRequest request) {
      Account newAcc = new Account();
      newAcc.setEmail(request.email);
      newAcc.setPassword(encoder.encode(request.password));
      newAcc.setStatus(0);
      Account saveAccout = accountRepository.save(newAcc);
      return saveAccout;
    }

    public Account changePassword(AccountRequest request) {
      Optional<Account> accountExits = accountRepository.findById(request.idCustomer);
      if (accountExits.isPresent()) {
        Account newAcc = accountExits.get();
        newAcc.setPassword(encoder.encode(request.password));
        Account saveAccout = accountRepository.save(newAcc);
        return saveAccout;
      } else {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Account with id ");
      }
    }

    // fillter
    public List<AccountResponse> fillter(AccountFillter fillter) {
      return null;
    }

    // panigation
    @Override
    public List<AccountResponse> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
      List<AccountResponse> ChiTietSanPhams;
      ChiTietSanPhams = new ArrayList<>();
      Sort sort;
      if (sortDir) {
        sort = Sort.by(sortBy).ascending();
        System.out.println("tang");
      } else {
        sort = Sort.by(sortBy).descending();
        System.out.println("giam");
      }
      Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
      // findAll method and pass pageable instance
      Page<AccountResponse> page = accountRepository.getPageAccountRepose(pageable);
      ChiTietSanPhams = page.getContent();
      return ChiTietSanPhams;
    }

    @Override
    public int getPageNumber(int rowcount) {
      Pageable pageable = PageRequest.of(1, rowcount);
      Page<AccountResponse> page = accountRepository.getPageAccountRepose(pageable);
      int totalPage = page.getTotalPages();
      return totalPage;
    }

    @Override
    public int[] getPanigation(int rowcount, int pageno) {
      Pageable pageable = PageRequest.of(1, rowcount);
      Page<AccountResponse> page = accountRepository.getPageAccountRepose(pageable);
      int totalPage = page.getTotalPages();
      int[] rs;
      if (totalPage <= 3) {
        rs = new int[totalPage];
        for (int i = 1; i <= totalPage; i++) {
          rs[i] = i;
        }
        return rs;
      } else {
        rs = new int[3];
        if (pageno <= 2) {
          int[] rs1 = { 1, 2, 3 };
          rs = rs1;
        }
        if (pageno > 2) {
          if (pageno < totalPage - 1) {
            int[] rs1 = { pageno - 1, pageno, pageno + 1 };
            rs = rs1;
          }
          if (pageno >= totalPage - 1) {
            int[] rs1 = { totalPage - 2, totalPage - 1, totalPage };
            rs = rs1;
          }
        }
        return rs;
      }
    }
    // panigation end
}
