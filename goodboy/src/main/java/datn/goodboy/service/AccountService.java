package datn.goodboy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Account;
import datn.goodboy.repository.AccountRepository;

@Service
public class AccountService {

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
}