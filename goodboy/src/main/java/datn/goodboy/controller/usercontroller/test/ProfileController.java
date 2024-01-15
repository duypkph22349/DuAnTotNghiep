package datn.goodboy.controller.usercontroller.test;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.request.CustomerRequest;
import datn.goodboy.model.request.UserChangePassword;
import datn.goodboy.service.AccountService;
import datn.goodboy.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    AccountService service;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRequest customerRequest;

    @GetMapping("/shop/profile")
    public String profile(Model model, Principal principal) {
        String email = principal.getName();
        Account account = service.findAccountByEmail(email);
        Customer customer = customerService.getCustomerByAccount(account.getId());
        model.addAttribute("edit", customer);
        return "/user2/profile";
    }
    @ModelAttribute("edit")
    public CustomerRequest getCustomer() {
        return customerRequest;
    }
    @GetMapping("/shop/edit_profile")
    public String ediProfile(Model model, Principal principal) {
        String email = principal.getName();
        Account account = service.findAccountByEmail(email);
        Customer customer = customerService.getCustomerByAccount(account.getId());
        model.addAttribute("edit", customer);
        return "/user2/edit_profile";
    }

    @PostMapping("/shop/edit_profile")
    public String edit(@ModelAttribute("edit") CustomerRequest customer) {
        customerService.updateCustomer(customer);
        return "redirect:/shop/profile";
    }
}
