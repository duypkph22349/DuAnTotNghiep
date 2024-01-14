package datn.goodboy.controller.client;

import datn.goodboy.service.client.VnPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vnpay")
public class VnPayRestController {

    @Autowired
    private VnPayService vnPayService;

    @PostMapping("/payment")
    public String payment(@RequestParam("total")Integer total,
                          @RequestParam("orderInfor")String orderInfor,
                          @RequestParam("orderCode")String orderCode) {
        try{
            return vnPayService.createOrderClient(total, orderInfor, orderCode);
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

}
