package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="image")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_product_detail")
    private ProductDetail idSP;

    @Column(name="image")
    private String img;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="update_at")
    private LocalDateTime updatedAt;
    @Column(name="status")
    private int status;
    @Column(name="deleted")
    private boolean deleted;

}