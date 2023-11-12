package datn.goodboy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.request.SingupRequest;
import datn.goodboy.model.response.CustomerResponse;
import datn.goodboy.service.AccountService;
import datn.goodboy.service.CustomerService;

@RestController
@RequestMapping("api/test")
public class TestController {
  @Autowired
  AccountService accountService;
  @Autowired
  CustomerService customerService;

  @GetMapping(value = "account/getall")
  public ResponseEntity<List<Account>> getAllAccounts() {
    return ResponseEntity.ok().body(accountService.getAllAccounts());
  }

  @GetMapping(value = "customer/getall")
  public ResponseEntity<List<Customer>> getAllCustomer() {
    return ResponseEntity.ok().body(customerService.getAllCustomers());
  }

  @GetMapping(value = "customer/getpageno/{pageno}")
  public ResponseEntity<List<CustomerResponse>> GetPageNo(@PathVariable("pageno") int pageno) {
    return ResponseEntity.ok().body(customerService.getPageNo(pageno));
  }

  @PostMapping(value = "singup")
  public ResponseEntity<Account> postMethodName(@RequestBody SingupRequest entity) {
    return ResponseEntity.ok().body(accountService.singup(entity));
  }
}
