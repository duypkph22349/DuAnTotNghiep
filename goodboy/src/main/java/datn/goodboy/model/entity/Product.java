package datn.goodboy.model.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="code")
    private String code;
    @NotNull
    @NotBlank
    @Column(name="name")
    private String name;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="status")
    private int status;
    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "idProduct") // Define the relationship with Images
    @JsonIgnore
    private List<ImageProduct> imageProducts;
}
