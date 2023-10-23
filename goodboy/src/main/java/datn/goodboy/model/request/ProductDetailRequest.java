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
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductDetailRequest {
  final String noImage = "http://res.cloudinary.com/da30qcqmf/image/upload/v1697981307/DUANTOTNGHIEP/d8157802-eeb2-417c-9dfe-9ba64f6bbd44.jpg";

  @Bean("newrequest")
  public ProductDetailRequest getNewRequest() {
    ProductDetailRequest request = new ProductDetailRequest();
    request.resetRequest();
    return request;
  }

  String image;
  int id;
  int idProduct;
  int idPattern;
  int idColor;
  int idOrigin;
  int idBrand;
  int idMaterial;
  int idSize;
  int idStyles;
  String description;
  boolean deleted;
  int quantity;
  Float price;
  String name;
  int status;

  public void resetRequest() {
    int id = -1;
    this.image = noImage;
    this.idProduct = -1;
    this.idPattern = -1;
    this.idColor = -1;
    this.idOrigin = -1;
    this.idBrand = -1;
    this.idMaterial = -1;
    this.idSize = -1;
    this.idStyles = -1;
    this.quantity = 0;
    this.price = 0f;
    this.name = "";
    this.status = 0;
  }
}
