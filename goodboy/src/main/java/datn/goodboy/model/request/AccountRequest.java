package datn.goodboy.model.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AccountRequest {
  @NotNull(message = "Hãy chọn khách hàng")
  UUID idCustomer;
  @NotNull(message = "Hãy nhập Email")
  @NotBlank(message = "Hãy nhập Email")
  @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email Định dạng không đúng")
  String email;
  @NotNull(message = "Hãy nhập Mật Khẩu")
  @NotBlank(message = "Hãy nhập Mật Khẩu")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
  String password;
  @NotNull(message = "Hãy nhập Lại Mật khẩu")
  @NotBlank(message = "Hãy nhập Lại Mật khẩu")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
  String passwordRepeat;
  @Range(min = -1, max = 1)
  int status;

  public AccountRequest(Account account) {
    this.idCustomer = account.getId();
    this.email = account.getEmail();
    this.status = account.getStatus();
  }
}
