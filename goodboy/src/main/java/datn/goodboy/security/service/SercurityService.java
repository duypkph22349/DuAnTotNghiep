package datn.goodboy.security.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.security.repo.AccountInforRepository;
import datn.goodboy.security.repo.EmployeeInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class SercurityService {
  @Autowired
  AccountInforRepository accountInfoRepository;
  @Autowired
  EmployeeInfoRepository employeInfoRepository;
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  HttpServletRequest request;

  EmployeInfoService nhanVienService() {
    return new EmployeInfoService(employeInfoRepository);
  }

  AccountInfoService accountService() {
    return new AccountInfoService(accountInfoRepository);
  }

  public void setAuthentichByEmail(String email) {
    UserDetails userDetails = loadUserDetailsByEmail(email);

    if (userDetails != null) {
      UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetails, null,
          Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD")));
      Authentication authentication = authenticationManager.authenticate(authRequest);
      SecurityContext securityContext = SecurityContextHolder.getContext();
      securityContext.setAuthentication(authentication);
      HttpSession session = request.getSession(true);
      session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
  }

  private UserDetails loadUserDetailsByEmail(String email) {
    UserDetails userDetails = null;
    if (employeInfoRepository.hasEmailis(email) != null) {
      userDetails = nhanVienService().loadUserByUsername(email);
    } else if (accountInfoRepository.hasEmailis(email) != null) {
      userDetails = accountService().loadUserByUsername(email);
    }
    return userDetails;
  }

  public void updatePassword(String email, String password) {
    String username = email;
    try {
      Optional<Employee> employee = employeInfoRepository.getuser(username);
      Optional<Account> account = accountInfoRepository.getuser(username);
      if (employee.isPresent()) {
        employee.get().setPassword(encoder.encode(password));
        employeInfoRepository.save(employee.get());
      } else if (account.isPresent()) {
        account.get().setPassword(encoder.encode(password));
        accountInfoRepository.save(account.get());
      }
    } catch (Exception e) {
    }
  }

  public void ActiveAccount() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      try {
        Optional<Employee> employee = employeInfoRepository.getuser(username);
        Optional<Account> account = accountInfoRepository.getuser(username);
        if (employee.isPresent()) {
          employee.get().setActived(true);
          employeInfoRepository.save(employee.get());
        } else if (account.isPresent()) {
          account.get().setActived(true);
          accountInfoRepository.save(account.get());
        }
      } catch (Exception e) {
        // throw exeption
      }
    } else {
      // throw exeption
    }
  }
}
