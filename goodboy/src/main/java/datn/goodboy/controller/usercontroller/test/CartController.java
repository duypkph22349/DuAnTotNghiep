package datn.goodboy.controller.usercontroller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.service.test.CartService;

@Controller("testCartController")
@RequestMapping("/cart/test")
public class CartController {
  @Autowired
  private CartService cartService;

  @GetMapping
  public String viewCart(Model model) {
    Cart cart = cartService.getCart();
    model.addAttribute("cart", cart);
    return "user/cart";
  }
}
