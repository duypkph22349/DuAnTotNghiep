package datn.goodboy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@Table(name = "bill_detail")
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JsonIgnore
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
