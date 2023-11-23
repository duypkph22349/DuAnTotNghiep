package datn.goodboy.model.response;

import java.math.BigDecimal;

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
  Long id_product_detail;
  String name;
  BigDecimal price;
  Integer totalQuantity; // Adjusted to total quantity
  BigDecimal totalPrice;
}
