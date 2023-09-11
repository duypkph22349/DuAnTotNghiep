package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  int id;
  @Column(name = "id_customer")
  UUID id_customer;
  @Column(name = "id_employee")
  UUID id_employee;
  @Column(name = "id_pay")
  int id_pay;
  @Column(name = "code")
  String code;
  @Column(name = "confirmation_date")
  LocalDateTime confirmation_date;
  @Column(name = "delivery_date")
  LocalDateTime delivery_date;
  @Column(name = "received_date")
  LocalDateTime received_date;
  @Column(name = "completion_date")
  LocalDateTime completion_date;
  @Column(name = "customer_name")
  String customer_name;
  @Column(name = "phone")
  String phone;
  @Column(name = "address")
  String address;
  @Column(name = "money_ship")
  float money_ship;
  @Column(name = "total_money")
  float total_money;
  @Column(name = "reduction_amount")
  float reduction_amount;
  @Column(name = "deposit")
  float deposit;
  @Column(name = "note")
  String note;
  @Column(name = "status")
  int status;
}