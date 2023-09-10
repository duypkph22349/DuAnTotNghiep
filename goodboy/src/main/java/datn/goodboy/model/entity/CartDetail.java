package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "CartDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToMany
    @JoinColumn(name = "id_cart")
    private Cart id_cart;
    @OneToMany
    @JoinColumn(name = "id_product_detail")
    private ProductDetail id_product_detail;
    @Column(name = "status")
    private int status;
}
