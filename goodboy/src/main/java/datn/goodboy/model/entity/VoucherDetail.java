package datn.goodboy.model.entity;

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
public class VoucherDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int id_bill;
  private int id_voucher;
  private float money_before_reduction;
  private float money_after_reduction;
  private float money_reduction;
  private int status;

}
