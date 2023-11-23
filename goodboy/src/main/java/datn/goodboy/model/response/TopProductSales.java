package datn.goodboy.model.response;

import datn.goodboy.model.entity.ProductDetail;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Data
@ToString
public class TopProductSales {
  ProductDetail productDetail;
  String name;
  int price;
  int totalQuantity; // Adjusted to total quantity
  float totalPrice;

  public TopProductSales(ProductDetail productDetail, String name, int price, int totalQuantity, float totalPrice) {
    this.productDetail = productDetail;
    this.name = name;
    this.price = price;
    this.totalQuantity = totalQuantity;
    this.totalPrice = totalPrice;
  }
}
