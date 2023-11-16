package datn.goodboy.controller;

import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.ProductDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;


@RequestMapping("user")
@Controller
public class CartDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CartDetailService cartDetailService;

    @GetMapping("/cart/hien-thi")
    public String hienThi(Model model ) {
        model.addAttribute("list", cartDetailService.getAllCartDetail());
        model.addAttribute("productDetail", productDetailService.getAllProductDetail());
        List<CartDetail> list = cartDetailService.getAllCartDetail();
        BigDecimal tongTien = cartDetailService.getTotal(list);
        model.addAttribute("tongTien", tongTien);
        return "user/cart";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        cartDetailService.deleteCart(id);
        model.addAttribute("view","Bạn đã xóa sản phẩm khỏi giỏ hàng");
        return "redirect:/user/cart/hien-thi";
    }

    @RequestMapping("/updateSoLuong/{id}")
    public String updateSoLuong(Model model,
                                @PathVariable("id") int id,
                                @RequestParam("actions") String actions,
                                @RequestParam("quantity") Integer quantity

    ){

         CartDetail  cartDetail = cartDetailService.findByIdCart(id);

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





        return "redirect:/user/cart/hien-thi";

    }



}
