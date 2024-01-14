package datn.goodboy.security.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import datn.goodboy.exeption.AuthenticationException;
import datn.goodboy.exeption.AuthenticationException.PasswordNotMatchException;
import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.request.EmployeeChangePassword;
import datn.goodboy.model.request.UserChangePassword;
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
  PasswordEncoder passwordEncoder;
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
        String encodedPassword = encoder.encode(password);
        employee.get().setPassword(encodedPassword);
        employeInfoRepository.save(employee.get());
        System.out.println("Password updated for Employee: " + username);
      } else if (account.isPresent()) {
        String encodedPassword = encoder.encode(password);
        account.get().setPassword(encodedPassword);
        accountInfoRepository.save(account.get());
        System.out.println("Password updated for Account: " + username);
      } else {
        System.out.println("User not found: " + username);
      }
    } catch (Exception e) {
      e.printStackTrace();
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

  public Account changePassword(UserChangePassword request) {
    Optional<Account> accountOptional = accountInfoRepository.getuser(request.getUsername());

    return accountOptional.map(account -> {
      if (passwordEncoder.matches(request.getPassword(), account.getPassword())) {
        account.setPassword(encoder.encode(request.getNewpassword()));
        return accountInfoRepository.save(account);
      } else {
        throw new PasswordNotMatchException("Mật khẩu của bạn không đúng !!!");
      }
    }).orElseThrow(() -> new AuthenticationException("Đăng nhập để tiếp tục"));
  }

  public UserChangePassword getUserChangePassword() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      Optional<Account> account = accountInfoRepository.getuser(authentication.getName());
      if (account.isPresent()) {
        UserChangePassword user = new UserChangePassword();
        user.setUsername(account.get().getEmail());
        return user;
      } else {
        throw new AuthenticationException("Đăng nhập để tiếp tục ");
      }
    } else {
      throw new AuthenticationException("Đăng nhập để tiếp tục ");
    }
  }

  public Employee changePassword(EmployeeChangePassword request) {
    Optional<Employee> employee = employeInfoRepository.getuser(request.getUsername());
    if (employee.isPresent()) {
      if (passwordEncoder.matches(request.getPasswordold(), employee.get().getPassword())) {
        throw new PasswordNotMatchException("Mật khẩu của bạn không đúng !!!");
      } else {
        employee.get().setPassword(encoder.encode(request.getPassword()));
        return employeInfoRepository.save(employee.get());
      }
    } else {
      throw new AuthenticationException("Đăng nhập để tiếp tục ");
    }
  }
}
