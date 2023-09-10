package datn.goodboy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Account;
import datn.goodboy.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/test")
public class TestController {
  @Autowired
  AccountService accountService;

  @GetMapping(value = "account/getall")
  public ResponseEntity<List<Account>> getAllAccounts(@RequestParam String param) {
    return ResponseEntity.ok().body(accountService.getAllAccounts());
  }

}
