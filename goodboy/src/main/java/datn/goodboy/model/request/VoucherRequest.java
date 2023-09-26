package datn.goodboy.model.request;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

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
  int status;
  String name;
  LocalDateTime start_time;
  LocalDateTime end_time;
  int quantily;
  float discount;
}
