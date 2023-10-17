package datn.goodboy.controller;

import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Product;
import datn.goodboy.service.ColorService;
<<<<<<< HEAD
import datn.goodboy.service.ProductService;
=======
import jakarta.validation.Valid;
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/color")
public class ColorController {
    @Autowired
    private ColorService colorService;
    private int currentProductCode = 1;
    @GetMapping("/dsColor")
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Color> brandPage = colorService.findAllColor(pageable);
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("colorPage", brandPage.getContent());
        return "admin/pages/color/hien-thi";
    }

    @GetMapping("/search")
    public String searchByKeyWork(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name="keyword",required = false) String keyword) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Color> colorPage;
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu từ khóa không rỗng, thực hiện tìm kiếm theo từ khóa
            colorPage = colorService.searchColorByKeyword(keyword, pageable);
        } else {
            // Nếu không có từ khóa, lấy tất cả thương hiệu
            colorPage = colorService.findAllColor(pageable);
        }
        model.addAttribute("totalPage", colorPage.getTotalPages());
        model.addAttribute("brandPage", colorPage.getContent());
        model.addAttribute("keyword", keyword); // Truyền từ khóa để hiển thị lại trên giao diện
        return "admin/pages/color/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        return "admin/pages/brand/create-brand";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("brand",colorService.getById(id));
        return "admin/pages/color/update-color";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid Color b, @PathVariable Integer id) {
        b.setUpdatedAt(LocalDateTime.now());
        colorService.update(id, b);
        return "redirect:/admin/color/dsColor";
    }

    @PostMapping("/add")
<<<<<<< HEAD
    public ResponseEntity<?> add(@RequestBody Color b, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        String newProductCode = "BR" + String.format("%d", currentProductCode);
=======
    public String add(Model model,@Valid Color b, BindingResult result) {
        String newProductCode = "MS" + String.format("%d", currentProductCode);
>>>>>>> 4910e2f486bfd3cf157ffe4f529c246ca1b2134f
        b.setCode(newProductCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        colorService.add(b);
        return "redirect:/admin/color/dsColor";
    }
}
