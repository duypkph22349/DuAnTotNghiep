package datn.goodboy.controller.usercontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.ProductDetailFilter;
import datn.goodboy.model.request.ProductDetailRequest;
import datn.goodboy.service.BrandService;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.ColorService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.ImageProductService;
import datn.goodboy.service.MaterialService;
import datn.goodboy.service.OriginService;
import datn.goodboy.service.PatternTypeService;
import datn.goodboy.service.ProductDetailService;
import datn.goodboy.service.ProductService;
import datn.goodboy.service.SizeService;
import datn.goodboy.service.StylesService;
import datn.goodboy.utils.convert.TrangThaiConvert;

@Controller
@RequestMapping("/shop/product/")
public class ProductDetailControllerUser {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductDetailService productDetailService;
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
    private CartDetailService cartDetailService;
    // @ModelAttribute("brand-combobox")
    // public ProductDetailFilter fillter() {
    // return filter;
    // }
    @Autowired
    private ImageProductService imageProductService;

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
    private CustomerService customerService;
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

    @GetMapping({ "index", "" })
    public String getIndexpage(Model model) {
        this.pageno = 1;
        this.rowcount = 10;
        this.sortBy = "createdAt";
        this.sortDir = false;
        this.pagenumbers = service.getPanigation(rowcount, pageno);
        List<Product> list = service.getPageNo(1, rowcount, sortBy, sortDir);
        this.totalpage = service.getPageNumber(rowcount);
        model.addAttribute("totalpage", this.totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", this.pagenumbers);
        model.addAttribute("crpage", this.pageno);
        model.addAttribute("rowcount", this.rowcount);
        return "user/product.html";
    }

    // panigation and sort
    @GetMapping("getcountrow")
    public String getCountRow(Model model, @RequestParam("selectedValue") String selectedValue) {
        rowcount = Integer.parseInt(selectedValue);
        pagenumbers = service.getPanigation(rowcount, pageno);
        this.pageno = 1;
        List<Product> list = service.getPageNo(1, rowcount, sortBy, sortDir);
        totalpage = service.getPageNumber(rowcount);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "user/product.html";
    }

    @GetMapping("sort")
    public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
            @RequestParam("sortDir") boolean sordir) {
        this.sortBy = sortby;
        this.sortDir = sordir;
        this.pageno = 1;
        List<Product> list = service.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
        totalpage = service.getPageNumber(rowcount);
        pagenumbers = service.getPanigation(rowcount, pageno);
        model.addAttribute("list", list);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "user/product";
    }

    @GetMapping("page")
    public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
        if (pageno <= 1) {
            this.pageno = 1;
            pageno = 1;
        }
        this.pageno = pageno;
        List<Product> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
        totalpage = service.getPageNumber(rowcount);
        model.addAttribute("totalpage", totalpage);
        pagenumbers = service.getPanigation(rowcount, this.pageno);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", this.pageno);
        model.addAttribute("list", list);
        model.addAttribute("rowcount", rowcount);
        return "user/product";
    }

    @GetMapping("detail/{id}")
    public String getProductDetailPage(Model model, @PathVariable("id") int id) {
        Product product = service.getById(id);
        model.addAttribute("product", product);
        return "user/product_detail.html";
    }

    @PostMapping("addtocart/{id}")
    public String addtocart(Model model, @PathVariable("id") Integer id, @RequestParam int quantity) {
        ProductDetail productDetail = productDetailService.getProductDetailById(id).orElse(null);
        if (productDetail != null) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProductDetail(productDetail);
            cartDetail.setQuantity(quantity);
            cartDetailService.saveCart(cartDetail);
        }
        System.out.println(productDetail);

        return "user/cart";
    }
}
