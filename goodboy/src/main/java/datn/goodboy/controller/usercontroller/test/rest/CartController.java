package datn.goodboy.controller.usercontroller.test.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Voucher;
import datn.goodboy.service.test.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController("testRestCartController")
@RequestMapping("/test/api/cart")
public class CartController {
  @Autowired
  CartService cartService;

  @PutMapping("/add/{id}")
  public ResponseEntity<String> addToCart(
      @PathVariable("id") int productId,
      @RequestBody int quantity) {
    try {
      cartService.addToCart(productId, quantity);
      return ResponseEntity.ok("Item added to the cart");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PatchMapping("/update/{id}/quantity/{quantity}")
  public ResponseEntity<?> updateCartDetail(
      @PathVariable("id") int cartDetailId, @PathVariable("quantity") int quantity) {
    try {
      CartDetail updatedCartDetail = cartService.updateCart(cartDetailId, quantity);
      return ResponseEntity.ok(updatedCartDetail);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteCartDetails(@PathVariable("id") int cartDetailId) {
    try {
      cartService.deleteCartDetails(cartDetailId);
      return ResponseEntity.ok("Cart detail deleted");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @GetMapping("/totalmoney")
  public ResponseEntity<Double> getTotalMoney(@RequestParam("carts") List<Integer> cartDetails) {
    return ResponseEntity.ok(cartService.calculateTotalPrice(cartDetails));
  }

}
