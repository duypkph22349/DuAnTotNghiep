package datn.goodboy.controller;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/admin/counter")
@Controller
public class CounterController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {

        model.addAttribute("employee", employeeService.getAllEmployee());
        return "admin/pages/cartcounter/table-counter";
    }




}

