package datn.goodboy.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
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
public class PromotionRequest {

  UUID id;

  @NotNull(message = "trường này không thể để trống")
  // @FutureOrPresent(message = "Ngày bắt đầu không được ở trong quá khứ")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  LocalDateTime startTime;
  @NotNull(message = "trường này không thể để trống")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  LocalDateTime endTime;

  @NotNull(message = "trường này không thể để trống")
  @Min(value = 0, message = "value không thể nhỏ hơn 0 ")
  Double value;
  @NotNull(message = "trường này không thể để trống")
  int status;

  public String ValidateError() {
    String errors = "";
    if (startTime.isAfter(endTime)) {
      errors += "Thời gian kết thúc phải sau thời gian bắt đầu \n";
    }
    return errors;
  }

  public boolean validateHasError() {
    if (startTime.isAfter(endTime)) {
      return true;
    }
    return false;
  }
}
