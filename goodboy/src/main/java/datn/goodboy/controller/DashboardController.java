package datn.goodboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin/pages")
public class DashboardController {

    @GetMapping(value = "dashboard")
    public ModelMap mmDashboard(Model model) {
        model.addAttribute("message", "hello this is meessage form controller ");
        return new ModelMap();
    }

    @GetMapping(value = "form-elements")
    public ModelMap mmFormElements() {
        return new ModelMap();
    }

}
