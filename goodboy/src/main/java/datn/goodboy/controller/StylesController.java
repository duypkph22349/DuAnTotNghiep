package datn.goodboy.controller;
import datn.goodboy.model.entity.Styles;
import datn.goodboy.service.StylesService;
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
@RequestMapping("/admin/style")
public class StylesController {
    @Autowired
    private StylesService stylesService;
    private int currentProductCode = 1;
    @GetMapping("/dsStyle")
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Styles> brandPage = stylesService.findAll(pageable);
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        return "admin/pages/styles/hien-thi";
    }
    @GetMapping("/search")
    public String searchByKeyWork(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name="keyword",required = false) String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Styles> brandPage;
        if (keyword != null && !keyword.isEmpty()) {
            // Nếu từ khóa không rỗng, thực hiện tìm kiếm theo từ khóa
            brandPage = stylesService.searchStylesByKeyword(keyword, pageable);
        } else {
            // Nếu không có từ khóa, lấy tất cả thương hiệu
            brandPage = stylesService.findAll(pageable);
        }
        model.addAttribute("totalPage", brandPage.getTotalPages());
        model.addAttribute("brandPage", brandPage.getContent());
        model.addAttribute("keyword", keyword); // Truyền từ khóa để hiển thị lại trên giao diện
        return "admin/pages/stypes/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        return "admin/pages/brand/create-brand";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("brand",stylesService.getById(id));
        return "admin/pages/stypes/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid Styles b, @PathVariable Integer id) {
        b.setUpdatedAt(LocalDateTime.now());
        stylesService.update(id, b);
        return "redirect:/admin/stype/dsStype";
    }

    @PostMapping("/add")
    public String add(Model model,@Valid Styles b, BindingResult result) {
        String newProductCode = "ST" + String.format("%d", currentProductCode);
        b.setCode(newProductCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        stylesService.add(b);
        return "redirect:/admin/style/dsStype";
    }
}
