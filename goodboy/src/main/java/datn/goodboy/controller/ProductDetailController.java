package datn.goodboy.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.ProductDetailFilter;
import datn.goodboy.model.request.ProductDetailRequest;
import datn.goodboy.service.BrandService;
import datn.goodboy.service.ColorService;
import datn.goodboy.service.ImageService;
import datn.goodboy.service.MaterialService;
import datn.goodboy.service.OriginService;
import datn.goodboy.service.PatternTypeService;
import datn.goodboy.service.ProductDetailService;
import datn.goodboy.service.ProductService;
import datn.goodboy.service.SizeService;
import datn.goodboy.service.StylesService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/productdetail")
public class ProductDetailController {

  @Autowired
  private ProductDetailService service;

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

  // @ModelAttribute("brand-combobox")
  // public ProductDetailFilter fillter() {
  // return filter;
  // }
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

  @Autowired
  TrangThaiConvert convert;

  @Autowired
  @Qualifier("filternew")
  private ProductDetailFilter filter;

  @ModelAttribute("fillter")
  public ProductDetailFilter fillter() {
    return filter;
  }

  @ModelAttribute("convert")
  public TrangThaiConvert convert() {
    return convert;
  }

  @Autowired
  @Qualifier("newrequest")
  private ProductDetailRequest productDetailRequest;
  public int rowcount = 10;
  public int[] pagenumbers;
  public String sortBy = "createdAt";
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
      @ModelAttribute("fillter") ProductDetailFilter fillter) {
    rowcount = Integer.parseInt(selectedValue);
    if (fillter != null) {
      if (fillter.filterAble()) {
        List<ProductDetail> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/table-productdetail.html";
      }
    }
    pagenumbers = service.getPanigation(rowcount, pageno);
    this.pageno = 1;
    List<ProductDetail> list = service.getPageNo(1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/table-productdetail.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir,
      @ModelAttribute("fillter") ProductDetailFilter fillter) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    if (fillter != null) {
      if (fillter.filterAble()) {
        List<ProductDetail> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/table-productdetail.html";
      }
    }
    List<ProductDetail> list = service.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
    totalpage = service.getPageNumber(rowcount);
    pagenumbers = service.getPanigation(rowcount, pageno);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/table-productdetail.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno,
      @ModelAttribute("fillter") ProductDetailFilter fillter) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    if (fillter != null) {
      if (fillter.filterAble()) {
        List<ProductDetail> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/table-productdetail.html";
      }
    }
    List<ProductDetail> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    pagenumbers = service.getPanigation(rowcount, this.pageno);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/table-productdetail.html";
  }

  // end
  @GetMapping({ "index", "" })
  public String getProductDetailIndexpages(Model model, @ModelAttribute("fillter") ProductDetailFilter fillter) {
    if (fillter != null) {
      if (fillter.filterAble()) {
        this.pageno = 1;
        List<ProductDetail> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, fillter);
        pagenumbers = service.getPanigation(rowcount, pageno, fillter);
        totalpage = service.getPageNumber(rowcount, fillter);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/productdetail/table-productdetail.html";
      }
    }
    this.pageno = 1;
    List<ProductDetail> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/productdetail/table-productdetail.html";
  }

  // @GetMapping({ "filter" })
  // public String searchAndFillter() {
  // return "/admin/pages/productdetail/table-productdetail.html";
  // }

  @ModelAttribute("productDetailRequest")
  public ProductDetailRequest setproductDetailForm() {
    return productDetailRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    productDetailRequest = new ProductDetailRequest();
    productDetailRequest.resetRequest();
    model.addAttribute("productDetailRequest", productDetailRequest);
    return "/admin/pages/productdetail/form-productdetail.html";
  }

  @GetMapping("/product/{idproductdetail}/create")
  public String goToCreateHaveProductForm(Model model, @PathVariable("idproductdetail") Integer idproductdetail) {
    productDetailRequest = new ProductDetailRequest();
    productDetailRequest.resetRequest();
    productDetailRequest.setIdProduct(idproductdetail);
    model.addAttribute("productDetailRequest", productDetailRequest);
    return "/admin/pages/productdetail/product-productdetailform.html";
  }

  @GetMapping("delete")
  public String deleteProductDetail(Model model, @RequestParam("id") String id) {
    // service.deleteProductDetail(UUID.fromString(id));
    return "redirect:index";
  }

  @GetMapping("filter")
  public String storeProductDetail(Model model, @ModelAttribute("fillter") ProductDetailFilter fillter) {
    return "redirect:index";
  }

  @GetMapping("resetfilter")
  public String resetFilter(Model model, @ModelAttribute("fillter") ProductDetailFilter fillter) {
    fillter.resetFilter();
    model.addAttribute("fillter", fillter);
    return "redirect:index";
  }

  @GetMapping("edit/{id}")
  public String editProductDetail(Model model, @PathVariable("id") Integer id) {
    model.addAttribute("productDetailRequest",
        service.getProductDetailRequetById(id));
    return "/admin/pages/productdetail/update-productdetail.html";
  }

  @PostMapping("update")
  public String updateProduct(Model model,
      @RequestParam("listimage") List<MultipartFile> listimage,
      @Valid @ModelAttribute("productDetailRequest") ProductDetailRequest productDetailRequest,
      BindingResult theBindingResult) throws IOException {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/productdetail/update-productdetail.html";
    } else {
      // if (productDetailRequest.validateHasError()) {
      // model.addAttribute("validateerrors", productDetailRequest.ValidateError());
      // return "/admin/pages/productdetail/form-voucher.html";
      // }
      service.updateProductDetail(productDetailRequest, listimage);
      return "redirect:index";
    }
  }

  @GetMapping("remove/{idproductdetail}/image/{idiamge}")
  public String removeImage(@PathVariable("idproductdetail") Integer idproductdetail,
      @PathVariable("idiamge") Integer idiamge) {
    imageService.deleted(idiamge);
    return "redirect:/admin/productdetail/edit/" + idproductdetail;
  }

  @PostMapping("store")
  public String storeProductDetail(Model model,
      @RequestParam("listimage") List<MultipartFile> listimage,
      @Valid @ModelAttribute("productDetailRequest") ProductDetailRequest productDetailRequest,
      BindingResult theBindingResult) throws IOException {
    if (theBindingResult.hasErrors()) {
      System.out.println(theBindingResult.getAllErrors());
      return "/admin/pages/productdetail/form-productdetail.html";
    } else {

      service.saveProdudct(productDetailRequest, listimage);
      return "redirect:index";
    }
  }

  @GetMapping("setactive/{id}")
  public String setactiveProductDetail(@PathVariable("id") int id) throws IOException {
    service.updateStatus(id);
    return "redirect:/admin/productdetail/index";
  }

  @GetMapping(value = "viewdetail/{id}")
  public String getMethodName(@RequestParam String param) {
    return "/admin/pages/productdetail/view-productdetail.html";
  }

}
