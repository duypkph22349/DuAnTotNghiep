package datn.goodboy.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/vnpay")
public class VnpayController {

    @GetMapping("/payment-success")
    public String paymentSuccess(@RequestParam("vnp_TransactionStatus") String status) {
        if(status.equals("00")){
            return "user2/payment_success";
        }else{
            return "user2/payment_fail";
        }
    }
}
