package datn.goodboy.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @OneToMany(mappedBy = "idProduct") // Define the relationship with Images
    @JsonIgnore
    private List<ImageProduct> imageProducts;
    @OneToMany(mappedBy = "idProduct") // Define the relationship with Images
    @JsonIgnore
    private List<ProductDetail> productDetails;

    public Float getMinPrice() {
        Optional<ProductDetail> minPrice = productDetails.stream()
                .min((val1, val2) -> {
                    return val1.getPrice().compareTo(val2.getPrice());
                });
        return minPrice.get().getPrice();
    }

    public Float getMaxPrice() {
        Optional<ProductDetail> maxPrice = productDetails.stream()
                .max((val1, val2) -> {
                    return val1.getPrice().compareTo(val2.getPrice());
                });
        return maxPrice.get().getPrice();
    }

    public List<List<ProductDetail>> getListOptionProduct() {
        List<List<ProductDetail>> options = ColerOn().stream()
                .map(color -> productDetails.stream()
                        .filter(p -> p.getIdColor().equals(color))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        return options;
    }

    public List<Color> ColerOn() {
        List<Color> colors = productDetails.stream()
                .map(ProductDetail::getIdColor)
                .distinct()
                .collect(Collectors.toList());
        return colors;
    }

}
