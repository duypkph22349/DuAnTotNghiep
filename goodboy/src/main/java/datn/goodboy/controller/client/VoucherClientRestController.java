package datn.goodboy.controller.client;

import datn.goodboy.service.client.VoucherClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/voucher")
public class VoucherClientRestController {

    @Autowired
    private VoucherClientService voucherClientService;

    @GetMapping("/find-by-code")
    public ResponseEntity<?> findVoucherByCode(@RequestParam("code") String code) {
       try{
           return ResponseEntity.ok(voucherClientService.findVoucherByCode(code));
       }catch (Exception e){
//           System.out.println(e);
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
}
