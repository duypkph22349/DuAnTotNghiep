package datn.goodboy.model.response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    UUID id;
    String roles;
    String code;
    String name;
    boolean gender;
    LocalDateTime birth_date;
    String phone;
    String email;
    int status;
    String cccd;
    String image;
    String password;
    String country;
    String city;
    String fulladdress;
    boolean deleted;

}
