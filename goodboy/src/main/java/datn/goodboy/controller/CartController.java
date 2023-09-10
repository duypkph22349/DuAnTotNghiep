package datn.goodboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cart")
@Controller
public class CartController {

    @GetMapping("/show")
    public String show(){
        return "Cart/Cart";
    }

}
