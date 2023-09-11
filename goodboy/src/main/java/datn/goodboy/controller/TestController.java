package datn.goodboy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.response.CustomerResponse;
import datn.goodboy.service.AccountService;
import datn.goodboy.service.CustomerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
