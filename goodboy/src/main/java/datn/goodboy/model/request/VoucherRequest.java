package datn.goodboy.model.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import datn.goodboy.validate.voucher.StartBeforeEnd;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
public class VoucherRequest {
  int id;
  @NotNull
  @NotBlank
  String name;
  @NotNull
  LocalDateTime start_time;
  @NotNull
  LocalDateTime end_time;
  @NotNull
  @Min(0)
  int quantity;
  @NotNull
  @Min(0)
  float discount;
  @NotNull
  int status;
}
