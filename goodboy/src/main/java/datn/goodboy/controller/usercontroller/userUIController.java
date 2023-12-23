package datn.goodboy.controller.usercontroller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.service.BillDetailService;
import datn.goodboy.service.BillService;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;

@Controller
@RequestMapping("/shop/product/")
public class userUIController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("/cart/totalQuantity")
    public String getTotalQuantity(Model model) {
        Cart cart = cartService.getCart();
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId(cart.getId());
        Integer quantity2 = cartDetailService.getQuantity2(cartDetails);
        model.addAttribute("quantity2", quantity2);
        return "/user/components/header";
    }

    @GetMapping({ "/don_hang", "" })
    public String viewOderStatus(Model model) {
        UUID customerId = billService.getCustomerId();
        int billCount = billService.getBillCountByStatus(3);
        if (customerId != null) {
            List<Bill> bills = billService.findBillsByCustomerId(customerId);
            model.addAttribute("bills", bills);
            model.addAttribute("billDetail", bills);
            model.addAttribute("billCount", billCount);
        }
        return "/user/order_status";
    }

}
