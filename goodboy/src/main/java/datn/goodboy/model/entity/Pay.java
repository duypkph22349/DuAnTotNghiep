package datn.goodboy.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
