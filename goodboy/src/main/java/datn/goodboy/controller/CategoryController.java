package datn.goodboy.controller;

import datn.goodboy.model.entity.Category;
import datn.goodboy.model.entity.Color;
import datn.goodboy.model.entity.Material;
import datn.goodboy.model.entity.Origin;
import datn.goodboy.service.CategoryService;
import datn.goodboy.service.ColorService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;



    @GetMapping("/dsCategory")
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Category> brandPage = categoryService.getPage(pageable);
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("categoryPage", brandPage.getContent());
        return "admin/pages/category/hien-thi";
    }

    @GetMapping("/search")
    public String searchByKeyWork(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name="keyword",required = false) String keyword) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Category> brandPage;
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu từ khóa không rỗng, thực hiện tìm kiếm theo từ khóa
            brandPage = categoryService.searchCategoryByKeyword(keyword, pageable);
        } else {
            // Nếu không có từ khóa, lấy tất cả thương hiệu
            brandPage = categoryService.getPage(pageable);
        }

        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        model.addAttribute("keyword", keyword); // Truyền từ khóa để hiển thị lại trên giao diện
        return "admin/pages/category/hien-thi";
    }


    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("categoryU",categoryService.getById(id));
        return "admin/pages/category/update-category";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid Category b, @PathVariable Integer id) {
        b.setUpdatedAt(LocalDateTime.now());
        categoryService.update(id, b);
        return "redirect:/admin/category/dsCategory";
    }

    @PostMapping("/add")
    public String add(Model model,@Valid Category b, BindingResult result) {
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        categoryService.add(b);
        return "redirect:/admin/category/dsCategory";
    }

}
