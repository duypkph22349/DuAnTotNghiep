package datn.goodboy.model.entity;

import java.util.UUID;

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
public class AccountVoucher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private UUID id_account;
  private UUID id_voucher;
  private int status;

}
