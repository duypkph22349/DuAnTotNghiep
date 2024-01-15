package datn.goodboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import datn.goodboy.model.entity.VertifyEmail;
import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.entity.Bill;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.VoucherRepository;
import datn.goodboy.utils.convert.TrangThaiConvert;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Component
public class EmailService {
  @Autowired
  TemplateEngine templateEngine;
  @Autowired
  private JavaMailSender emailSender;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private VoucherRepository vouhcerRepository;
  @Autowired
  private BillRepository billRepository;

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

  @Transactional
  public void sendVoucherMail(Integer idvoucher, String title) {
    MimeMessage message = emailSender.createMimeMessage();
    Voucher voucher = vouhcerRepository.findById(idvoucher).orElse(null);
    accountRepository.findAll().stream().forEach(
        cutomer -> {
          try {
            System.out.println(
                "Start sending mail for custmoer: " + cutomer.getEmail());
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("thatdeptraivjpro@26kleft.com");
            helper.setTo(cutomer.getEmail());
            helper.setSubject(title);
            Context context = new Context();
            context.setVariable("fullNameCustomer", cutomer.getCustomer().getName());
            context.setVariable("codeVoucher", voucher.getCode());
            context.setVariable("valueVoucher", voucher.getDiscountValue());
            context.setVariable("conditionVoucher", voucher.getConditionVoucher());
            context.setVariable("startTime", voucher.getStart_time());
            context.setVariable("endTime", voucher.getEnd_time());
            context.setVariable("message", "Chúc quý khách có trải nghiệm mua sắm vui vẻ <3333");
            String htmlCode = templateEngine.process("mail/template-voucher", context);
            helper.setText(htmlCode, true);
            emailSender.send(message);
          } catch (MessagingException e) {
          }
        });
  }

  @Transactional
  public void sendEmailBill(int idbill, String title) {
    MimeMessage message = emailSender.createMimeMessage();
    try {
      Bill bill = billRepository.findById(idbill).orElse(null);

      if (bill != null) {
        // Eagerly fetch associated entities
        bill.getCustomer().getAccount();
        bill.getCode();

        System.out.println("Start sending bill mail for customer: " +
            bill.getCustomer().getAccount().getEmail());

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("thatdeptraivjpro@26kleft.com");
        helper.setTo(bill.getCustomer().getAccount().getEmail());
        helper.setSubject(title);

        Context context = new Context();
        context.setVariable("bill", bill);
        context.setVariable("convert", new TrangThaiConvert());
        String htmlCode = templateEngine.process("mail/billTemplate", context);
        helper.setText(htmlCode, true);

        emailSender.send(message);
      }
    } catch (MessagingException e) {
      // Handle the exception as needed
      System.out.println("Error sending bill mail for customer: " + e);
    }
  }

  @Transactional
  public void sendEmailBillWithEmailAnother(int idbill, String title, String email) {
    MimeMessage message = emailSender.createMimeMessage();
    try {
      Bill bill = billRepository.findById(idbill).orElse(null);

      if (bill != null) {
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("thatdeptraivjpro@26kleft.com");
        helper.setTo(email);
        helper.setSubject(title);

        Context context = new Context();
        context.setVariable("bill", bill);
        context.setVariable("convert", new TrangThaiConvert());
        String htmlCode = templateEngine.process("mail/billTemplate", context);
        helper.setText(htmlCode, true);

        emailSender.send(message);
      }
    } catch (MessagingException e) {
      // Handle the exception as needed
      System.out.println("Error sending bill mail for customer: " + e);
    }
  }
}
