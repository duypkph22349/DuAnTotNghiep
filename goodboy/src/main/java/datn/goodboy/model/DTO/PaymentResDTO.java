package datn.goodboy.model.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentResDTO implements Serializable {
    private String status;
    private String message;
    private String url;
}
