package datn.goodboy.controller;

import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Size;
import datn.goodboy.service.BrandService;
import datn.goodboy.service.SizeService;
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
@RequestMapping("/admin/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;
    private int currentProductCode = 1;
    @GetMapping("/dsSize")
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Size> brandPage = sizeService.findAll(pageable);
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        return "admin/pages/size/hien-thi";
    }

    @GetMapping("/search")
    public String searchByKeyWork(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name="keyword",required = false) String keyword) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Size> brandPage;
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu từ khóa không rỗng, thực hiện tìm kiếm theo từ khóa
            brandPage = sizeService.searchSizeByKeyword(keyword, pageable);
        } else {
            // Nếu không có từ khóa, lấy tất cả thương hiệu
            brandPage = sizeService.findAll(pageable);
        }

        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        model.addAttribute("keyword", keyword); // Truyền từ khóa để hiển thị lại trên giao diện
        return "admin/pages/size/hien-thi";
    }



    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        return "admin/pages/brand/create-brand";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("brand",sizeService.getById(id));
        return "admin/pages/size/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid Size b, @PathVariable Integer id) {
        b.setUpdatedAt(LocalDateTime.now());
        sizeService.update(id, b);
        return "redirect:/admin/size/dsSize";
    }

    @PostMapping("/add")
<<<<<<< HEAD
    public ResponseEntity<?> add( @RequestBody Size b, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        String newProductCode = "BR" + String.format("%d", currentProductCode);
=======
    public String add(Model model,@Valid Size b, BindingResult result) {
        String newProductCode = "SZ_" + String.format("%d", currentProductCode);
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
        b.setCode(newProductCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        sizeService.add(b);
        return "redirect:/admin/size/dsSize";
    }
}
