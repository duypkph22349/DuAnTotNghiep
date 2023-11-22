package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "carts_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_cart" )
    private Cart cart;

    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "id_product_detail" )
    private ProductDetail productDetail;

    @Column(name = "status")
    private int status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted")
    private boolean deleted;

}
