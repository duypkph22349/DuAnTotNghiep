package datn.goodboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.VertifyEmail;
import datn.goodboy.model.request.EmployeeSignUpRequest;
import datn.goodboy.model.request.ResetPasswordRequest;
import datn.goodboy.model.request.UserSignUpRequest;
import datn.goodboy.security.service.SercurityService;
import datn.goodboy.security.service.SignUpService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmailService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.VertifyEmailService;
import datn.goodboy.utils.validate.EmailHelper;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SecurityController {
  @Autowired
  EmailHelper emailHelper;
  @Autowired
  SignUpService signUpService;

  @Autowired
  SercurityService securityService;
  @Autowired
  VertifyEmailService vertifyEmailsService;
  @Autowired
  EmailService emailService;
  @Autowired
  EmployeeService empService;
  @Autowired
  CustomerService cusService;

  @GetMapping("/login")
  public String loginPage(Model model) {
    return "login.html";
  }

  @GetMapping("/employee/login")
  public String loginEmployeePage(Model model) {
    return "admin/login.html";
  }

  @GetMapping("/user/login")
  public String loginCustomePage(Model model) {
    return "user/login.html";
  }

  @GetMapping("/login-fail")
  public String loginFail(Model model,
      RedirectAttributes redirAttrs) {
    redirAttrs.addFlashAttribute("message", "Email or Password is incorrect!!!");
    return "redirect:/login";
  }

  @GetMapping("employee/login-fail")
  public String loginEmployeeFail(Model model,
      RedirectAttributes redirAttrs) {
    redirAttrs.addFlashAttribute("message", "Email or Password is incorrect!!!");
    return "redirect:/employee/login";
  }

  @GetMapping("/user/login-fail")
  public String loginUserFail(Model model,
      RedirectAttributes redirAttrs) {
    redirAttrs.addFlashAttribute("message", "Email or Password is incorrect!!!");
    return "redirect:/user/login";
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

  @PreAuthorize("hasAuthority('NOT_ACCTIVE')")
  @GetMapping("vertifyemail")
  public String vertifyPage(Model model, RedirectAttributes thRedirectAttributes) {
    model.addAttribute("vertifyemail", new VertifyEmail());
    return "active-email.html";
  }

  @PreAuthorize("hasAuthority('NOT_ACCTIVE')")
  @GetMapping("sendvertifyemail")
  public String sendVertifyAcc(Model model, RedirectAttributes thRedirectAttributes) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      emailService.activeEmailMessage(
          vertifyEmailsService.createVertifyEmail(username));
      thRedirectAttributes.addFlashAttribute("message", "Vui lòng kiểm tra email!!!");
      model.addAttribute("vertifyemail", new VertifyEmail());
      return "redirect:/vertifyemail";
    }
    return "redirect:/signOut";
  }

  @PreAuthorize("hasAuthority('NOT_ACCTIVE')")
  @PostMapping("vertifyemail")
  public String vertifyAccount(Model model, RedirectAttributes thRedirectAttributes,
      @ModelAttribute("vertifyemail") VertifyEmail vertifyemail) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      vertifyemail.setEmail(username);
      if (vertifyEmailsService.vertifyEmail(vertifyemail)) {
        securityService.ActiveAccount();
        thRedirectAttributes.addFlashAttribute("message", "Xác thực thành công vui lòng đăng nhập lại!!!");
        return "redirect:/signOut";
      } else {
        thRedirectAttributes.addFlashAttribute("message",
            "Xác thực không thành công mã kích hoạt không đúng hoặc đã hết hạn vui lòng thử lại !!!");
      }
    }
    return "redirect:/vertifyemail";
  }

  @GetMapping("forgotpassword")
  public String findAccount(Model model, RedirectAttributes thRedirectAttributes) {
    model.addAttribute("vertifyemail", new VertifyEmail());
    return "find-account.html";
  }

  @PostMapping("sendresetpasswordcode")
  public String sendResetPasswordCode(Model model, RedirectAttributes thRedirectAttributes,
      @RequestParam("email") String email) {
    if (emailHelper.isEmailNotExsits(email)) {
      model.addAttribute("message", "Email Chưa đăng ký tài khoản nào!");
      return "find-account.html";
    } else {
      emailService.resetEmailMessage(vertifyEmailsService.createVertifyEmail(email));
      securityService.setAuthentichByEmail(email);
      thRedirectAttributes.addFlashAttribute("message", "Vui lòng kiểm tra email!!!");
      model.addAttribute("vertifyemail", new VertifyEmail());
      return "reset-password-code.html";
    }
  }

  @PostMapping("/resetpasswordcode")
  public String resetPasswordCode(
      Model model,
      RedirectAttributes thRedirectAttributes,
      @ModelAttribute("vertifyemail") VertifyEmail vertifyemail) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String username = userDetails.getUsername();
      vertifyemail.setEmail(username);
      if (vertifyEmailsService.vertifyEmail(vertifyemail)) {
        thRedirectAttributes.addFlashAttribute("message", "Xác thực thành công nhập mật khẩu mới!!!");
        return "redirect:/resetpassword";
      }
    }
    thRedirectAttributes.addFlashAttribute("message",
        "Xác thực không thành công mã kích hoạt không đúng hoặc đã hết hạn vui lòng thử lại !!!");
    return "redirect:/resetpasswordcode";
  }

  @GetMapping("resetpassword")
  public String resetPasswordPage(Model model, RedirectAttributes thRedirectAttributes) {
    model.addAttribute("requestPasswordRequest", new ResetPasswordRequest());
    return "reset-password.html";
  }

  @GetMapping("/access-denied")
  public String getAccessDenied() {
    return "pages-error-403.html";
  }

  @GetMapping("/updateprofile/employee")
  public String getUpdateIdForm(Model model, HttpSession session) {
    Employee authenEmployee = (Employee) session.getAttribute("authenemployee");
    model.addAttribute("requset", authenEmployee);
    return "concac";
  }

  @PostMapping("/updateprofile/employee")
  public String updateEmpInfo(Model model, @Valid @ModelAttribute("requset") Employee employee, HttpSession session) {

    session.setAttribute("authenemployee", empService.saveEmployee(employee));
    return "concac";
  }
}
