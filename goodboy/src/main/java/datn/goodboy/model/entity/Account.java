package datn.goodboy.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@Table(name = "Account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  @OneToOne
  @JoinColumn(name = "id")
  private Customer customer;
  @Column(name = "code")
  private String code;
  @Column(name = "name")
  private String name;
  @Column(name = "birth_date")
  private LocalDate birth_date;
  @Column(name = "phone")
  private String phone;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
  private Employee employee;

}
