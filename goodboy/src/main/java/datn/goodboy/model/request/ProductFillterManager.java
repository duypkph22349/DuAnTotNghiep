package datn.goodboy.model.request;

import org.springframework.context.annotation.Bean;
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
public class ProductFillterManager {
  @Bean("productfilltermanager")
  ProductFillterManager ProductFillterManager() {
    ProductFillterManager filter = new ProductFillterManager();
    filter.resetFilter();
    return filter;
  }

  String txtSearch;
  int idOrigin;
  int idBrand;
  int idMaterial;
  int idStyles;
  int idCategory;

  public boolean filterAble() {
    if (txtSearch == null) {
      return false;
    }
    if (!txtSearch.equals("")) {
      return true;
    }
    if (idCategory != -1) {
      return true;
    }
    if (idOrigin != -1) {
      return true;
    }
    if (idBrand != -1) {
      return true;
    }
    if (idMaterial != -1) {
      return true;
    }
    if (idStyles != -1) {
      return true;
    }
    return false;
  }

  public void resetFilter() {
    txtSearch = "";
    idOrigin = -1;
    idBrand = -1;
    idMaterial = -1;
    idCategory = -1;
    idStyles = -1;
  }
}
