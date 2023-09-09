package datn.goodboy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Data
@Getter
@Setter
@Table(name = "VoucherDetail")
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @OneToMany
  @JoinColumn(name = "id_bill")
  private Bill bill;
  @OneToMany
  @JoinColumn(name = "id_voucher")
  private Voucher voucher;
  @Column(name = "money_before_reduction")
  private float money_before_reduction;
  @Column(name = "money_after_reduction")
  private float money_after_reduction;
  @Column(name = "money_reduction")
  private float money_reduction;
  @Column(name = "status")
  private int status;
}
