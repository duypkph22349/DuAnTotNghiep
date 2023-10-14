package datn.goodboy.model.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

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
@AllArgsConstructor
@NoArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
@ToString
public class VoucherRequest {

  int id;

  @NotNull(message = "trường này không thể để trống")
  @NotBlank(message = "Trường này không thể để trống")
  String name;
  @NotNull(message = "trường này không thể để trống")
  // @FutureOrPresent(message = "Ngày bắt đầu không được ở trong quá khứ")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  LocalDateTime startTime;
  @NotNull(message = "trường này không thể để trống")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  LocalDateTime endTime;
  @NotNull(message = "trường này không thể để trống")
  @Min(0)
  int quantity;
  @NotNull(message = "trường này không thể để trống")
  @Min(value = 0, message = "discount không thể nhỏ hơn 0 ")
  float discount;
  @NotNull(message = "trường này không thể để trống")
  int status;
  @NotNull(message = "trường này không thể để trống")
  boolean types;
  // true are %
  // false are vnd;
  @NotNull(message = "trường này không thể để trống")
  @Min(value = 0, message = "max discount không thể nhỏ hơn 0 ")
  Double maxDiscount;
  @NotNull(message = "trường này không thể để trống")
  @Min(value = 0, message = "max discount không thể nhỏ hơn 0 ")
  Double minOrder;

  public String ValidateError() {
    String errors = "";
    if (startTime.isAfter(endTime)) {
      errors += "Thời gian kết thúc phải sau thời gian bắt đầu \n";
    }
    if (types) {
      if (discount > 100) {
        errors += "with type is % discount cannot greater than 100 % \n";
      }
    }
    if (maxDiscount > minOrder) {
      errors += "Min Order must greater than Max Discount";
    }
    return errors;
  }

  public boolean validateHasError() {
    if (startTime.isAfter(endTime)) {
      return true;
    }
    if (types) {
      if (discount > 100) {
        return true;
      }
    }
    if (maxDiscount > minOrder) {
      return true;
    }
    return false;
  }
}
