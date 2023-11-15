package datn.goodboy.controller.testcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import datn.goodboy.config.JwtTokenProvider;
import datn.goodboy.model.entity.Account;
import datn.goodboy.model.request.LoginRequest;
import datn.goodboy.model.request.SingupRequest;
import datn.goodboy.service.AccountService;

@RestController
@RequestMapping("test/login")
public class TestLoginRestController {
  @Autowired
  AccountService service;
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @PostMapping("signup")
  public ResponseEntity<Account> signUp(@RequestBody SingupRequest request) {
    return ResponseEntity.ok().body(service.singup(request));
  }

  @PostMapping("login")
  public ResponseEntity<String> Loogin(@RequestBody LoginRequest request) {
    Authentication authen = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    String token = jwtTokenProvider.generateToken(authen);
    return ResponseEntity.ok().body(token);
  }

  @PostMapping("validatetoken")
  public ResponseEntity<String> validateToken(@RequestBody String token) {
    if (jwtTokenProvider.validateToken(token)) {
      return ResponseEntity.ok().body("login succes");
    } else {
      return ResponseEntity.ok().body("login unsuccess");
    }
  }
}
