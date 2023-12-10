package datn.goodboy.controller.usercontroller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.repository.BillDetailRepository;
import datn.goodboy.service.BillDetailService;
import datn.goodboy.service.BillService;
import datn.goodboy.service.ProductDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckOutController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;
    @Autowired
    private BillService billService;

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/shop/checkout")
    public String viewCheckout(Model model) {
        Cart cart = cartService.getCart();
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId(cart.getId());
        model.addAttribute("cartDetails", cartDetails);
        return "user/checkout";
    }



    @PostMapping("/checkout")
    public String saveCheckOut(@RequestParam("cartDetails") List<Integer> cartDetails) {
        Bill bill = cartService.getCheckOutPage(cartDetails);
        billService.saveBillAndDetails(bill);
        productDetailService.updateProductQuantities(bill.getBillDetail());
        return "redirect:/home";
    }
}
