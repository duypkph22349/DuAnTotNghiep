package datn.goodboy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.request.AccountFillter;
import datn.goodboy.model.request.AccountRequest;
import datn.goodboy.model.request.SingupRequest;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.repository.CustomerRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;

@Service
public class AccountService implements PanigationInterface<Account> {
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  CustomerRepository cusrepository;
  // Declare the repository as final to ensure its immutability
  @Autowired
  AccountRepository accountRepository;

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
    Optional<Account> account = accountRepository.findById(id);
    if (account.isPresent()) {
      if (account.get().isDeleted()) {
        account.get().setDeleted(false);
        account.get().setStatus(1);
      } else {
        account.get().setDeleted(true);
        account.get().setStatus(-1);
      }
      accountRepository.save(account.get());
    }
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

  public Account singup(SingupRequest request) {
    Optional<Customer> customer = cusrepository.findById(request.getId_customer());
    if (!customer.isPresent() || customer == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Account account = new Account();
    account.setCustomer(customer.get());
    account.setEmail(request.getEmail());
    account.setPassword(encoder.encode(request.getPassword()));
    account.setStatus(0);
    return accountRepository.save(account);
  }

  // admin
  public Account updateAccount(UUID id, int status) {
    Optional<Account> accountupdate = accountRepository.findById(id);
    if (accountupdate.isPresent()) {
      Account account2 = accountupdate.get();
      account2.setStatus(status);
      if (status == -1) {
        account2.setDeleted(true);
      }
      return accountRepository.save(account2);
    } else {
      throw new RuntimeException();
    }
  }
  public void updateAccount(UUID id, String fullname, boolean gender, String phone, String email, int status) {
    Optional<Account> optionalAccount = accountRepository.findById(id);

    if (optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      Customer customer = account.getCustomer();

      // Cập nhật các thuộc tính của Customer
      customer.setName(fullname);
      customer.setGender(gender);
      customer.setPhone(phone);

      // Cập nhật các thuộc tính của Account
      account.setEmail(email);
      account.setStatus(status);

      // Lưu lại vào cơ sở dữ liệu
      accountRepository.save(account);
    } else {
      // Xử lý khi không tìm thấy tài khoản
      throw new EntityNotFoundException("Không tìm thấy tài khoản với ID: " + id);
    }
  }
  public Account findAccountByEmail(String email) {
    // Sử dụng phương thức findByEmail từ repository để tìm tài khoản theo email
    return accountRepository.getAccountByEmails(email);
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
  public List<Account> fillter(AccountFillter fillter) {
    return null;
  }

  // panigation
  @Override
  public List<Account> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > getPageNumber(pageSize)) {
      return null;
    }
    List<Account> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    // findAll method and pass pageable instance
    Page<Account> page = accountRepository.findAll(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(1, rowcount);
    Page<Account> page = accountRepository.findAll(pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<Account> page = accountRepository.findAll(pageable);
    int totalPage = page.getTotalPages();
    int[] rs;
    if (totalPage <= 1) {
      int[] rs1 = { 1 };
      return rs1;
    } else if (totalPage <= 3) {
      rs = new int[totalPage];
      for (int i = 1; i <= totalPage; i++) {
        rs[i - 1] = i;
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
}
