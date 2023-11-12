package datn.goodboy.controller.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {
  @RequestMapping("index")
  public String getUserIndexPage(Model model) {
    return "/user/home.html";
  }
}
