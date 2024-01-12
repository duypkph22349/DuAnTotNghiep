package datn.goodboy.controller.client;

import datn.goodboy.service.client.CartClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/cart")
public class CartClientRestController {

    @Autowired
    private CartClientService cartClientService;

    @GetMapping("/quantity")
    private ResponseEntity<?> getQuantity(){
        try {
            return ResponseEntity.ok(cartClientService.getQuantityByIDCustomer());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find-voucher-by-code")
    public ResponseEntity<?> findVoucherByCode(@RequestParam("code") String code) {
        try{
            return ResponseEntity.ok(cartClientService.findVoucherByCode(code));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
