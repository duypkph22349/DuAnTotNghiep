package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@Table(name = "promotion_detail")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PromotionDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "id_product_detail")
  private ProductDetail productDetail;

  @ManyToOne
  @JoinColumn(name = "id_promotion")
  @JsonIgnore
  private Promotion promotion;

  @Column(name = "price")
  private Double price;

  @Column(name = "price_after_promotion")
  private Double price_after_promotion;
  @Column(name = "created_at")
  LocalDateTime created_at;
  @Column(name = "update_at")
  LocalDateTime updated_at;
  @Column(name = "deleted")
  boolean deleted;

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
