package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
  @Column(name = "birth_date")
  LocalDateTime birth_date;
  @Column(name = "address")
  String address;
  @Column(name = "phone")
  String phone;
  @Column(name = "city")
  String city;
  @Column(name = "country")
  String country;
  @Column(name = "status")
  int status;
  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Account account;
}
