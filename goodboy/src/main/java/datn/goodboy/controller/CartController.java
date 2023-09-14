package datn.goodboy.controller;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/cart")
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/show")
    public String show(){
        return "Cart/Cart";
    }

    @GetMapping("/hien-thi")
    public ResponseEntity<Page<Cart>> hienThi(@RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(cartService.getPage(pageable));
    }
}
