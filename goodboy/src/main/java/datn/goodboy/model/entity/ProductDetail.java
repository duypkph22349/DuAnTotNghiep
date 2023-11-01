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

import groovy.transform.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_detail")
@ToString
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product idProduct;

    @ManyToOne
    @JoinColumn(name = "id_pattern")
    private PatternType idPattern;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color idColor;

    @ManyToOne
    @JoinColumn(name = "id_origin")
    private Origin idOrigin;

    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brand idBrand;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material idMaterial;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size idSize;

    @ManyToOne
    @JoinColumn(name = "id_styles")
    private Styles idStyles;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "idProductDetail") // Define the relationship with Images
    @JsonIgnore
    private List<Images> imageProducts;
}
