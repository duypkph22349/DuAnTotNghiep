package datn.goodboy.model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class EmployeeSignUpRequest {

  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String email;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String name;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String phone;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String password;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String passwordRepeat;
  @NotNull(message = "Vui lòng nhập trường này!")
  boolean sex;
  @NotNull(message = "trường này không thể để trống")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent(message = "Ngày sinh không hợp lệ")
  LocalDate birthDay;

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
