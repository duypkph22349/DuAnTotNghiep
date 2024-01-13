package datn.goodboy.model.request;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import datn.goodboy.model.entity.ProductDetail;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class EvaluateRequest {
  Integer idbill;
  UUID idCustomer;
  List<EvaluateProduct> evaluateProducts;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  public static class EvaluateProduct {
    ProductDetail idproductdetails;
    @NotNull(message = "Vui lòng đánh giá")
    @Size(min = 1, max = 5, message = "Vui lòng đánh giá")
    Integer rating;
    String discription;
  }
}
