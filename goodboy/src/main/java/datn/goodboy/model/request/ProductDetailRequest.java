package datn.goodboy.model.request;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.Images;
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
public class ProductDetailRequest {
  final String noImage = "http://res.cloudinary.com/da30qcqmf/image/upload/v1697981307/DUANTOTNGHIEP/d8157802-eeb2-417c-9dfe-9ba64f6bbd44.jpg";

  @Bean("newrequest")
  public ProductDetailRequest getNewRequest() {
    ProductDetailRequest request = new ProductDetailRequest();
    request.resetRequest();
    return request;
  }

  List<Images> image = new ArrayList<>();
  Integer id;
  @Min(value = 0, message = "Vui lòng chọn")
  Integer idProduct;
  @Min(value = 0, message = "Vui lòng chọn")
  Integer idPattern;
  @Min(value = 0, message = "Vui lòng chọn")
  Integer idSize;

  String description;
  @NotNull(message = "Trường này không thể bỏ qua")
  boolean deleted;
  @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng  0")
  int quantity;
  @Min(value = 0, message = "Giá phải lớn hơn hoặc bằng 0")
  Float price;
  // @NotNull
  // @NotBlank
  // @Length(min = 5, message = "Tên không thể quá ngắn")
  String name;
  @NotNull
  int status;

  public void resetRequest() {
    int id = -1;
    if (this.image != null) {
      this.image.clear();
    }
    this.idProduct = -1;
    this.idPattern = -1;
    this.idSize = -1;
    this.quantity = 0;
    this.price = 0f;
    this.name = "";
    this.status = 0;
  }
}
