package datn.goodboy.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.service.ThongKeService;
import datn.goodboy.utils.convert.TrangThaiConvert;

@Controller
@RequestMapping
public class DashboardController {

    @Autowired
    ThongKeService thongKeService;
    @Autowired
    TrangThaiConvert convert;

    @ModelAttribute("convert")
    public TrangThaiConvert convert() {
        return convert;
    }

    @GetMapping(value = "admin/pages/dashboard")
    public String mmDashboard(Model model) {
        model.addAttribute("resentBill",
                thongKeService.getRecentBill(0, 10));
        return "admin/pages/dashboard.html";
    }
}
