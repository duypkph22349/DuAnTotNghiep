package datn.goodboy.model.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    int id;
    String code;
    UUID idCustomer;
    LocalDateTime start_time;
    LocalDateTime end_time;
    int quantily;
    float discount;
    int status;
}
