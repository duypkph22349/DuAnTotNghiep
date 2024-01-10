package datn.goodboy.controller.usercontroller.test.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import datn.goodboy.model.cookieentity.CartResponse;
import datn.goodboy.service.test.CartCookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController("testRestCartControllerCookie")
@RequestMapping("/test/api/cart/cookie")
public class CartControllerCookie {
  @Autowired
  CartCookieService cartCookieService;

  @PatchMapping("/update/{id}/quantity/{quantity}")
  public ResponseEntity<?> updateCartDetail(
      @PathVariable("id") int cartDetailId, @PathVariable("quantity") int quantity, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      CartResponse updatedCartDetail = cartCookieService.updateToCart(cartDetailId, quantity, request, response);
      return ResponseEntity.ok(updatedCartDetail);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteCartDetails(@PathVariable("id") int cartDetailId, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      cartCookieService.removeFromCart(cartDetailId, request, response);
      return ResponseEntity.ok("Cart" + cartDetailId + " detail deleted ");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PutMapping("/delete-carts")
  public ResponseEntity<String> deleteCartDetailSelected(@RequestBody List<String> cartDetails, HttpServletRequest request,
                                                         HttpServletResponse response){
    try {
      cartCookieService.removeFromCarts(cartDetails, request, response);
      return ResponseEntity.ok("Carts detail deleted ");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @GetMapping("/totalmoney")
  public ResponseEntity<Double> getTotalMoney(@RequestParam("carts") List<Integer> cartDetails,
      HttpServletRequest request, HttpServletResponse response) {
    return ResponseEntity.ok(cartCookieService.calculateTotalPrice(cartDetails, request, response));
  }

  @GetMapping("/cart/{id}")
  public ResponseEntity<CartResponse> getCartDetail(@PathVariable("id") int id, HttpServletRequest request,
      HttpServletResponse response) {
    return ResponseEntity.ok(cartCookieService.getCartResponse(request, response, id));
  }

}
