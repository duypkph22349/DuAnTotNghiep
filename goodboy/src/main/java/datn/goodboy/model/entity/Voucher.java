package datn.goodboy.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int status;
  private String code;
  private String name;
  private LocalDateTime start_time;
  private LocalDateTime end_time;
  private int quantily;
  private float discount;

}
