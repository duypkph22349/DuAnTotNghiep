package datn.goodboy.model.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * VoucherResponse
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class VoucherResponse {
  int id;
  String code;
  String name;
  LocalDateTime start_time;
  LocalDateTime end_time;
  int quantily;
  float discount;
  int status;
  boolean types;
  Double max_discount;
  Double min_order;
  boolean deleted;
}