package datn.goodboy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.request.AccountFillter;
import datn.goodboy.model.request.AccountRequest;
import datn.goodboy.model.response.AccountResponse;
import datn.goodboy.repository.AccountRepository;

@Service
public class AccountService {
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

  public List<AccountResponse> getPageNo(int pageno, String sortBy, boolean sortDir) {
    // Sort sort =
    return null;
  }

  public AccountResponse createAccout(AccountRequest request) {
    Account newAcc = new Account();
    newAcc.setEmail(request.email);
    newAcc.setPassword(encoder.encode(request.password));
    newAcc.setStatus(0);
    Account saveAccout = accountRepository.save(newAcc);
    return new AccountResponse(saveAccout.getId(), saveAccout.getEmail(), saveAccout.getStatus());
  }

  public AccountResponse changePassword(AccountRequest request) {
    Optional<Account> accountExits = accountRepository.findById(request.idCustomer);
    if (accountExits.isPresent()) {
      Account newAcc = accountExits.get();
      newAcc.setPassword(encoder.encode(request.password));
      Account saveAccout = accountRepository.save(newAcc);
      return new AccountResponse(saveAccout.getId(), saveAccout.getEmail(), saveAccout.getStatus());
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Account with id ");
    }
  }

  public List<AccountResponse> fillter(AccountFillter fillter) {
    return null;
  }

}
