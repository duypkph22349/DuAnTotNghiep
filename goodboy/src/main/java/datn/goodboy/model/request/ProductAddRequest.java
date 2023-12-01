package datn.goodboy.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddRequest {
  String name;

  int idOrigin;

  int idBrand;

  int idMaterial;

  int idCategory;

  int idStyles;

  List<ProductDetailAdd> productDetails;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public class ProductDetailAdd {
    float price;
    int quantity;
    int idSize;
    int idPattern;
    String description;
  }
}
