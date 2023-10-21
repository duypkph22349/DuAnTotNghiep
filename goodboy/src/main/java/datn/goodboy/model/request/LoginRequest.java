package datn.goodboy.model.request;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class LoginRequest {
  @NotNull(message = "Hãy nhập Email")
  @NotBlank(message = "Hãy nhập Email")
  @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email Định dạng không đúng")
  String email;
  @NotNull(message = "Hãy nhập Mật Khẩu")
  @NotBlank(message = "Hãy nhập Mật Khẩu")
  @Size(min = 8, max = 25, message = "Mật khẩu từ 8 đến 25 ký tự ")
  String password;
  boolean rememberme;
}
