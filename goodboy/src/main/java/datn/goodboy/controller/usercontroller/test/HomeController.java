package datn.goodboy.controller.usercontroller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.Product;
import datn.goodboy.service.BrandService;
import datn.goodboy.service.CategoryService;
import datn.goodboy.service.ColorService;
import datn.goodboy.service.MaterialService;
import datn.goodboy.service.OriginService;
import datn.goodboy.service.StylesService;
import datn.goodboy.service.test.CartService;
import datn.goodboy.service.test.ProductService;

@Controller("testHomeController")
@RequestMapping("/home/test")
public class HomeController {
  @Autowired
  private ProductService productService;

  @Autowired
  private BrandService brandService;

  @Autowired
  private ColorService colorService;

  @Autowired
  private MaterialService materialService;

  @Autowired
  private OriginService originService;

  @Autowired
  private StylesService stylesService;
  @Autowired
  private CategoryService categoryService;

  @ModelAttribute("categoryCbb")
  public List<Map<Integer, String>> getComboboxCategory() {
    return categoryService.getCombobox();
  }

  @ModelAttribute("brandCbb")
  public List<Map<Integer, String>> getComboboxBrand() {
    return brandService.getCombobox();
  }

  @ModelAttribute("colorCbb")
  public List<Map<Integer, String>> getComboboxColor() {
    return colorService.getCombobox();
  }

  @ModelAttribute("materialCbb")
  public List<Map<Integer, String>> getComboboxMaterial() {
    return materialService.getCombobox();
  }

  @ModelAttribute("originCbb")
  public List<Map<Integer, String>> getComboboxOrigin() {
    return originService.getCombobox();
  }

  @ModelAttribute("stylesCbb")
  public List<Map<Integer, String>> getComboboxStyles() {
    return stylesService.getCombobox();
  }

  @GetMapping
  public String view(Model model) {
    model.addAttribute("productDetails", productService.getTopProductSaleThisYear(10));
    model.addAttribute("product", productService.getNewProducts(20));
    return "user/home";
  }

  @Autowired
  private CartService cartService;

  @GetMapping("cart")
  public String viewCart(Model model) {
    Cart cart = cartService.getCart();
    model.addAttribute("cart", cart);
    return "user2/cart";
  }

  @GetMapping("checkout")
  public String viewCheckOut(Model model) {
    return "user2/checkout";
  }

  @GetMapping("index")
  public String viewProduct(Model model) {
    return "user2/index";
  }

  @GetMapping("detail/{id}")
  public String viewProductDetails(Model model, @PathVariable("id") int id) {
    List<Product> product = productService.getTopProductSaleThisYear(10);
    model.addAttribute("productrelasze", product);
    model.addAttribute("product", productService.getById(id));
    return "user2/detail";
  }

  @GetMapping("shop")
  public String viewIndex(Model model) {

    return "user2/shop";
  }

}
