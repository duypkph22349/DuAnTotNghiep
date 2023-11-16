package datn.goodboy.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pay_detail")
public class PayDetail {

  @EmbeddedId
  private PayDetailId id;

  @ManyToOne
  @MapsId("id_bill")
  @JoinColumn(name = "id_bill", nullable = false)
  private Bill bill;

  @ManyToOne
  @MapsId("id_pay")
  @JoinColumn(name = "id_pay", nullable = false)
  private Pay pay;

  @Column(name = "total_money", columnDefinition = "money default 0")
  private float totalMoney;

  @Column(name = "status", columnDefinition = "BIT default 0")
  private Boolean status;

}
