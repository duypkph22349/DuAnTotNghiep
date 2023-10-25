package datn.goodboy.controller;

import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/admin/counter")
@Controller
public class CounterController {
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


    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("productDetailList", productDetailService.getAllProductDetail());
        model.addAttribute("employee", employeeService.getAllEmployee());
        model.addAttribute("sanPhamTrongGio", cartService2.getAllItems());
        model.addAttribute("total", cartService2.getAmount());
        return "admin/pages/cartcounter/table-counter";
    }

    @PostMapping("add")
    public String addItemToCart(@PathVariable("idProduct") Integer id, @RequestParam("quantity") Integer quantity,Model model) {
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
                    // Nếu sản phẩm chưa tồn tại trong giỏ hàng, tạo mới đối tượng CartItem và thêm vào giỏ hàng
                    itemToAdd = new CartDetail();
                    itemToAdd.setId(product.get().getId());
                    itemToAdd.setProductDetail(product.get());
                    itemToAdd.setProductDetail(product.get());
                    itemToAdd.setQuantity(quantity);



                    cartService2.add(itemToAdd);
                } else {
                    // Nếu sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng theo yêu cầu
                    itemToAdd.setQuantity(itemToAdd.getQuantity() + quantity);
                    model.addAttribute("cart",itemToAdd.getQuantity());
                    System.out.println(itemToAdd);
                    cartService2.update(itemToAdd.getId(), itemToAdd.getQuantity());
                }
            }
        }

        return "redirect:admin/pages/cartcounter/table-counter";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService2.clear();
        return "redirect:admin/pages/cartcounter/table-counter";
    }

    @GetMapping("/delete/{idProduct}")
    public String removeItemCart(@PathVariable("idProduct") Integer id) {
        cartService2.remove(id);
        return "redirect:admin/pages/cartcounter/table-counter";

    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
        cartService2.update(id, qty);
        return "redirect:admin/pages/cartcounter/table-counter";
    }




}

