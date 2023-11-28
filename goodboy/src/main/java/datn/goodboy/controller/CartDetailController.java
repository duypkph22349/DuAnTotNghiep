// package datn.goodboy.controller;
//
// import datn.goodboy.model.entity.Cart;
// import datn.goodboy.model.entity.CartDetail;
// import datn.goodboy.model.entity.Customer;
// import datn.goodboy.model.entity.ProductDetail;
// import datn.goodboy.service.CartDetailService;
// import datn.goodboy.service.CartService;
// import datn.goodboy.service.CustomerService;
// import datn.goodboy.service.ProductDetailService;
// import jakarta.servlet.http.HttpSession;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
//
//
// @RequestMapping("user")
// @Controller
// public class CartDetailController {
// @Autowired
// private ProductDetailService productDetailService;
//
// @Autowired
// private CartDetailService cartDetailService;
//
// @GetMapping("/cart/index")
// public String hienThi(Model model ) {
// model.addAttribute("list", cartDetailService.getAllCartDetail());
// model.addAttribute("productDetail",
// productDetailService.getAllProductDetail());
// List<CartDetail> list = cartDetailService.getAllCartDetail();
// BigDecimal tongTien = cartDetailService.getTotal(list);
// model.addAttribute("tongTien", tongTien);
// return "user/cart";
// }
//
// @GetMapping("delete/{id}")
// public String delete(@PathVariable("id") Integer id, Model model) {
// cartDetailService.deleteCart(id);
// model.addAttribute("view","Bạn đã xóa sản phẩm khỏi giỏ hàng");
// return "redirect:/user/cart/index";
// }
//
// @RequestMapping("/updateSoLuong/{id}")
// public String updateSoLuong(Model model,
// @PathVariable("id") int id,
// @RequestParam("actions") String actions,
// @RequestParam("quantity") Integer quantity
//
// ){
//
// CartDetail cartDetail = cartDetailService.findByIdCart(id);
//
// if (cartDetail != null) {
// if ("tang".equals(actions)) {
// cartDetail.setQuantity(cartDetail.getQuantity() + 1);
// } else if ("giam".equals(actions)) {
// cartDetail.setQuantity(Math.max(cartDetail.getQuantity() - 1, 1));
// }
// BigDecimal price = new
// BigDecimal(Float.toString(cartDetail.getProductDetail().getPrice()));
// BigDecimal donGia =
// price.multiply(BigDecimal.valueOf(cartDetail.getQuantity()));
// float donGiaFloat = donGia.floatValue();
// cartDetail.setPrice(donGiaFloat);
// cartDetailService.saveCart(cartDetail);
// }
//
//
// return "redirect:/user/cart/index";
//
// }
////
//
// CustomerService customerService;
// CartService cartService;
//// @PostMapping("/addToCart/{id}")
//// public String addToCart(Model model,
//// HttpSession session,
//// @PathVariable(value = "id") Integer id,
//// //@RequestParam ("maKH") String maKH,//lấy mã khách hàng
//// @RequestParam ("soLuong") Integer soLuong,
//// RedirectAttributes redirectAttributes //lưu lại mã khách hàng
////
//// ){
//// String maNV = (String) session.getAttribute("maNV");
//// UUID uuidMaNV = UUID.nameUUIDFromBytes(maNV.getBytes());
//// if (maNV != null) {
//// // Đã đăng nhập và lưu mã nhân viên vào session
////
//// // Tìm đối tượng User tương ứng với mã nhân viên
//// Customer customer = customerService.customerByid(uuidMaNV);
////
//// if (customer != null) {
////
//// String maNhanVienTrongUser = customer.getCode(); // Trích xuất mã nhân viên
// từ đối tượng User
////
//// //how to code findByMaNV
//// Cart cart = cartService.findByMaNV(maNhanVienTrongUser);
////
//// if (cart == null) {
//// cart = new Cart();
//// cart.setCode(maNhanVienTrongUser); // Tạo mã giỏ hàng tự động
////
//// // Gán mã nhân viên từ đối tượng User vào giỏ hàng
//// cart.setCustomer(customer);
////
//// cartService.addGioHang(cart); // Lưu giỏ hàng vào cơ sở dữ liệu
//// }
////
//// ProductDetail productDetail =
// productDetailService.getProductDetailById(id).orElse(null);
////
//// if(productDetail != null){
////
//// CartDetail crt = cartDetailService.findByMaGHAndMaCTSP(cart,productDetail);
////
//// if(crt != null){
////
//// crt.setQuantity(crt.getQuantity() + soLuong);
//// cartDetailService.saveCart(crt);
////
//// }else{
////
//// crt = new CartDetail();
//// crt.setCart(cart);
//// crt.setProductDetail(productDetail);
//// crt.setQuantity(soLuong);
//// crt.setPrice(productDetail.getPrice());
////
//// cartDetailService.saveCart(crt);
////
//// }
//// }
//// }
//// }
////
//// return "redirect:/user/cart/index";
////
//// }
//
//
//
//
//
// }
