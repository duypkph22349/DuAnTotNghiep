package datn.goodboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import datn.goodboy.model.request.EmployeeSignUpRequest;
import datn.goodboy.model.request.LoginRequest;
import datn.goodboy.model.request.UserSignUpRequest;
import datn.goodboy.security.service.SignUpService;
import datn.goodboy.utils.validate.EmailHelper;
import jakarta.validation.Valid;

@Controller
public class SecurityController {
  @Autowired
  LoginRequest loginRequest;
  @Autowired
  EmailHelper emailHelper;
  @Autowired
  SignUpService signUpService;

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

  @GetMapping("/newemployee")
  public String newEmpList() {
    return "new-employee.html";
  }

  @GetMapping("/signOut")
  public String signOutPage() {
    return "signOut";
  }

  @GetMapping("employee/signup")
  public String getSignUpPage(Model model) {
    model.addAttribute("signUpRequest", new EmployeeSignUpRequest());
    return "admin/pages-register.html";
  }

  @PostMapping("employee/signup")
  public String signUp(Model model, RedirectAttributes redirectAttributes,
      @Valid @ModelAttribute("signUpRequest") EmployeeSignUpRequest request,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/pages-register.html";
    }
    if (request.validateHasError()) {
      model.addAttribute("message", request.ValidateError());
      return "admin/pages-register.html";
    }
    if (emailHelper.isEmailExits(request.getEmail())) {
      model.addAttribute("message", "Email already exists");
      return "admin/pages-register.html";
    } else {

      try {
        if (signUpService.signUpAsEmployee(request) == null) {
          model.addAttribute("message", "Đang có lỗi xẩy ra vui lòng thử lại sau");
          return "admin/pages-register.html";
        }
        redirectAttributes.addFlashAttribute("signupsuccess", "Tạo tài khoản thành công !!!");
        return "redirect:/login";
      } catch (Exception e) {
        model.addAttribute("message", "Đang có lỗi xẩy ra vui lòng thử lại sau");
        return "admin/pages-register.html";
      }
    }
  }

  @GetMapping("user/signup")
  public String getUserSignUpPage(Model model) {
    model.addAttribute("signUpRequest", new UserSignUpRequest());
    return "user/pages-register.html";
  }

  @PostMapping("user/signup")
  public String signUp(Model model, RedirectAttributes redirectAttributes,
      @Valid @ModelAttribute("signUpRequest") UserSignUpRequest request,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "user/pages-register.html";
    }
    if (request.validateHasError()) {
      model.addAttribute("message", request.ValidateError());
      return "user/pages-register.html";
    }
    if (emailHelper.isEmailExits(request.getEmail())) {
      // TODO: process POST request
      model.addAttribute("message", "Email already exists");
      return "user/pages-register.html";
    } else {
      try {
        if (signUpService.signUpAsUser(request) == null) {
          model.addAttribute("message", "Đang có lỗi xẩy ra vui lòng thử lại sau");
          return "user/pages-register.html";
        }
        redirectAttributes.addFlashAttribute("signupsuccess", "Tạo tài khoản thành công !!!");
        return "redirect:/login";
      } catch (Exception e) {
        model.addAttribute("message", "Đang có lỗi xẩy ra vui lòng thử lại sau");
        return "user/pages-register.html";
      }
    }
    // return "redirect:/login";
  }

}
