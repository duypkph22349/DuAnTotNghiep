package datn.goodboy.controller.usercontroller;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.repository.CartDetailRepository;
import datn.goodboy.service.CartDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/shop/product/")
public class userUIController {
    private CartDetailService cartDetailService;

    @GetMapping("/header")
    public String getTotalQuantity(Model model, @PathVariable int id) {
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId(id);
        Integer quantity = cartDetailService.getQuantity2(cartDetails);
        model.addAttribute("quantity", quantity);
        return "/user/components/header" ;
    }

}
