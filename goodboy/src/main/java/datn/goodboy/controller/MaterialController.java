package datn.goodboy.controller;

import java.time.LocalDateTime;

import datn.goodboy.utils.convert.TrangThaiConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import datn.goodboy.model.entity.Material;
import datn.goodboy.service.MaterialService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    private int currentProductCode = 1;
    @Autowired
    TrangThaiConvert convert;

    @ModelAttribute("convert")
    public TrangThaiConvert convert() {
        return convert;
    }
    @GetMapping("/dsMaterial")
    public String hienThi(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Material> brandPage = materialService.findAllMaterial(pageable);
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        return "admin/pages/material/hien-thi";
    }

    @GetMapping("/search")
    public String searchByKeyWork(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name="keyword",required = false) String keyword) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Material> brandPage;
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu từ khóa không rỗng, thực hiện tìm kiếm theo từ khóa
            brandPage = materialService.searchMaterialByKeyword(keyword, pageable);
        } else {
            // Nếu không có từ khóa, lấy tất cả thương hiệu
            brandPage = materialService.findAllMaterial(pageable);
        }

        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        model.addAttribute("keyword", keyword); // Truyền từ khóa để hiển thị lại trên giao diện
        return "admin/pages/material/hien-thi";
    }



    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        return "admin/pages/brand/create-brand";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("brand",materialService.getById(id));
        return "admin/pages/material/update-material";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid Material b, @PathVariable Integer id) {
        b.setUpdatedAt(LocalDateTime.now());
        materialService.update(id, b);
        return "redirect:/admin/material/dsMaterial";
    }

    @PostMapping("/add")
    public String add(Model model,@Valid Material b, BindingResult result) {

        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        materialService.add(b);
        return "redirect:/admin/material/dsMaterial";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam("id") Integer id) {
        materialService.deleteMaterial(id);
        return "redirect:/admin/material/dsMaterial";
    }
}
