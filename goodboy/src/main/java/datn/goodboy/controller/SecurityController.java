package datn.goodboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import datn.goodboy.model.request.LoginRequest;

@Controller
public class SecurityController {
  @Autowired
  LoginRequest loginRequest;

  @ModelAttribute("loginRequest")
  LoginRequest loginReqest() {
    return loginRequest;
  }

  @GetMapping("/login")
  public String loginPage(Model model) {
    loginRequest = new LoginRequest();
    model.addAttribute("loginRequest", loginRequest);

    return "login.html";
  }

  @GetMapping("/login-fail")
  public String loginFail(Model model,
      RedirectAttributes redirAttrs) {
    redirAttrs.addFlashAttribute("message", "Email or Password is incorrect!!!");
    return "redirect:/login";
  }

  @GetMapping("/homepage")
  public String loggedInPage() {
    return "redirect:/admin";
  }

  @GetMapping("/signOut")
  public String signOutPage() {
    return "signOut";
  }

}
