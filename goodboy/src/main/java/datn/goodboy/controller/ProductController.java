package datn.goodboy.controller;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Product;
import datn.goodboy.service.BrandService;
import datn.goodboy.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
<<<<<<< HEAD
//    @Autowired
//    private ProductService productService;
//    private int currentProductCode = 1;
//    @GetMapping("/hien-thi")
//    public ResponseEntity<Page<Product>> hienThi(@RequestParam(defaultValue = "1") int page) {
//        Pageable pageable = PageRequest.of(page - 1, 5);
//        return ResponseEntity.ok().body(productService.findAll(pageable));
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<?> add( @RequestBody Product b, BindingResult result) {
//        if (result.hasErrors()) {
//            List<ObjectError> objectErrorList = result.getAllErrors();
//            return ResponseEntity.ok(objectErrorList);
//        }
//        String newProductCode = "BR" + String.format("%d", currentProductCode);
//        b.setCode(newProductCode);
//        b.setCreatedAt(LocalDateTime.now());
//        b.setUpdatedAt(LocalDateTime.now());
//        b.setStatus(1);
//        currentProductCode++;
//        return ResponseEntity.ok(productService.add(b));
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update( @RequestBody Product b, @PathVariable Integer id, BindingResult result) {
//        if (result.hasErrors()) {
//            List<ObjectError> objectErrorList = result.getAllErrors();
//            return ResponseEntity.ok(objectErrorList);
//        }
//        b.setUpdatedAt(LocalDateTime.now());
//        return ResponseEntity.ok(productService.update(id,b));
//    }
=======
    @Autowired
    private ProductService productService;
    private int currentProductCode = 1;
    @GetMapping("/dsProduct")
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> brandPage = productService.findAll(pageable);
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        return "admin/pages/product/hien-thi";
    }

    @GetMapping("/search")
    public String searchByKeyWork(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name="keyword",required = false) String keyword) {

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
        model.addAttribute("brand",productService.getById(id));
        return "admin/pages/product/update-product";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid Product b, @PathVariable Integer id) {
        b.setUpdatedAt(LocalDateTime.now());
        productService.update(id, b);
        return "redirect:/admin/product/dsProduct";
    }

    @PostMapping("/add")
    public String add(Model model,@Valid Product b, BindingResult result) {
        String newProductCode = "SP" + String.format("%d", currentProductCode);
        b.setCode(newProductCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        productService.add(b);
        return "redirect:/admin/product/dsProduct";
    }
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
}
