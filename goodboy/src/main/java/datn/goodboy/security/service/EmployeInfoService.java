package datn.goodboy.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.security.UserInfo;
import datn.goodboy.security.UserInfoUserDetails;
import datn.goodboy.security.repo.EmployeeInfoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeInfoService implements UserDetailsService {
  private final EmployeeInfoRepository repository;
  @Autowired
  PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Employee> employee = repository.getuser(username);
    if (!employee.isPresent() || employee == null) {
      throw new UsernameNotFoundException("Not found user with username is" + username);
    } else {
      if(employee.get().isActived()){
        UserInfo userinfo = new UserInfo(employee.get().getEmail(), employee.get().getPassword(),
        employee.get().getRoles().getRole());
        return new UserInfoUserDetails(userinfo);
      }else{
        UserInfo userinfo = new UserInfo(employee.get().getEmail(), employee.get().getPassword(),
        "NOT_ACCTIVE");
        return new UserInfoUserDetails(userinfo);
      }
    }
  }

  public Employee getEmployeeByEmail(String email) {
    Optional<Employee> empOptional = repository.findByEmail(email);
    if (empOptional.isPresent()) {
      return empOptional.get();
    }
    return null;
  }
}
