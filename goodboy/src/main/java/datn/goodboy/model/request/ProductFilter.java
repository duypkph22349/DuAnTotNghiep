package datn.goodboy.model.request;

import java.util.ArrayList;
import java.util.List;

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
public class ProductFilter {
  @Bean("filterproductnew")
  ProductFilter getProductDetailNew() {
    ProductFilter filter = new ProductFilter();
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
    idCategory = -1;
    idOrigin = -1;
    idBrand = -1;
    idMaterial = -1;
    idStyles = -1;
  }

  public List<String> getFilterList() {
    List<String> result = new ArrayList<String>();

    if (idCategory != -1) {
      result.add("Category");
    }
    if (idOrigin != -1) {
      result.add("Origin");
    }
    if (idBrand != -1) {
      result.add("Brand");
    }
    if (idMaterial != -1) {
      result.add("Material");
    }
    if (idStyles != -1) {
      result.add("Styles");
    }

    return result;
  }
}
