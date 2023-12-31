package datn.goodboy.model.request;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.ImageProduct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductRequest {
  final String noImage = "http://res.cloudinary.com/da30qcqmf/image/upload/v1697981307/DUANTOTNGHIEP/d8157802-eeb2-417c-9dfe-9ba64f6bbd44.jpg";

  @Bean("productrequest")
  ProductRequest getNewRequest() {
    ProductRequest request = new ProductRequest();
    request.resetRequest();
    return request;
  }

  List<ImageProduct> images = new ArrayList<>();
  Integer id;
  @Min(value = 0, message = "Vui lòng chọn Origin")
  Integer idOrigin;
  @Min(value = 0, message = "Vui lòng chọn Brand")
  Integer idBrand;
  @Min(value = 0, message = "Vui lòng chọn Material")
  Integer idMaterial;
  @Min(value = 0, message = "Vui lòng chọn CategoryController")
  Integer idCategory;
  @Min(value = 0, message = "Vui lòng chọn Styles")
  Integer idStyles;
  @NotNull
  @NotBlank
  @Length(min = 5, message = "Tên không thể quá ngắn")
  String name;
  @NotNull
  Integer status;

  public void resetRequest() {
    id = -1;
    if (this.images != null) {
      this.images.clear();
    }
    this.idOrigin = -1;
    this.idBrand = -1;
    this.idMaterial = -1;
    this.idCategory = -1;
    this.idStyles = -1;
    this.name = "";
    this.status = 0;
  }
}
