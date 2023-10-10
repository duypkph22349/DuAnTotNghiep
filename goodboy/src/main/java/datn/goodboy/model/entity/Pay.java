package datn.goodboy.model.entity;

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
    LocalDateTime created_at;

    @Column(name = "update_at")
    LocalDateTime update_at;

    @Column(name = "deleted")
    int deleted;

}
