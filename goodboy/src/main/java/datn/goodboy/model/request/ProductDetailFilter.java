package datn.goodboy.model.request;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductDetailFilter {
  int idProduct;
  int idPattern;
  int idColor;
  int idOrigin;
  int idBrand;
  int idMaterial;
  int idSize;
  int idStyles;
}
