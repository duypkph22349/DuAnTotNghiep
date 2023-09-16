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

  public List<AccountResponse> getPageNo(int pageno, String sortBy, boolean sortDir) {
    Sort sort = Sort.by(sortBy);
    if (sortDir) {
      sort.ascending();
    } else {
      sort.descending();
    }
    Pageable page = PageRequest.of(pageno, 10, sort);
    return accountRepository.getPageAccountRepose(page).getContent();
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
  public List<AccountResponse> getFirstPage(int pageSize, String sortBy, boolean sortDir) {
    List<AccountResponse> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort = Sort.by(sortBy);
    if (sortDir) {
      sort.ascending();
    } else {
      sort.descending();
    }
    // Pageable object
    Pageable pageable = PageRequest.of(0, pageSize, sort);
    // findAll method and pass pageable instance
    Page<AccountResponse> page = accountRepository.getPageAccountRepose(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public List<AccountResponse> getLastPage(int pageSize, String sortBy, boolean sortDir) {
    List<AccountResponse> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort = Sort.by(sortBy);
    if (sortDir) {
      sort.ascending();
    } else {
      sort.descending();
    }
    // Pageable object
    Pageable pageable = PageRequest.of(getPageNumber(pageSize).length - 1, pageSize, sort);
    // findAll method and pass pageable instance
    Page<AccountResponse> page = accountRepository.getPageAccountRepose(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public List<AccountResponse> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    List<AccountResponse> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort = Sort.by(sortBy);
    if (sortDir) {
      sort.ascending();
    } else {
      sort.descending();
    }
    // Pageable object
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    // findAll method and pass pageable instance
    Page<AccountResponse> page = accountRepository.getPageAccountRepose(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int[] getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(1, rowcount);
    Page<AccountResponse> page = accountRepository.getPageAccountRepose(pageable);
    int totalPage = page.getTotalPages();
    int[] nb = new int[totalPage];
    for (int i = 0; i < totalPage; i++) {
      nb[i] = i + 1;
    }
    return nb;
  }
  // panigation end

}
