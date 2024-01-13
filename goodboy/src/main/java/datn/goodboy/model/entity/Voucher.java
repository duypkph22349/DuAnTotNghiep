package datn.goodboy.model.entity;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@Table(name = "voucher")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Voucher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "status")
  private int status;
  @Column(name = "code", insertable = false, updatable = false)
  private String code;
  @Column(name = "name")
  private String name;
  @Column(name = "start_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime start_time;
  @Column(name = "end_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime end_time;
  @Column(name = "quantily")
  private int quantily;
  @Column(name = "discount")
  private float discount;
  @Column(name = "created_at")
  LocalDateTime created_at;
  @Column(name = "update_at")
  LocalDateTime updated_at;
  @Column(name = "types")
  boolean types;
  @Column(name = "deleted")
  boolean deleted;
  @Column(name = "max_discount")
  Double max_discount;
  @Column(name = "min_order")
  Double min_order;

  @PrePersist
  protected void onCreate() {
    this.created_at = LocalDateTime.now();
    this.updated_at = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = LocalDateTime.now();
  }

  public String getDiscountValue() {
    String message = "";
    if (types) {
      message += "Giảm " + discount + " %";
    } else {
      NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
      format.setCurrency(Currency.getInstance("VND"));
      String formattedPrice = format.format(discount);
      message += "Giảm " + formattedPrice;
    }
    if (max_discount != null) {
      NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
      format.setCurrency(Currency.getInstance("VND"));
      String formattedPrice = format.format(max_discount);
      message += " - Tối đa " + formattedPrice;
    }
    return message;
  }

  public String getConditionVoucher() {
    String message = "";
    if (min_order != null) {
      NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
      format.setCurrency(Currency.getInstance("VND"));
      String formattedPrice = format.format(min_order);
      message += "Áp dụng với Hóa đơn có giá trị từ: " + formattedPrice;
    }
    return message;
  }

  public boolean sendMail() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    return !this.isDeleted()
        && (this.getStart_time().isBefore(currentDateTime) || this.getStart_time().isAfter(currentDateTime))
        && (this.getEnd_time() == null || this.getEnd_time().isAfter(currentDateTime))
        && (this.getStart_time().isBefore(this.getEnd_time()))
        && this.getStatus() == 1
        && this.getQuantily() > 0;
  }

  public boolean checkVoucher() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    return !this.isDeleted()
        && this.getStart_time().isBefore(currentDateTime)
        && (this.getEnd_time() == null || this.getEnd_time().isAfter(currentDateTime))
        && this.getStatus() == 1
        && this.getQuantily() > 0;
  }
}
