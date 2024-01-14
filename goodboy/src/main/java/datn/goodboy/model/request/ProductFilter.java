package datn.goodboy.model.request;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductFilter {

  @JsonProperty("pageno")
  private int pageno;

  @JsonProperty("rowcount")
  private int rowcount;

  @JsonProperty("sortBy")
  private String sortBy;

  @JsonProperty("sortDir")
  private boolean sortDir;

  String txtSearch;
  int[] idOrigins;
  int[] idBrands;
  int[] idMaterials;
  int[] idStyless;
  int[] idCategorys;

  @Bean(name = "filterproductnew")
  ProductFilter getProductDetailNew() {
    ProductFilter filter = new ProductFilter();
    filter.resetFilter();
    return filter;
  }

  @JsonCreator
  public ProductFilter(
      @JsonProperty("idOrigins") int[] idOrigins,
      @JsonProperty("idBrands") int[] idBrands,
      @JsonProperty("idMaterials") int[] idMaterials,
      @JsonProperty("txtSearch") String txtSearch,
      @JsonProperty("idStyless") int[] idStyless,
      @JsonProperty("idCategorys") int[] idCategorys,
      @JsonProperty("pageno") int pageno,
      @JsonProperty("rowcount") int rowcount,
      @JsonProperty("sortBy") String sortBy,
      @JsonProperty("sortDir") boolean sortDir) {
    this.pageno = pageno;
    this.rowcount = rowcount;
    this.sortBy = sortBy;
    this.sortDir = sortDir;
    this.txtSearch = (txtSearch == null) ? "" : txtSearch;
    this.idStyless = (idStyless == null) ? new int[] { -1 } : idStyless;
    this.idOrigins = (idOrigins == null) ? new int[] { -1 } : idOrigins;
    this.idBrands = (idBrands == null) ? new int[] { -1 } : idBrands;
    this.idMaterials = (idMaterials == null) ? new int[] { -1 } : idMaterials;
    this.idCategorys = (idCategorys == null) ? new int[] { -1 } : idCategorys;
  }

  public void resetFilter() {
    txtSearch = "";
    idCategorys = new int[] { -1 };
    idOrigins = new int[] { -1 };
    idBrands = new int[] { -1 };
    idMaterials = new int[] { -1 };
    idStyless = new int[] { -1 };
  }

  public boolean filterAble() {
    return false;
  }
}
