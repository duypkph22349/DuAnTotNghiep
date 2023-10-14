package datn.goodboy.model.entity;

import java.time.LocalDateTime;

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
import lombok.ToString;
import lombok.AllArgsConstructor;

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
  @Column(name = "code")
  private String code;
  @Column(name = "name")
  private String name;
  @Column(name = "start_time")
  private LocalDateTime start_time;
  @Column(name = "end_time")
  private LocalDateTime end_time;
  @Column(name = "quantily")
  private int quantily;
  @Column(name = "discount")
  private float discount;
  @Column(name = "created_at")
  LocalDateTime created_at;
  @Column(name = "update_at")
  LocalDateTime update_at;
  @Column(name = "types")
  boolean types;
  @Column(name = "deleted")
  boolean deleted;
  @Column(name = "max_discount")
  Double max_discount;
  @Column(name = "min_order")
  Double min_order;
}
