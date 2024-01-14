package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Getter
@Setter
@Table(name = "carts_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "id_cart")
    private Cart cart;

    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "id_product_detail")
    private ProductDetail productDetail;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = 1;
        this.deleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CartDetail that = (CartDetail) o;
        return Objects.equals(productDetail.getId(), that.productDetail.getId()) &&
                Objects.equals(cart.getId(), that.cart.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(productDetail.getId(), cart.getId());
    }

    @JsonProperty("totalMoeny")
    public float getTotalMoney() {
        return this.getQuantity() * this.getProductDetail().getPrice();
    }
}
