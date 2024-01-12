package datn.goodboy.model.request;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class UserChangePassword {
  String username;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String password;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
  String newpassword;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
  @NotNull(message = "Vui lòng nhập trường này!")
  String newconfirm;

  public String ValidateError() {
    String errors = "";
    if (!newpassword.equals(newconfirm)) {
      errors += "Password not Match";
    }
    return errors;
  }

  public boolean validateHasError() {
    if (!newpassword.equals(newconfirm)) {
      return true;
    }
    return false;
  }
}
