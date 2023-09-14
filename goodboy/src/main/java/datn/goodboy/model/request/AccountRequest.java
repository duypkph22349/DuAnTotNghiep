package datn.goodboy.model.request;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class AccountRequest {
  @NotNull
  UUID idCustomer;
  @NotNull
  String email;
  @NotNull
  String password;
  int status;
}
