package datn.goodboy.model.response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductDetailResponse {
    int id;
    String code;
    String name;
    float price;
    int quantity;
    String description;
    String product;
    String pattern;
    String color;
    String origin;
    String brand;
    String material;
    String size;
    String styles;
    int status;
    boolean deleted;
}
