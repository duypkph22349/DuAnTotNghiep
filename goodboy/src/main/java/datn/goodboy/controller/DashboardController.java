package datn.goodboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DashboardController {

    @GetMapping(value = "admin/pages/dashboard")
    public String mmDashboard(Model model) {
        return "admin/pages/dashboard.html";
    }
}
