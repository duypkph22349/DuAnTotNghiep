package datn.goodboy.model.request;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class VoucherDetailRequest {
  UUID idBill;
  UUID idVoucher;
  float money_before_reduction;
  float money_after_reduction;
  float money_reduction;
  int status;
}
