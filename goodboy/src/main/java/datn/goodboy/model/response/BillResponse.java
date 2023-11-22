package datn.goodboy.model.response;

import java.time.LocalDateTime;
import java.util.UUID;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.Pay;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {

  Customer customer;

  Employee employee;

  Pay pay;

  String code;

  LocalDateTime confirmation_date;

  LocalDateTime delivery_date;

  LocalDateTime received_date;

  LocalDateTime completion_date;

  String customer_name;

  String phone;

  String address;

  float money_ship;

  float total_money;

  float reduction_amount;

  float deposit;

  String note;

  int status;

  int loaiDon;
}
