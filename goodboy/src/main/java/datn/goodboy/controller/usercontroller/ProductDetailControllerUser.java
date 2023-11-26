package datn.goodboy.controller.usercontroller;

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
import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class ProductDetailControllerUser {

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
                return "user/product";
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
        return "user/product";
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
                return "user/product";
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
        return "user/product";
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
                return "user/product";
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
        return "user/product";
    }

    // end
    @GetMapping("products")
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
                return "user/product";
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
        return "user/product";
    }

    @ModelAttribute("productDetailRequest")
    public ProductDetailRequest setproductDetailForm() {
        return productDetailRequest;
    }

    @PostMapping("store")
    public String storeProductDetail(Model model,
                                     @RequestParam("listimage") List<MultipartFile> listimage,
                                     @Valid @ModelAttribute("productDetailRequest") ProductDetailRequest productDetailRequest,
                                     BindingResult theBindingResult) throws IOException {
        if (theBindingResult.hasErrors()) {
            return "user/product";
        } else {

            service.saveProdudct(productDetailRequest, listimage);
            return "redirect:products";
        }
    }

    @GetMapping("detail/{id}")
    public String editProductDetail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("productDetailRequest",
                service.getProductDetailById(id).get());
        return "user/product_detail";
    }

}
