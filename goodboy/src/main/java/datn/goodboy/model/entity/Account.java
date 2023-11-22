package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "image")
  private String image;

  @Column(name = "status")
  private int status;

  @Column(name = "deleted")
  private boolean deleted;
  @Column(name = "actived")
  private boolean actived;

  @Column(name = "updated_at")
  LocalDateTime updated_at;

  @Column(name = "created_at")
  LocalDateTime created_at;
  @OneToOne
  @JoinColumn(name = "id_customer")
  private Customer customer;
}
