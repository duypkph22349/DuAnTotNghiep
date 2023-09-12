package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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
    @OneToMany(mappedBy = "id_cart" )
    private List<Cart> carts;
    @OneToMany(mappedBy = "id_product_detail" )
    private List<ProductDetail> productDetails;
    @Column(name = "status")
    private int status;
}
