package datn.goodboy.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.Account;
import datn.goodboy.security.UserInfo;
import datn.goodboy.security.UserInfoUserDetails;
import datn.goodboy.security.repo.AccountInforRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountInforService implements UserDetailsService {
  private final AccountInforRepository repository;
  @Autowired
  PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) {
    System.out.println(username);
    Optional<Account> account = repository.getuser(username);
    if (!account.isPresent() || account == null) {
      throw new UsernameNotFoundException("can not find nhan vien with username khachhang");
    }else{
      String Roles = "USER";
      UserInfo UserInfo = new UserInfo(account.get().getEmail(), account.get().getPassword(), Roles);
      return new UserInfoUserDetails(UserInfo);
    }
  }
}
