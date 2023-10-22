package datn.goodboy.controller;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.service.CartService;
import datn.goodboy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping("/add")
    public String add(@ModelAttribute Cart cart) {
        cartService.saveCart(cart);
        return "redirect:/admin/cartcounter/hien-thi";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        cartService.deleteCart(id);
        return "redirect:/admin/cartcounter/hien-thi";
    }
    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Optional<Cart> customer = cartService.findByIdCart(id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
        } else {
            model.addAttribute("detail", null);
        }
        return "redirect:/admin/cartcounter/hien-thi";
    }



}






