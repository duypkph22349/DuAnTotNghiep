package datn.goodboy.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayDetailId {
  @Column(name = "id_bill")
  int id_bill;

  @Column(name = "id_pay")
  int id_pay;

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof PayDetailId))
      return false;
    PayDetailId other = (PayDetailId) obj;
    return Objects.equals(id_bill, other.id_bill) && Objects.equals(id_pay, other.id_pay);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_bill, id_pay);
  }
}
