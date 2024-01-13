package datn.goodboy.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;

    @OneToOne
    @JoinColumn(name = "id_roles")
    private Roles roles;

    @Column(name = "code", insertable = false, updatable = false)
    String code;

    @Column(name = "name")
    // @NotNull(message = "Trường này không thể để trống")
    // @NotBlank(message = "Hãy nhập họ và tên")
    String name;

    @Column(name = "gender")
    boolean gender;

    @Column(name = "birth_date")
    // @NotNull(message = "Trường này không thể để trống")
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birth_date;

    @Column(name = "address")
    String address;
    @Column(name = "districtcode")
    String city;
    @Column(name = "wardcode")
    String country;
    @Column(name = "fulladdress")
    String fulladdress;

    @Column(name = "phone")
    // @NotNull(message = "Trường này không thể để trống")
    // @NotBlank(message = "Hãy nhập số điện thoại")
    // @Pattern(regexp = "0\\d{9}", message = "Số điện thoại phải bắt đầu bằng số 0 và phải đủ 10 số")
    String phone;

    @Column(name = "email")
    // @NotNull(message = "Trường này không thể để trống")
    // @NotBlank(message = "Hãy nhập Email")
    // @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
    //         + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email Định dạng không đúng")
    String email;

    @Column(name = "cccd")
    // @NotNull(message = "Trường này không thể để trống")
    // @Pattern(regexp = "\\d{12}", message = "Căn cước công dân phải có 12 số")
    String cccd;

    @Column(name = "image")
    String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "status")
    int status;
    @Column(name = "actived")
    private boolean actived;

    @Column(name = "password")
//    @NotNull(message = "Hãy nhập Mật Khẩu")
//    @NotBlank(message = "Hãy nhập Mật Khẩu")
//    @Size(min = 8, max = 25, message = "Mật khẩu từ 8 đến 25 ký tự ")
    String password;

}
