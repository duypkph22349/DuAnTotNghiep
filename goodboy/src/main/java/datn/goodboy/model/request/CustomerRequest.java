package datn.goodboy.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
@ToString
public class CustomerRequest {
  UUID id;

  @NotNull(message = "Trường này không thể để trống")
  @NotBlank(message = "Trường này không thể để trống")
  String name;
  boolean gender;

  @NotNull(message = "Trường này không thể để trống")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  LocalDate birth_date;


  @NotNull(message = "Số định dạng không thể được trồng")
  @NotBlank(message = "Hãy nhập số điện thoại")
  @Pattern(regexp = "0\\d{9}", message = "Số điện thoại phải bắt đầu bằng số 0 và phải đủ 10 số")
  String phone;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  boolean deleted;
  int status;
}
