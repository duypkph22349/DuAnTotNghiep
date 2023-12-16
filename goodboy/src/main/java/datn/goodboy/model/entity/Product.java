package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code", insertable = false, updatable = false)
    private String code;

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
    @JoinColumn(name = "id_category")
    private Category idCategory;

    @ManyToOne
    @JoinColumn(name = "id_style")
    private Styles idStyles;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "status")
    private int status;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "idProduct", cascade = CascadeType.ALL) // Define the relationship with Images
    @JsonIgnore
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private List<ImageProduct> imageProducts;
    @OneToMany(mappedBy = "idProduct", cascade = CascadeType.ALL) // Define the relationship with ProductDetail
    @JsonIgnore
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private List<ProductDetail> productDetails;

    @JsonIgnore

    public Float getMinPrice() {
        Optional<ProductDetail> minPrice = productDetails.stream()
                .min((val1, val2) -> {
                    return val1.getPrice().compareTo(val2.getPrice());
                });
        return minPrice.get().getPrice();
    }

    @JsonIgnore

    public Float getMaxPrice() {
        Optional<ProductDetail> maxPrice = productDetails.stream()
                .max((val1, val2) -> {
                    return val1.getPrice().compareTo(val2.getPrice());
                });
        return maxPrice.get().getPrice();
    }

    @JsonIgnore
    public List<List<ProductDetail>> getListOptionProduct() {
        List<List<ProductDetail>> options = patternTypes().stream()
                .map(color -> productDetails.stream()
                        .filter(p -> p.getIdPattern().equals(color))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        return options;
    }

    @JsonIgnore
    public List<PatternType> patternTypes() {
        List<PatternType> colors = productDetails.stream()
                .map(ProductDetail::getIdPattern)
                .distinct()
                .collect(Collectors.toList());
        return colors;
    }

}
