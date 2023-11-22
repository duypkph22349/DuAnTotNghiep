// package datn.goodboy.controller.usercontroller;

// import datn.goodboy.model.DTO.CartDetailDto;
// import datn.goodboy.model.DTO.CartDto;
// import datn.goodboy.model.entity.Cart;
// import datn.goodboy.service.ProductDetailService;
// import datn.goodboy.service.serviceinterface.CartService;
// import jakarta.servlet.http.HttpSession;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import java.time.LocalDate;

// @RestController
// @RequestMapping("/api/cart")
// public class APICart {
//     @Autowired
//     CartService service;

// //    @Autowired
// //    AccountsRepo accRepo;

// //    @Autowired
// //    OrdersRepo orderRepo;
// //
// //    @Autowired
// //    OrderDetailsRepo detailRepo;

//     @Autowired
//     ProductDetailService productRepo;

//     @Autowired
//     CartService cartService;

//     // localhost:8080/api/cart/update?productId=...&quantity=...&isReplace=...
//     @PostMapping("/update")
//     public ResponseEntity<?> doPostUpdate(@RequestParam Integer productId, @RequestParam Integer quantity,
//                                           @RequestParam boolean isReplace, HttpSession session) {
//         CartDto cart = (CartDto) session.getAttribute(PTConstain.SESSION_CART);
//         service.updateCart(cart, productId, quantity, isReplace);
//         session.setAttribute(PTConstain.SESSION_CART, cart);
//         return ResponseEntity.ok(cart);
//     }

//     // localhost:8080/api/cart/update?productId=...&quantity=...&isReplace=...
//     @SuppressWarnings("deprecation")
//     @PostMapping("/checkout")
//     @Transactional
//     public ResponseEntity<?> doPostCheckout(@RequestParam String username, @RequestParam String address,
//                                             @RequestParam String phone, HttpSession session) {
//         boolean check = true;
//         CartDto cart = (CartDto) session.getAttribute(PTConstain.SESSION_CART);

//         Cart order = new Cart();

// //        order.set(accRepo.getById(username));
// //        order.set(address);
// //        order.set(phone);
//         order.setStatus(1);
//         order.setCreatedAt(LocalDate.now().atStartOfDay());
//         order.setMoney(Long.parseLong(String.valueOf(cart.getTotalPrice())));

//         try {
//             order = cartService.save(order);

//             for (CartDetailDto detail : cart.getListDetail().values()) {
//                 OrderDetails orderDetail = new OrderDetails();
//                 orderDetail.setOrder(order);
//                 orderDetail.setProduct(productRepo.getById(detail.getProductId()));
//                 orderDetail.setQuantity(detail.getQuantity());
//                 orderDetail.setTotalPrice(detail.getPrice());

//                 detailRepo.save(orderDetail);
//             }

//         } catch (Exception e) {
//             check = false;
//         }

//         session.setAttribute(PTConstain.SESSION_CART, new CartDto());
//         return ResponseEntity.ok(check);
//     }

// }
