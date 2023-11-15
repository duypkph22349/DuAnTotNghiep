// package datn.goodboy.controller.usercontroller;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import datn.goodboy.model.entity.ProductDetail;
// import datn.goodboy.model.request.UserProductFillter;
// import datn.goodboy.service.BrandService;
// import datn.goodboy.service.ColorService;
// import datn.goodboy.service.MaterialService;
// import datn.goodboy.service.OriginService;
// import datn.goodboy.service.PatternTypeService;
// import datn.goodboy.service.ProductDetailService;
// import datn.goodboy.service.ProductService;
// import datn.goodboy.service.SizeService;
// import datn.goodboy.service.StylesService;

// @Controller("userproduct")
// @RequestMapping("shop/product")
// public class ProductController {
//   @Autowired
//   ProductDetailService productDetailService;

//   @Autowired
//   ProductService productService;

//   @Autowired
//   private ProductDetailService service;

//   @Autowired
//   private BrandService brandService;

//   @Autowired
//   private ColorService colorService;

//   @Autowired
//   private MaterialService materialService;

//   @Autowired
//   private OriginService originService;

//   @Autowired
//   private PatternTypeService patternTypeService;

//   @Autowired
//   private SizeService sizeService;

//   @Autowired
//   private StylesService stylesService;

//   @ModelAttribute("brandCbb")
//   public List<Map<Integer, String>> getComboboxBrand() {
//     return brandService.getCombobox();
//   }

//   @ModelAttribute("colorCbb")
//   public List<Map<Integer, String>> getComboboxColor() {
//     return colorService.getCombobox();
//   }

//   @ModelAttribute("materialCbb")
//   public List<Map<Integer, String>> getComboboxMaterial() {
//     return materialService.getCombobox();
//   }

//   @ModelAttribute("originCbb")
//   public List<Map<Integer, String>> getComboboxOrigin() {
//     return originService.getCombobox();
//   }

//   @ModelAttribute("pattenCbb")
//   public List<Map<Integer, String>> getComboboxPattern() {
//     return patternTypeService.getCombobox();
//   }

//   @ModelAttribute("sizeCbb")
//   public List<Map<Integer, String>> getComboboxSize() {
//     return sizeService.getCombobox();
//   }

//   @ModelAttribute("stylesCbb")
//   public List<Map<Integer, String>> getComboboxStyles() {
//     return stylesService.getCombobox();
//   }

//   @Autowired
//   UserProductFillter fillter;

//   @ModelAttribute("filter")
//   public UserProductFillter fillter() {
//     return fillter;
//   }

//   @GetMapping({ "index" })
//   public String indexpage() {
//     return "/user/shop.html";
//   }

//   @ModelAttribute("products")
//   public List<ProductDetail> getAllProdcut() {
//     return productDetailService.getAllProductDetail();
//   }

//   @GetMapping("fillter")
//   public String fillter(
//       @RequestParam(value = "brand", required = false, defaultValue = "") List<Integer> brandfi,
//       @RequestParam(value = "material", required = false, defaultValue = "") List<Integer> materialfi,
//       @RequestParam(value = "style", required = false, defaultValue = "") List<Integer> stylefi,
//       @RequestParam(value = "pattern", required = false, defaultValue = "") List<Integer> patternfi,
//       @RequestParam(value = "origin", required = false, defaultValue = "") List<Integer> originfi) {
//     fillter = new UserProductFillter(brandfi, materialfi, originfi, patternfi, stylefi);
//     System.out.println(fillter);
//     return "/user/shop.html";
//   }

//   @GetMapping("removefilter")
//   public String removeFilter(
//       @RequestParam(value = "brand", required = false, defaultValue = "") List<Integer> brandfi,
//       @RequestParam(value = "material", required = false, defaultValue = "") List<Integer> materialfi,
//       @RequestParam(value = "style", required = false, defaultValue = "") List<Integer> stylefi,
//       @RequestParam(value = "pattern", required = false, defaultValue = "") List<Integer> patternfi,
//       @RequestParam(value = "origin", required = false, defaultValue = "") List<Integer> originfi) {
//     fillter = new UserProductFillter(brandfi, materialfi, originfi, patternfi, stylefi);
//     System.out.println(fillter);
//     return "/user/shop.html";
//   }
// }
