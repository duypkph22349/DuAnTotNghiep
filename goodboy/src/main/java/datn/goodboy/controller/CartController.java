package datn.goodboy.controller;

import datn.goodboy.model.entity.Cart;
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
import java.util.Optional;

@RequestMapping("/api/cart")
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;
    private int currentProductCode = 1;
    @GetMapping("/show")
    public String show(){
        return "/admin/pages/cart/cart";
    }

    @GetMapping("/get-all")
    public List<Cart> getAll(){
        return cartService.getAllCart();
    }

    @GetMapping("/get-page")
    public ResponseEntity<Page<Cart>> getPage(@RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(cartService.getPage(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Cart b, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        String newCartCode = "BR" + String.format("%d", currentProductCode);
        b.setCode(newCartCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        return ResponseEntity.ok(cartService.saveCart(b));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update1(Model model, @PathVariable("id") Integer id,@RequestBody Cart cart) {
        Optional<Cart> cartOptional = cartService.findByIdCart(id);
        if (cartOptional.isPresent()) {
            model.addAttribute("detail", cartOptional.get());
            model.addAttribute("showCustomer", customerService.getAllCustomers());
        } else {
            model.addAttribute("detail", null);
        }
        return ResponseEntity.ok(cartService.saveCart(cart));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update2(@RequestBody Cart cart, @PathVariable("id")int id){
        cart.setId(id);
        cartService.saveCart(cart);
        return ResponseEntity.ok(cart);
    }

}

