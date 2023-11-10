package datn.goodboy.model.entity;

import java.time.LocalDate;
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
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  UUID id;
  @Column(name = "code")
  String code;
  @Column(name = "name")
  String name;
  @Column(name = "gender")
  boolean gender;
  @Column(name = "phone")
  String phone;
  @Column(name = "birth_date")
  LocalDate birth_date;
  @Column(name = "address")
  String address;
  @Column(name = "wardcode")
  String wardcode;
  @Column(name = "districtcode")
  int districtcode;
  @Column(name = "fulladdress")
  String fulladdress;
  @Column(name = "status")
  int status;
}
