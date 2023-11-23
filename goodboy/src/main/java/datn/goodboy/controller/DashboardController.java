package datn.goodboy.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.controller.testcontroller.ThongkeController;
import datn.goodboy.service.ThongKeService;

@Controller
@RequestMapping
public class DashboardController {
    @Autowired
    ThongKeService thongKeService;

    @GetMapping(value = "admin/pages/dashboard")
    public String mmDashboard(Model model) {
        model.addAttribute("totalProductSales",
                thongKeService.getTotalProductSale(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
                        LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59)));
        model.addAttribute("totalIncome",
                thongKeService.getToTalDoanhThu(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
                        LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59)));
        model.addAttribute("totalIncome",
                thongKeService.getToTalDoanhThu(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
                        LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59)));
        model.addAttribute("totalBill",
                thongKeService.getTotalBill(LocalDate.now().withDayOfMonth(1).atStartOfDay(),
                        LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59)));
         model.addAttribute("resentBill",
                thongKeService.getRecentBill(0,5));
        return "admin/pages/dashboard.html";
    }
}
