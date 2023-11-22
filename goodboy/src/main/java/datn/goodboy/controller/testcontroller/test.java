package datn.goodboy.controller.testcontroller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"home"})
public class test {
    @GetMapping
    public String t(Model model){
        return "user/home";
    }
}
