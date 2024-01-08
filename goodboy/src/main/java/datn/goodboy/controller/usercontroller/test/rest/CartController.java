package datn.goodboy.controller.usercontroller.test.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.exeption.AuthenticationException;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.test.CartCookieService;
import datn.goodboy.service.test.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController("testRestCartController")
@RequestMapping("/test/api/cart")
public class CartController {
  @Autowired
  CartService cartService;
  @Autowired
  CartCookieService cartCookieService;
  @Autowired
  CustomerService customerService;

  @GetMapping("/add/{id}")
  public ResponseEntity<String> addToCart(
      @PathVariable("id") int productId,
      @RequestParam("quantity") int quantity, HttpServletRequest request, HttpServletResponse response) {
    try {
//      ADD TO CART LOGIN
      cartService.addToCart(productId, quantity);
      return ResponseEntity.ok("Thêm sản phẩm thành công");
    } catch (AuthenticationException e) {
      try {
//        ADD TO CART LOGIN
        cartCookieService.addToCart(productId, quantity, request, response);
      } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
      }
      return ResponseEntity.ok("Thêm sản phẩm thành công");
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

  @GetMapping("/cart/{id}")
  public ResponseEntity<CartDetail> getCartDetail(@PathVariable("id") int id) {
    return ResponseEntity.ok(cartService.getCartDetails(id));
  }

  @GetMapping("customer/{id}")
  public ResponseEntity<Customer> getMethodName(@PathVariable("id") UUID idcustomer) {
    return ResponseEntity.ok(customerService.getCustomerById(idcustomer).get());
  }
}
