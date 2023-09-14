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

    @ManyToOne
    @JoinColumn(name = "id_cart" )
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "id_product_detail" )
    private ProductDetail productDetail;
    
    @Column(name = "status")
    private int status;
}
