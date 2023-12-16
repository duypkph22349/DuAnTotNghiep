package datn.goodboy.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
  String name;

  @JsonProperty("idOrigin")
  int idOrigin;

  @JsonProperty("idBrand")
  int idBrand;

  @JsonProperty("idMaterial")
  int idMaterial;

  @JsonProperty("idCategory")
  int idCategory;

  @JsonProperty("idStyles")
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

  @Getter
  @Setter
  @NoArgsConstructor
  @ToString
  public static class ProductDetailAdd {
    @JsonProperty("price")
    float price;
    @JsonProperty("quantity")

    int quantity;
    @JsonProperty("idSize")

    int idSize;
    @JsonProperty("idPattern")

    int idPattern;
    @JsonProperty("description")

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
