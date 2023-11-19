package datn.goodboy.controller.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class HomePageController {
  @RequestMapping("index")
  public String getUserIndexPage(Model model) {
    return "/user/home.html";
  }

  @GetMapping("shope")
  public String getShope(Model model) {
    return "/user/index.html";
  }

  @GetMapping("cart")
  public String getCart(Model model) {
    return "/user/cart.html";
  }

  @GetMapping("shop")
  public String getshop(Model model) {
    return "/user/shop.html";
  }

}
