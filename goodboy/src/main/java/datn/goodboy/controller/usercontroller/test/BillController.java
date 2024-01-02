package datn.goodboy.controller.usercontroller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.service.test.BillService;

@Controller("testBillController")
@RequestMapping("/bill/test")
public class BillController {
  @Autowired
  BillService billService;

  @GetMapping("/checkout")
  public String viewCheckout(Model model, @RequestParam("carts") List<Integer> cartDetails) {
    Bill bill = billService.getCheckOutPage(cartDetails);
    model.addAttribute("bill", bill);
    return "user2/checkout";
  }

  @GetMapping("/history")
  public String viewHistoryBill(Model model) {
    Customer customer = billService.getCustomer();
      model.addAttribute("bills", customer.getBills());
    return "user2/userbills";
  }
}
