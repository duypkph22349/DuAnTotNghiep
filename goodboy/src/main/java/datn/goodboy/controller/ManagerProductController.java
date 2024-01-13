package datn.goodboy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Product;
import datn.goodboy.model.request.ProductFillterManager;
import datn.goodboy.service.BrandService;
import datn.goodboy.service.CategoryService;
import datn.goodboy.service.ColorService;
import datn.goodboy.service.ImageService;
import datn.goodboy.service.ManagerProductService;
import datn.goodboy.service.MaterialService;
import datn.goodboy.service.OriginService;
import datn.goodboy.service.PatternTypeService;
import datn.goodboy.service.ProductService;
import datn.goodboy.service.SizeService;
import datn.goodboy.service.StylesService;
import datn.goodboy.utils.convert.TrangThaiConvert;

@RequestMapping("/admin/managerproduct")
@Controller
public class ManagerProductController {
  @Autowired
  private ManagerProductService service;
  @Autowired
  TrangThaiConvert convert;

  @ModelAttribute("convert")
  public TrangThaiConvert convert() {
    return convert;
  }

  @Autowired
  private BrandService brandService;

  @Autowired
  private ColorService colorService;

  @Autowired
  private MaterialService materialService;

  @Autowired
  private OriginService originService;

  @Autowired
  private PatternTypeService patternTypeService;

  @Autowired
  private ProductService productService;

  @Autowired
  private SizeService sizeService;

  @Autowired
  private StylesService stylesService;

  @Autowired
  private CategoryService categoryService;

  // @ModelAttribute("brand-combobox")
  // public ProductDetailFilter fillter() {
  // return filter;
  // }

  @Autowired
  @Qualifier("productfilltermanager")
  private ProductFillterManager fillter;

  @ModelAttribute("fillter")
  public ProductFillterManager fillter() {
    return fillter;
  }

  @ModelAttribute("categoryCbb")
  public List<Map<Integer, String>> getComboboxCategory() {
    return categoryService.getCombobox();
  }

  @Autowired
  private ImageService imageService;

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

  @ModelAttribute("productCbb")
  public List<Map<Integer, String>> getComboboxProduct() {
    return productService.getCombobox();
  }

  @ModelAttribute("pattenCbb")
  public List<Map<Integer, String>> getComboboxPattern() {
    return patternTypeService.getCombobox();
  }

  @ModelAttribute("sizeCbb")
  public List<Map<Integer, String>> getComboboxSize() {
    return sizeService.getCombobox();
  }

  @ModelAttribute("stylesCbb")
  public List<Map<Integer, String>> getComboboxStyles() {
    return stylesService.getCombobox();
  }

  public int rowcount = 10;

  public int[] pagenumbers;
  public String sortBy = "updatedAt";
  public boolean sortDir = false;
  public int pageno = 0;
  public int totalpage = 0;

  @ModelAttribute("rowcount")
  public int rowcount() {
    return rowcount;
  }

  // panigation and sort
  @GetMapping("/getcountrow")
  public String getCountRow(Model model, @RequestParam("selectedValue") String selectedValue,
      @ModelAttribute("fillter") ProductFillterManager fillter) {
    rowcount = Integer.parseInt(selectedValue);

    if (fillter != null) {
      if (fillter.filterAble()) {
        List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/manager-product.html"; // Redirect back to the form page
      }
    }
    this.pageno = 1;
    List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);

    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/manager-product.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir,
      @ModelAttribute("fillter") ProductFillterManager fillter) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    if (fillter != null) {
      if (fillter.filterAble()) {
        List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/manager-product.html"; // Redirect back to the form page
      }
    }
    List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/manager-product.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno,
      @ModelAttribute("fillter") ProductFillterManager fillter) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    if (fillter != null) {
      if (fillter.filterAble()) {
        List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/manager-product.html"; // Redirect back to the form page
      }
    }
    List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);

    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/manager-product.html";
  }

  // end
  @GetMapping({ "index", "" })
  public String getProductIndexpages(Model model,
      @ModelAttribute("fillter") ProductFillterManager fillter) {
    this.pageno = 1;
    System.out.println(fillter);
    if (fillter != null) {
      if (fillter.filterAble()) {
        System.out.println("get result of fillter ");

        List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/manager-product.html"; // Redirect back to the form page
      }
    }
    List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);

    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/manager-product.html";
  }

  @GetMapping("filter")
  public String storeProductDetail(Model model, @ModelAttribute("fillter") ProductFillterManager fillter) {
    return "redirect:index";
  }

  @GetMapping("resetfilter")
  public String resetFilter(Model model, @ModelAttribute("fillter") ProductFillterManager fillter) {
    fillter.resetFilter();
    model.addAttribute("fillter", fillter);
    return "redirect:index";
  }

  @GetMapping("delete")
  public String deleteProduct(Model model, @RequestParam("id") int id) {
    service.deleteProduct(id);
    return "redirect:index";
  }

  @GetMapping("/search")
  public String search(Model model,
      @RequestParam(name = "txtSearch", required = false) String search,
      @RequestParam(name = "status", defaultValue = "0") int status) {
    this.pageno = 1;
    List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/manager-product.html";
  }

  @GetMapping("/create")
  public String createProduct(Model model) {
    model.addAttribute("product", new Product());
    return "/admin/pages/productdetail/create-product.html";
  }
}
