package datn.goodboy.controller.usercontroller.test;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.service.test.BillService;
import datn.goodboy.service.test.CartService;

@Controller("testBillController")
@RequestMapping("/shop/order")
public class BillController {
  @Autowired
  BillService billService;
  @Autowired
  CartService cartService;

  @GetMapping("/checkout")
  public String viewCheckout(Model model, @RequestParam("carts") List<Integer> cartDetails) {
    Bill bill = billService.getCheckOutPage(cartDetails);
    model.addAttribute("bill", bill);
    return "user2/checkout";
  }

  @GetMapping("/history")
  public String viewHistoryBill(Model model) {
    Customer customer = billService.getCustomer();
    model.addAttribute("customer", customer);
    return "user2/userbills";
  }

  @GetMapping("/cancel/{idbill}")
  public String cancelBill(Model model, @PathVariable("idbill") int bill) {
    billService.cancelInvoice(bill);
    return "redirect:history";
  }

  @GetMapping("/reorder/{idbill}")
  public String reorderBill(Model model, @PathVariable("idbill") int bill) {
    List<Integer> cartDetailIds = cartService.reorderBill(bill)
        .stream()
        .map(CartDetail::getId) // Assuming CartDetail has a method getId() to get the id
        .collect(Collectors.toList());
    System.out.println(cartDetailIds);
    model.addAttribute("cartchooes", cartDetailIds);
    Cart cart = cartService.getCart();
    model.addAttribute("cart", cart);
    return "user2/cart";
  }

  @GetMapping("/detail/{idbill}")
  public String detailBill(Model model, @PathVariable("idbill") int bill) {
    Customer customer = billService.getCustomer();
    model.addAttribute("customer", customer);
    return "user2/userbills";
  }
}
