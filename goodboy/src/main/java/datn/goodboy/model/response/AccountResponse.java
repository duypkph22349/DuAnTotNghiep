package datn.goodboy.model.response;

import java.time.LocalDate;
import java.util.UUID;

public class AccountResponse {
  UUID idCustomer;
  String name;
  LocalDate birth_date;
  String phone;
  String email;
}
