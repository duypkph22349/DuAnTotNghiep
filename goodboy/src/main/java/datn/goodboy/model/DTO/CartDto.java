package datn.goodboy.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String address;

    private String phone;

    private Integer totalQuantity = 0;

    private Double totalPrice = 0D;

    private HashMap<Integer, CartDetailDto> listDetail = new HashMap<>();
}
