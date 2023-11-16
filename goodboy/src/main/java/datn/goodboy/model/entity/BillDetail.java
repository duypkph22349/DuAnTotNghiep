package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    @JoinColumn(name = "id_product_detail")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill idBill;

    @Column(name = "total_money")
    Double totalMoney;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "status")
    int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    boolean deleted;
}
