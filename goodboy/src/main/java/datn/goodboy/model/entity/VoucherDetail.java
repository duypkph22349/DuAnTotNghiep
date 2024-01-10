package datn.goodboy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "voucher_detail")
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @OneToOne
  @JoinColumn(name = "id_bill")
  private Bill bill;
  @OneToOne
  @JoinColumn(name = "id_voucher")
  private Voucher voucher;
  @Column(name = "money_before_reduction")
  private Double money_before_reduction;
  @Column(name = "money_after_reduction")
  private Double money_after_reduction;
  @Column(name = "money_reduction")
  private Double money_reduction;
  @Column(name = "status")
  private int status;

  @PrePersist
  protected void onCreate() {
    this.money_before_reduction = bill.getTotal_money();
    if (voucher.types) {
      Double discount = bill.getTotal_money() * voucher.getDiscount();
      if (discount < voucher.getMax_discount()) {
        this.money_after_reduction = bill.getTotal_money() - discount;
        this.money_reduction = discount;
      } else {
        this.money_after_reduction = bill.getTotal_money() - voucher.getMax_discount();
        this.money_reduction = voucher.getMax_discount();
      }
    } else {
      Double discount = (double) voucher.getDiscount();
      if (discount < voucher.getMax_discount()) {
        this.money_after_reduction = bill.getTotal_money() - discount;
        this.money_reduction = discount;
      } else {
        this.money_after_reduction = bill.getTotal_money() - voucher.getMax_discount();
        this.money_reduction = voucher.getMax_discount();
      }
    }
  }
}
