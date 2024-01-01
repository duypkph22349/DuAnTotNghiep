package datn.goodboy.controller.usercontroller.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.service.test.CartService;

@RestController("testRestCartController")
@RequestMapping("/test/api/cart")
public class CartController {
  @Autowired
  CartService cartService;

  @PostMapping("/add/{id}")
  public ResponseEntity<String> addToCart(
      @PathVariable("id") int productId,
      @RequestParam("quantity") int quantity) {
    System.out.println("id: " + productId);
    System.out.println("quantity: " + quantity);
    cartService.addToCart(productId, quantity);
    return ResponseEntity.ok("Item added to the cart");
  }

}
