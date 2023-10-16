package datn.goodboy.model.request;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class VoucherRequest {
  int status;
  String name;
  LocalDateTime start_time;
  LocalDateTime end_time;
  int quantily;
  float discount;
}
