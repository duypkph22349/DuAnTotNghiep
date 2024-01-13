package datn.goodboy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import datn.goodboy.model.entity.Product;
import datn.goodboy.model.request.ProductRequest;
import datn.goodboy.service.BrandService;
import datn.goodboy.service.CategoryService;
import datn.goodboy.service.ImageProductService;
import datn.goodboy.service.MaterialService;
import datn.goodboy.service.OriginService;
import datn.goodboy.service.ProductService;
import datn.goodboy.service.StylesService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    TrangThaiConvert convert;

    @Autowired
    @ModelAttribute("convert")
    public TrangThaiConvert convert() {
        return convert;
    }

    @Autowired
    private MaterialService materialService;

    @Autowired
    private OriginService originService;

    @Autowired
    private StylesService stylesService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageProductService imageService;

    @ModelAttribute("brandCbb")
    public List<Map<Integer, String>> getComboboxBrand() {
        return brandService.getCombobox();
    }

    @ModelAttribute("materialCbb")
    public List<Map<Integer, String>> getComboboxMaterial() {
        return materialService.getCombobox();
    }

    @ModelAttribute("categoryCbb")
    public List<Map<Integer, String>> getComboboxCategory() {
        return categoryService.getCombobox();
    }

    @ModelAttribute("originCbb")
    public List<Map<Integer, String>> getComboboxOrigin() {
        return originService.getCombobox();
    }

    @ModelAttribute("stylesCbb")
    public List<Map<Integer, String>> getComboboxStyles() {
        return stylesService.getCombobox();
    }

    @GetMapping({ "/dsProduct", "" })
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> brandPage = productService.findAll(pageable);
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        return "admin/pages/product/hien-thi";
    }

    @GetMapping("/search")
    public String searchByKeyWork(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "keyword", required = false) String keyword) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> brandPage;
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu từ khóa không rỗng, thực hiện tìm kiếm theo từ khóa
            brandPage = productService.searchProductByKeyword(keyword, pageable);
        } else {
            // Nếu không có từ khóa, lấy tất cả thương hiệu
            brandPage = productService.findAll(pageable);
        }
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        model.addAttribute("keyword", keyword); // Truyền từ khóa để hiển thị lại trên giao diện
        return "admin/pages/product/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        return "admin/pages/brand/create-brand";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("entity", productService.getRequest(id));
        return "admin/pages/product/update-product";
    }

    @PostMapping("/update")
    public String update(Model model, @Valid @ModelAttribute("entity") ProductRequest request,
            @RequestParam("listimage") List<MultipartFile> listimage, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "/admin/pages/product/update-product";
        } else {
            // if (productDetailRequest.validateHasError()) {
            // model.addAttribute("validateerrors", productDetailRequest.ValidateError());
            // return "/admin/pages/productdetail/form-voucher.html";
            // }
            productService.updateProduct(request, listimage);
            return "redirect:/admin/product/dsProduct";
        }
    }

    @GetMapping("remove/{idproduct}/image/{idiamge}")
    public String removeImage(@PathVariable("idproduct") Integer idproduct,
            @PathVariable("idiamge") Integer idiamge) {
        imageService.deleted(idiamge);
        return "redirect:/admin/product/view-update/" + idproduct;
    }

    @PostMapping("/add")
    public String add(Model model, @Valid Product b, BindingResult result) {
        b.setStatus(1);
        productService.add(b);
        return "redirect:/admin/product/dsProduct";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product/dsProduct";
    }

    @GetMapping("/dicription/update/{id}")
    public String updateDiscription(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("product", productService.getById(id));
        return "admin/pages/product/dicription";
    }

    @PostMapping("/dicription/update")
    public String SaveDiscription(Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("product", productService.saveDicription(product));
        return "redirect:/admin/managerproduct";
    }
}
