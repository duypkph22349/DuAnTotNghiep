package datn.goodboy.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "product_detail")
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonIgnore
    @JoinColumn(name = "id_product")
    @ToString.Exclude
    private Product idProduct;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size idSize;

    @ManyToOne
    @JoinColumn(name = "id_pattern")
    private PatternType idPattern;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "idProductDetail", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonProperty("imageProducts")
    private List<Images> imageProducts = new ArrayList<>();

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonProperty("evaluates")
    private List<Evaluate> evaluates = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public boolean ableToSales() {
        if (this.getQuantity() <= 0) {
            return false;
        }
        if (this.isDeleted()) {
            return false;
        }
        if (this.getStatus() != 1) {
            return false;
        }
        return true;
    }

    @JsonProperty("firstImage")
    public String getFirstImage() {
        if (imageProducts != null && !imageProducts.isEmpty()) {
            return imageProducts.get(0).getImg();
        } else if (idProduct != null && idProduct.getImageProducts() != null
                && !idProduct.getImageProducts().isEmpty()) {
            return idProduct.getImageProducts().get(0).getImg();
        } else {
            return null;
        }
    }

    @JsonProperty("nameProduct")
    public String getNameProduct() {
        if (this.name == null) {
            return idProduct.getName();
        }
        if (this.name.equals("")) {
            return idProduct.getName();
        } else {
            return this.name;
        }
    }

    @JsonProperty("fullnameProduct")
    public String getFullNameProduct() {
        if (this.name == null) {
            return idProduct.getName() + " - " + this.getIdPattern().getName() + " - " + this.getIdSize().getName();
        }
        if (this.name.equals("")) {
            return idProduct.getName() + " - " + this.getIdPattern().getName() + " - " + this.getIdSize().getName();
        } else {
            return this.name + " - " + this.getIdPattern().getName() + " - " + this.getIdSize().getName();
        }
    }

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
