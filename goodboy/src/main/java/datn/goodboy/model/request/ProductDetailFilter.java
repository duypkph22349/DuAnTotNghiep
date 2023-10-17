package datn.goodboy.model.request;

import org.springframework.stereotype.Component;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ProductDetailFilter {
  String txtSearch;
  int idProduct;
  int idPattern;
  int idColor;
  int idOrigin;
  int idBrand;
  int idMaterial;
  int idSize;
  int idStyles;
}
