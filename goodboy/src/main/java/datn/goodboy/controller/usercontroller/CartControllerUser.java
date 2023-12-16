package datn.goodboy.controller.usercontroller;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.CartRepository;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import datn.goodboy.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartControllerUser {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;
    @GetMapping("/cart")
    public String viewCart( Model model){
        Cart cart = cartService.getCart();
        System.out.println("ID cart ="+cart.getId());
        List<CartDetail> cartDetails = cartDetailService.findAllByCartId(cart.getId());
        model.addAttribute("cartDetails", cartDetails);
        BigDecimal tongTien = cartDetailService.getTotal(cartDetails);
        model.addAttribute("tongTien", tongTien);

        Integer quantity = cartDetailService.getQuantity(cartDetails);
        model.addAttribute("quantity", quantity);
        return "user/cart";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        cartDetailService.deleteCart(id);
        model.addAttribute("view","Bạn đã xóa sản phẩm khỏi giỏ hàng");
        return "redirect:/cart";
    }

    @RequestMapping("/updateSoLuong/{id}")
    public String updateSoLuong(Model model,
                                @PathVariable("id") int id,
                                @RequestParam("actions") String actions,
                                @RequestParam("quantity") Integer quantity

    ){

        CartDetail cartDetail = cartDetailService.findByIdCart(id);

        if (cartDetail != null) {
            if ("tang".equals(actions)) {
                cartDetail.setQuantity(cartDetail.getQuantity() + 1);
            } else if ("giam".equals(actions)) {
                cartDetail.setQuantity(Math.max(cartDetail.getQuantity() - 1, 1));
            }
            BigDecimal price = new BigDecimal(Float.toString(cartDetail.getProductDetail().getPrice()));
            BigDecimal donGia = price.multiply(BigDecimal.valueOf(cartDetail.getQuantity()));
            float donGiaFloat = donGia.floatValue();
            cartDetail.setPrice(donGiaFloat);
            cartDetailService.saveCart(cartDetail);
        }


        return "redirect:/cart";

    }
}
