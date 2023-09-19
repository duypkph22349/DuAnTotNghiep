package datn.goodboy.model.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotNull
  UUID idCustomer;
  @NotNull
  @NotBlank
  String email;
  @NotNull
  @NotBlank
  String password;
  @NotNull
  @NotBlank
  String passwordRepeat;
  @Range(min = 0, max = 4)
  int status;

  public AccountRequest(Account account) {
    this.idCustomer = account.getId();
    this.email = account.getEmail();
    this.status = account.getStatus();
  }
}
