package datn.goodboy.exeption.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SecurityHandleController {
  @ExceptionHandler(UsernameNotFoundException.class)
  public String userNotFoundException(UsernameNotFoundException ex, Model model) {
    model.addAttribute("usernotfounmessage", ex.getMessage());
    return "login.html";
  }
}
