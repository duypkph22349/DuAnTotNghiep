package datn.goodboy.controller.testcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.service.test.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController("usercartcontroller")
@RequestMapping("/test/cart/")
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping("/add")
  public ResponseEntity<Cart> addToCart(@RequestParam("idproduct") int idProduct,
      @RequestParam("quantity") int quantity) {
    Cart cart = cartService.addToCart(idProduct, quantity);
    return ResponseEntity.ok(cart);
  }

  @GetMapping("/update")
  public ResponseEntity<CartDetail> updateCartDetail(@RequestParam("idcartdetails") int idCartDetails,
      @RequestParam("quantity") int quantity) {
    CartDetail updatedCartDetail = cartService.updateCart(idCartDetails, quantity);
    if (updatedCartDetail != null) {
      return ResponseEntity.ok(updatedCartDetail);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/get")
  public ResponseEntity<Cart> getCart() {
    Cart cart = cartService.getCart();
    if (cart != null) {
      return ResponseEntity.ok(cart);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/delete/{idcartdetails}")
  public ResponseEntity<Void> deleteCartDetail(@PathVariable("idcartdetails") int idCartDetails) {
    try {
      cartService.deleteCartDetails(idCartDetails);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
