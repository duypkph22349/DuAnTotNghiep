package datn.goodboy.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
<<<<<<< HEAD
    @Id
=======
@Id
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;

    @OneToOne
    @JoinColumn(name = "id_roles" )
    private Roles roles;

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

<<<<<<< HEAD
    @Column(name = "cccd")
    String cccd;
=======
    // @Column(name = "cccd")
    // String cccd;
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f

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
