package datn.goodboy.model.request;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerRequest {
  UUID id;
  String name;
  boolean gender;
  LocalDateTime birth_date;
  String address;
  String phone;
  int status;
}
