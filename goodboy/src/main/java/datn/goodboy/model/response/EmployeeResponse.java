package datn.goodboy.model.response;

import datn.goodboy.model.entity.Roles;
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
public class EmployeeResponse {
    UUID id;

    private Roles roles;

    String code;

    String name;

    String cccd;

    boolean gender;

    LocalDateTime birth_date;

    String address;

    String phone;

    String email;

    String password;

    int status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    boolean deleted;
}
