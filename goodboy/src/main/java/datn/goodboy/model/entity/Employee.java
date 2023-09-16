package datn.goodboy.model.entity;

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

    @Column(name = "id_roles")
    int id;

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @Column(name = "gender")
    boolean gender;

    @Column(name = "birth_date")
    LocalDateTime birth_date;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phone;

    @Column(name = "status")
    int status;

    @Column(name = "created_at")
    LocalDateTime created_at;

    @Column(name = "update_at")
    LocalDateTime update_at;

    @Column(name = "deleted")
    int deleted;






}
