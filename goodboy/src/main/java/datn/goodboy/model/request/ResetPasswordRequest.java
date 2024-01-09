package datn.goodboy.model.request;

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
public class ResetPasswordRequest {
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
  String password;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
  @NotNull(message = "Vui lòng nhập trường này!")
  String passwordRepeat;

  public String ValidateError() {
    String errors = "";
    if (!password.equals(passwordRepeat)) {
      errors += "Password not Match";
    }
    return errors;
  }

  public boolean validateHasError() {
    if (!password.equals(passwordRepeat)) {
      return true;
    }
    return false;
  }
}
