package datn.goodboy.model.request;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class BillRequest {
  @NotNull
  UUID id_customer;
  @NotNull
  UUID id_employee;
  @NotNull
  int id_pay;
  @NotNull
  String code;
  @NotNull
  LocalDateTime confirmation_date;
  @NotNull
  LocalDateTime delivery_date;
  @NotNull
  LocalDateTime received_date;
  @NotNull
  LocalDateTime completion_date;
  @NotNull
  String customer_name;
  @NotNull
  String phone;
  @NotNull
  String address;
  @NotNull
  Double money_ship;
  @NotNull
  float total_money;
  @NotNull
  float reduction_amount;
  @NotNull
  float deposit;
  @NotNull
  String note;
  @NotNull
  int status;
  @NotNull
  int loaiDon;
}
