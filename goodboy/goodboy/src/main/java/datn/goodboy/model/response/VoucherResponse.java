package datn.goodboy.model.response;

import java.time.LocalDateTime;

/**
 * VoucherResponse
 */
public class VoucherResponse {
  int status;
  String name;
  LocalDateTime start_time;
  LocalDateTime end_time;
  int quantily;
  float discount;
}