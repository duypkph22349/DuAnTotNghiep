package datn.goodboy.controller.usercontroller.test;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.request.EvaluateRequest;
import datn.goodboy.service.test.BillService;
import datn.goodboy.service.test.CartService;
import jakarta.validation.Valid;

@Controller("testBillController")
@RequestMapping("/shop/order")
public class BillController {
  @Autowired
  BillService billService;
  @Autowired
  CartService cartService;
  @Autowired
  EvaluateRequest evaluateRequest;

  @ModelAttribute("evaluateRequest")
  public EvaluateRequest getEvaluateRequest() {
    return evaluateRequest;
  }

  @GetMapping("/checkout")
  public String viewCheckout(Model model, @RequestParam("carts") List<Integer> cartDetails) {
    Bill bill = billService.getProductDetailsByCartDetails(cartDetails);
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
    return "redirect:/shop/order/history";
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
//    Customer customer = billService.getCustomer();
//    model.addAttribute("customer", customer);
    return "user2/userbills";
  }

  @GetMapping("/evaluate/{idbill}")
  public String evaluateBill(Model model, @PathVariable("idbill") int bill) {
    evaluateRequest = billService.getEvaluateRequest(bill);
    System.out.println(evaluateRequest);
    model.addAttribute("evaluateRequest", evaluateRequest);
    return "user2/evaluate";
  }

  @PostMapping("/evaluate/save")
  public String saveEvaluate(@Valid @ModelAttribute("evaluateRequest") EvaluateRequest request, Model model) {
    billService.saveEvaluate(request);
    return "redirect:/shop/order/history";
  }
}
