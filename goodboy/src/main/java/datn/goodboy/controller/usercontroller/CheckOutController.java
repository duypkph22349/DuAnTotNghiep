package datn.goodboy.controller.usercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;

@Controller
public class CheckOutController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @GetMapping("/shop/checkout")
    public String viewCheckout(Model model) {
        Cart cart = cartService.getCart();
        System.out.println(cart.getId());
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId(cart.getId());
        model.addAttribute("cartDetails", cartDetails);
        return "user/checkout";
    }
}
