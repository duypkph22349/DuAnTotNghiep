package datn.goodboy.model.response;

import datn.goodboy.model.entity.Roles;
import groovy.transform.ToString;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeResponse {
    UUID id;
    Integer idRoles;
    String code;
    String name;
    boolean gender;
    LocalDateTime birth_date;
    String phone;
    String email;
    int status;
    String image;
}
