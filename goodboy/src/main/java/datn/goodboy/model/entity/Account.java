package datn.goodboy.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name="id")
  private UUID id;
  @Column(name="code")
  private String code;
  @Column(name="name")
  private String name;
  @Column(name="birth_date")
  private LocalDate birth_date;
  @Column(name="phone")
  private String phone;
  @Column(name="email")
  private String email;
  @Column(name="password")
  private String password;
}
