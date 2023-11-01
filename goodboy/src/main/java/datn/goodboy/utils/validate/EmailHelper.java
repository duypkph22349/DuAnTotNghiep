package datn.goodboy.utils.validate;

import java.util.ArrayList;
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

  public boolean isEmailExits(String email) {
    List<Employee> listE = employeeRepository.getEmployeesByEmail(email);
    List<Account> listA = accountRepository.getAccountByEmail(email);
    if (listA == null) {
      if (listE == null) {
        return false;
      }
    }
    if (listA.isEmpty()) {
      if (listE.isEmpty()) {
        return false;
      }
    }
    return true;
  }
}
