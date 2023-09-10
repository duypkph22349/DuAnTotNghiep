package datn.goodboy.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "account_voucher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountVoucher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "id_account")
  private UUID id_account;
  @Column(name = "id_voucher")
  private UUID id_voucher;
  @Column(name = "status")
  private int status;
}
