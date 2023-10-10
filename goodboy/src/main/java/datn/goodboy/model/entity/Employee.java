package datn.goodboy.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @ManyToOne
    @JoinColumn(name = "id_roles" )
    private Roles roles;

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @Column(name = "cccd")
    String cccd;

    @Column(name = "gender")
    boolean gender;

    @Column(name = "birth_date")
    Date birth_date;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "status")
    int status;

    @Column(name = "image")
    String image;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="update_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted")
    private boolean deleted;
}
