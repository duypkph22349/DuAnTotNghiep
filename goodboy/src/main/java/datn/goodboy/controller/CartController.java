package datn.goodboy.controller;

import datn.goodboy.service.CartService;
import datn.goodboy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin/cart")
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("cart", cartService.getAllCart());
        model.addAttribute("roles", customerService.getAllCustomers());
        return "admin/pages/cartcounter/table-counter";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        cartService.deleteCart(id);
        return "redirect:/admin/cartcounter/hien-thi";
    }



}






