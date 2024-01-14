package datn.goodboy.controller.usercontroller.test;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.request.UserChangePassword;
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

        return "/user2/profile";
    }

    @GetMapping("/shop/edit_profile")
    public String ediProfile() {

        return "/user2/edit_profile";
    }

    @PostMapping("/shop/edit_profile")
    public String edit(@ModelAttribute("edit") Account account) {
        service.saveAccount(account);
        return "redirect:/shop/profile";
    }
}
