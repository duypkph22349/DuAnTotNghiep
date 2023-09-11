package datn.goodboy.model.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class AccoutVoucherRequest {
  @NotNull
  UUID idAccount;
  @NotNull
  UUID idVoucher;
  @NotNull
  int status;
}
