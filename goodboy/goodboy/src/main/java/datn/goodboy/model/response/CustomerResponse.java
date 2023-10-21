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
@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
  UUID id;
  String code;
  String name;
  boolean gender;
  LocalDateTime birth_date;
  String phone;
  String address;
  String city;
  String country;
  int status;
}
