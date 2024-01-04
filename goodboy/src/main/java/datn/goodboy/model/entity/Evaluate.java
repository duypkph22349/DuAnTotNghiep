package datn.goodboy.model.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluate")
public class Evaluate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "star")
  private int start;

  @Column(name = "description")
  private String description;

  @Column(name = "created_at")
  LocalDateTime created_at;

  @Column(name = "update_at")
  LocalDateTime updated_at;

  @Column(name = "deleted")
  boolean deleted;

  @ManyToOne
  @JoinColumn(name = "id_bill")
  @JsonIgnore
  @ToString.Exclude

  private Bill bill;

  @ManyToOne
  @JoinColumn(name = "id_customer")
  @JsonIgnore
  @ToString.Exclude

  private Customer customer;
  @ManyToOne
  @JoinColumn(name = "id_product_detail")
  @JsonIgnore
  @ToString.Exclude
  private ProductDetail productDetail;

  @PrePersist
  protected void onCreate() {
    this.created_at = LocalDateTime.now();
    this.updated_at = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = LocalDateTime.now();
  }
}
