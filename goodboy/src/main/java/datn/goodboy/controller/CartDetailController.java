package datn.goodboy.controller;

import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@Controller
public class CartDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CartDetailService cartDetailService;

    @GetMapping("/home/cart/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("list", cartDetailService.getAllCartDetail());
        model.addAttribute("productDetail", productDetailService.getAllProductDetail());
        return "user/cart";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        cartDetailService.deleteCart(id);
        return "redirect:/user/home/cart/hien-thi";
    }



}
