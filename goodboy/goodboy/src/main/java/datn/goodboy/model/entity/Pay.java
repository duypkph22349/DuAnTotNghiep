package datn.goodboy.model.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "pay")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "payment_method")
    int payment_method;

    @Column(name = "status")
    int status;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Column(name = "deleted")
    boolean deleted;

}
