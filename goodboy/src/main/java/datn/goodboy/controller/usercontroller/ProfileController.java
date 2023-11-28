package datn.goodboy.controller.usercontroller;

import datn.goodboy.model.entity.Account;
import datn.goodboy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    AccountService service;

    @GetMapping("/shop/profile")
    public String profile() {

        return "/user/profile";
    }

    @GetMapping("/shop/edit_profile")
    public String ediProfile() {

        return "/user/edit_profile";
    }

    @PostMapping("/shop/edit_profile")
    public String edit(@ModelAttribute("edit") Account account) {
        service.saveAccount(account);
        return "redirect:/shop/profile";
    }

    @GetMapping("/shop/change_password")
    public String change_password(Model model) {
        model.addAttribute("change_password", new Account());
        return "/user/change_password";
    }

    @PostMapping("/doimatkhau/{id}")
    public String doiMatKhau(@PathVariable("id") Integer id, @RequestParam("newpassword") String password, @RequestParam("newconfirm") String newconfirm,@ModelAttribute("doimatkhau") Account account) {

        if (password == newconfirm) {
            service.saveAccount(account);
        }
        return "redirect:/shop/profile";
    }
}
