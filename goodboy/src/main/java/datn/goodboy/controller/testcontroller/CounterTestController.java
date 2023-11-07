package datn.goodboy.controller.testcontroller;

import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/test/counter")
@Controller
public class CounterTestController {
  // bean spring boot // -> luư trạng thái của 1 object -> DJ
  List<CartDetail> cardetail = new ArrayList<CartDetail>();
// CartDetail (Vd) -> chuyển thành 1 cái request order khác!
  @ModelAttribute("orderlist") // vừa là bean + model .addtribue ( "orderlist" ,new ArrayList<CartDetail>()) --
                               // all time app
  public List<CartDetail> lisorder() {
    return cardetail;
  }
  // thao tác trên cardetail ->>
  // time tao
  // time to pay ??
  // id cartdetail
  // view --
  // nut thanh CartDetail -> luư vào db -> id db ra xong sang xác nhận thanh toan

  @Autowired
  private CartService cartService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private ProductDetailService productDetailService;

  @Autowired
  private ShoppingCartService cartService2;

  @Autowired
  private CartDetailService icartService;

  @GetMapping({ "/hien-thi", "" })
  public String hienThi(Model model) {
    model.addAttribute("productDetailList", productDetailService.getAllProductDetail());
    model.addAttribute("employee", employeeService.getAllEmployee());
    model.addAttribute("sanPhamTrongGio", cartService2.getAllItems());
    model.addAttribute("total", cartService2.getAmount());
    return "admin/pages/cartcounter/test-counter";
  }

  @PostMapping("add")
  public String addItemToCart(@PathVariable("idProduct") Integer id, @RequestParam("quantity") Integer quantity,
      Model model) {
    Optional<ProductDetail> product = productDetailService.getProductDetailById(id);
    if (product.isPresent()) {
      if (quantity > 0) {
        CartDetail itemToAdd = null;
        List<CartDetail> cartItems = cartService2.getCartItems();

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        for (CartDetail item : cartItems) {
          if (item.getId() == product.get().getId()) {
            itemToAdd = item;
            break;
          }
        }

        if (itemToAdd == null) {
          // Nếu sản phẩm chưa tồn tại trong giỏ hàng, tạo mới đối tượng CartItem và thêm
          // vào giỏ hàng
          itemToAdd = new CartDetail();
          itemToAdd.setId(product.get().getId());
          itemToAdd.setProductDetail(product.get());
          itemToAdd.setProductDetail(product.get());
          itemToAdd.setQuantity(quantity);

          cartService2.add(itemToAdd);
        } else {
          // Nếu sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng theo yêu cầu
          itemToAdd.setQuantity(itemToAdd.getQuantity() + quantity);
          model.addAttribute("cart", itemToAdd.getQuantity());
          System.out.println(itemToAdd);
          cartService2.update(itemToAdd.getId(), itemToAdd.getQuantity());
        }
      }
    }

    return "redirect:admin/pages/cartcounter/test-counter";
  }

  @GetMapping("/clear")
  public String clearCart() {
    cartService2.clear();
    return "redirect:admin/pages/cartcounter/test-counter";
  }

  @GetMapping("/delete/{idProduct}")
  public String removeItemCart(@PathVariable("idProduct") Integer id) {
    cartService2.remove(id);
    return "redirect:admin/pages/cartcounter/test-counter";

  }

  @PostMapping("/update")
  public String update(@ModelAttribute("list") List<CartDetail> listCart, @RequestParam("id") Integer id,
      @RequestParam("qty") Integer qty) {
    cartService2.update(id, qty);
    return "redirect:admin/pages/cartcounter/test-counter";
  }

  public List<CartDetail> getCardetail() {
    return cardetail;
  }

  public void setCardetail(List<CartDetail> cardetail) {
    this.cardetail = cardetail;
  }

  public CartService getCartService() {
    return cartService;
  }

  public void setCartService(CartService cartService) {
    this.cartService = cartService;
  }

  public CustomerService getCustomerService() {
    return customerService;
  }

  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  public EmployeeService getEmployeeService() {
    return employeeService;
  }

  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public ProductDetailService getProductDetailService() {
    return productDetailService;
  }

  public void setProductDetailService(ProductDetailService productDetailService) {
    this.productDetailService = productDetailService;
  }

  public ShoppingCartService getCartService2() {
    return cartService2;
  }

  public void setCartService2(ShoppingCartService cartService2) {
    this.cartService2 = cartService2;
  }

  public CartDetailService getIcartService() {
    return icartService;
  }

  public void setIcartService(CartDetailService icartService) {
    this.icartService = icartService;
  }

}
