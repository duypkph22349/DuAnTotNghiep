package datn.goodboy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "code", insertable = false, updatable = false)
    String code;

    @OneToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    private static List<ProductDetail> cartItems = new ArrayList<>();

    public static void addToCart(ProductDetail productDetail) {
        cartItems.add(productDetail);
    }

    public static List<ProductDetail> getCartItems() {
        return cartItems;
    }

    @OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST)
    @ToString.Include
    public List<CartDetail> cartDetails;

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

}
