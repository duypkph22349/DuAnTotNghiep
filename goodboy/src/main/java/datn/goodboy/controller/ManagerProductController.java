package datn.goodboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/managerproduct")
@Controller
public class ManagerProductController {
  @GetMapping({ "index", "" })
  public String getIndexPage() {
    return "/admin/pages/productdetail/manager-product.html";
  }
}
