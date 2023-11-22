package datn.goodboy.utils.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.repository.EmployeeRepository;

@Component
public class EmailHelper {
  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  AccountRepository accountRepository;
  public boolean isEmailExists(String email) {
    List<Employee> employees = employeeRepository.getEmployeesByEmail(email);
    List<Account> accounts = accountRepository.getAccountByEmail(email);
    return !employees.isEmpty() || !accounts.isEmpty();
}

public boolean isEmailNotExists(String email) {
    return employeeRepository.hasEmailis(email).isEmpty() && accountRepository.hasEmailis(email).isEmpty();
}
}
