package datn.goodboy.model.request;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductAddRequest {

  @JsonProperty("name")
  @NotBlank(message = "Tên không thể trống")
  String name;

  @JsonProperty("idOrigin")
  @Min(value = 0, message = "nguồn gốc không hợp lệ")
  int idOrigin;

  @JsonProperty("idBrand")
  @Min(value = 0, message = "thương hiệu không hợp lệ")
  int idBrand;

  @JsonProperty("idMaterial")
  @Min(value = 0, message = "chất liệu không hợp lệ")
  int idMaterial;

  @JsonProperty("idCategory")
  @Min(value = 0, message = "danh mục không hợp lệ")
  int idCategory;

  @JsonProperty("idStyles")
  @Min(value = 0, message = "phong cách không hợp lệ")
  int idStyles;

  @JsonProperty("productDetails")
  List<ProductDetailAdd> productDetails;

  @JsonCreator
  public ProductAddRequest(
      @JsonProperty("productDetails") List<ProductDetailAdd> productDetails,
      @JsonProperty("name") String name,
      @JsonProperty("idOrigin") int idOrigin,
      @JsonProperty("idBrand") int idBrand,
      @JsonProperty("idMaterial") int idMaterial,
      @JsonProperty("idCategory") int idCategory,
      @JsonProperty("idStyles") int idStyles) {
    this.name = name;
    this.idOrigin = idOrigin;
    this.idBrand = idBrand;
    this.idMaterial = idMaterial;
    this.idCategory = idCategory;
    this.idStyles = idStyles;
    this.productDetails = productDetails;
  }

  public List<String> validateError() {
    Set<String> uniqueProductDetails = new HashSet<>();
    List<String> duplicateErrors = productDetails.stream()
        .map(productDetail -> {
          String key = productDetail.getIdPattern() + "-" + productDetail.getIdSize();
          return uniqueProductDetails.add(key) ? null
              : "Có sản phẩm chi tiết trùng lặp với idPattern và idSize giống nhau: " + key;
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    return duplicateErrors;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @ToString
  public static class ProductDetailAdd {
    @JsonProperty("price")
    @Min(value = 0, message = "Giá phải lớn hơn hoặc bằng 0")
    float price;

    @JsonProperty("quantity")
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    int quantity;

    @JsonProperty("idSize")
    @Min(value = 0, message = "kích thước không hợp lệ")
    int idSize;

    @JsonProperty("idPattern")
    @Min(value = 0, message = "mẫu không hợp lệ")
    int idPattern;

    String description;

    @JsonCreator
    public ProductDetailAdd(
        @JsonProperty("price") float price,
        @JsonProperty("quantity") int quantity,
        @JsonProperty("idSize") int idSize,
        @JsonProperty("idPattern") int idPattern,
        @JsonProperty("description") String description) {
      this.price = price;
      this.quantity = quantity;
      this.idSize = idSize;
      this.idPattern = idPattern;
      this.description = description;
    }
  }
}
