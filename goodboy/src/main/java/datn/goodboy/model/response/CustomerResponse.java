package datn.goodboy.model.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CustomerResponse {
  UUID id;
  String code;
  String name;
  boolean gender;
  LocalDateTime birth_date;
  String phone;
  String fulladdress;
  int status;
}
