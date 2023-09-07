package datn.goodboy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
  @Column(name = "id")
  private int id;
  @ManyToMany
  @JoinColumn(name = "id_bill")
  private int id_bill;
  @Column(name = "id_voucher")
  private int id_voucher;
  @Column(name = "money_before_reduction")
  private float money_before_reduction;
  @Column(name = "money_after_reduction")
  private float money_after_reduction;
  @Column(name = "money_reduction")
  private float money_reduction;
  @Column(name = "status")
  private int status;
}
