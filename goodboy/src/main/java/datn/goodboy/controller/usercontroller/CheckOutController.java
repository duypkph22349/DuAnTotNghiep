package datn.goodboy.controller.usercontroller;

import java.util.List;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.service.BillService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckOutController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;
    @Autowired
    private BillService billService;

    @GetMapping("/shop/checkout")
    public String viewCheckout(Model model) {
        Cart cart = cartService.getCart();
        System.out.println(cart.getId());
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId(cart.getId());
        model.addAttribute("cartDetails", cartDetails);
        return "user/checkout";
    }

    @PostMapping("/checkout")
    public String saveCheckOut(@ModelAttribute("bill") Bill bill, HttpServletRequest request) {
        String money_ship = request.getParameter("money_ship");
        String total_money = request.getParameter("soTienCanThanhToan");
        bill.setMoney_ship(Float.parseFloat(money_ship));
        bill.setTotal_money(Double.valueOf(total_money));
        billService.saveBill(bill);
        return "redirect:/home";
    }
}
