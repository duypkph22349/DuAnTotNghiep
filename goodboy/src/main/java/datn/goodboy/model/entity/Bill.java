package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  int id;

  @ManyToOne
  @JoinColumn(name = "id_customer")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "id_employee")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "id_pay")
  private Pay pay;

  @Column(name = "code")
  String code;

  @Column(name = "confirmation_date")
  LocalDateTime confirmation_date;

  @Column(name = "delivery_date")
  LocalDateTime delivery_date;

  @Column(name = "received_date")
  LocalDateTime received_date;

  @Column(name = "completion_date")
  LocalDateTime completion_date;

  @Column(name = "customer_name")
  String customer_name;

  @Column(name = "phone")
  String phone;

  @Column(name = "address")
  String address;

  @Column(name = "money_ship")
  float money_ship;

  @Column(name = "total_money")
  Double total_money;

  @Column(name = "reduction_amount")
  float reduction_amount;

  @Column(name = "deposit")
  float deposit;

  @Column(name = "note")
  String note;

  @Column(name = "status")
  int status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "deleted")
  boolean deleted;

  @Column(name = "order_type")
  int loaiDon;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  @OneToMany(mappedBy = "idBill")
  @JsonIgnore
  private List<BillDetail> billDetail;

}
