package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "code", insertable = false, updatable = false)
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

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
