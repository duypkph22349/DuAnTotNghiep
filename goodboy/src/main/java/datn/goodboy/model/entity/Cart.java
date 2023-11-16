package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "code")
    String code;

    @ManyToOne
    @JoinColumn(name = "id_customer" )
    private Customer customer;

    @Column(name = "status")
    private int status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted")
    private boolean deleted;

    private static List<ProductDetail> cartItems = new ArrayList<>();

    public static void addToCart(ProductDetail productDetail) {
        cartItems.add(productDetail);
    }

    public static List<ProductDetail> getCartItems() {
        return cartItems;
    }



}
