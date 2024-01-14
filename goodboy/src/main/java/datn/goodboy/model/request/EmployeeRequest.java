package datn.goodboy.model.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import datn.goodboy.model.entity.Roles;
import jakarta.validation.constraints.*;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
@ToString
public class EmployeeRequest {

    UUID id;

    private Roles roles;

    String image;

    @NotNull(message = "Hãy nhập Email")
    @NotBlank(message = "Hãy nhập Email")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email Định dạng không đúng")
    String email;

    @NotNull(message = "Trường này không thể để trống")
    @NotBlank(message = "Trường này không thể để trống")
    String name;

    boolean gender;

    @NotNull(message = "Trường này không thể để trống")
    @NotBlank(message = "Hãy nhập số điện thoại")
    @Pattern(regexp = "0\\d{9}", message = "Số điện thoại phải bắt đầu bằng số 0 và phải đủ 10 số")
    String phone;

    @NotNull(message = "Trường này không thể để trống")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate birth_date;

    @NotNull(message = "Trường này không thể để trống")
    @Pattern(regexp = "\\d{12}", message = "Căn cước công dân phải có 12 số")
    String cccd;

//    @NotBlank(message = "Vui lòng nhập trường này!")
//    @NotNull(message = "Vui lòng nhập trường này!")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
//    String password;
////
////    @NotBlank(message = "Vui lòng nhập trường này!")
////    @NotNull(message = "Vui lòng nhập trường này!")
////    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "Mật khẩu từ 6 - 8 kí tự và phải chứa 1 kí tự số và 1 ký tự viết hoa")
////    String passwordRepeat;


    String address;

    String city;

    String country;

    String fulladdress;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean deleted;
    int status;
    private boolean actived;

}