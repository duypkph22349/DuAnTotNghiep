package datn.goodboy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.VertifyEmail;
import datn.goodboy.repository.VertifyEmailRepository;
import datn.goodboy.utils.validate.EmailHelper;

@Service
public class VertifyEmailService {
  @Autowired
  EmailHelper checkEmailHelper;
  @Autowired
  VertifyEmailRepository repository;
  @Autowired
  EmailService emailService;

  public VertifyEmail getVertifyEmail(String email) {
      Optional<VertifyEmail> vertifyEmailOpl = repository.findById(email);
      if (vertifyEmailOpl.isPresent()) {
        return vertifyEmailOpl.get();
      }
    return null;
  }

  public VertifyEmail createVertifyEmail(String email) {
      VertifyEmail newVertifyEmail = repository.save(new VertifyEmail(email));
      return newVertifyEmail;
  }

  public boolean vertifyEmail(VertifyEmail vertifyEmail) {
    VertifyEmail vertifyEmail2 = this.getVertifyEmail(vertifyEmail.getEmail());
    if (vertifyEmail2.checkCode(vertifyEmail.getCode())) {
      return true;
    }
    return false;
  }
}
