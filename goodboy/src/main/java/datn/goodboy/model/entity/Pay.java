package datn.goodboy.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
    String payment_method;

    @Column(name = "status")
    int status;

    @Column(name = "created_at")
    LocalDateTime created_at;

    @Column(name = "update_at")
    LocalDateTime update_at;

    @Column(name = "deleted")
    int deleted;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
        this.update_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.update_at = LocalDateTime.now();
    }
}
