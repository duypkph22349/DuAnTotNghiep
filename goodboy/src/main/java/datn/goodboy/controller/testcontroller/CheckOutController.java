package datn.goodboy.controller.testcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import datn.goodboy.model.entity.Bill;
import datn.goodboy.service.test.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController("testCheckoutcontroler")
@RequestMapping("shop/checkout")
public class CheckOutController {
  @Autowired
  private CartService cartService;

  @GetMapping("pay")
  public Bill checkout(@RequestParam("carts") List<Integer> cartDetails) {
    return cartService.getCheckOutPage(cartDetails);
  }
}
