package datn.goodboy.controller.usercontroller;

import datn.goodboy.model.entity.*;
import datn.goodboy.repository.BillDetailRepository;
import datn.goodboy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"home"})
public class HomeController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BillDetailRepository billDetailRepository;


    @GetMapping
    public String view(Model model){
        List<ProductDetail> productDetails = billDetailRepository.findTop10BestProducts();
        ArrayList<Product> product = productService.getAll();
        model.addAttribute("productDetails", productDetails);
        model.addAttribute("product", product);
        return "user/home";
    }

}
