package datn.goodboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import datn.goodboy.model.entity.VertifyEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {
  @Autowired
  TemplateEngine templateEngine;
  @Autowired
  private JavaMailSender emailSender;

  public void activeEmailMessage(VertifyEmail request) {
    MimeMessage message = emailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setFrom("thatdeptraivjpro@26kleft.com");
      helper.setTo(request.getEmail());
      helper.setSubject("Active Email");

      Context context = new Context();
      context.setVariable("code", request.getCode());
      String htmlCode = templateEngine.process("mail/activeCode", context);

      // Set the HTML content of the email
      helper.setText(htmlCode, true);

      emailSender.send(message);
    } catch (MessagingException e) {
      // Handle exception
    }
  }

  public void resetEmailMessage(VertifyEmail request) {
    MimeMessage message = emailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setFrom("thatdeptraivjpro@26kleft.com");
      helper.setTo(request.getEmail());
      helper.setSubject("Khôi phục mật khẩu Email");

      Context context = new Context();
      context.setVariable("code", request.getCode());
      String htmlCode = templateEngine.process("mail/resetCode", context);

      // Set the HTML content of the email
      helper.setText(htmlCode, true);
      emailSender.send(message);
    } catch (MessagingException e) {
      // Handle exception
    }
  }
}
