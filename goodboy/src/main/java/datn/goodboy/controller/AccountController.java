package datn.goodboy.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.request.AccountRequest;
import datn.goodboy.model.response.AccountResponse;
import datn.goodboy.service.AccountService;
import datn.goodboy.service.CustomerService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/pages/account")
public class AccountController {

  @Autowired
  private AccountService service;

  @Autowired
  private CustomerService customerService;
  @Autowired
  private AccountRequest accountRequest;
  private AccountResponse accountResponse;
  public int rowcount = 10;
  public int[] pagenumbers;
  public String sortBy = "email";
  public boolean sortDir = true;
  public int pageno = 0;
  public int totalpage = 0;

  // panigation and sort
  @GetMapping("/getcountrow")
  public String handleSubmit(Model model, @RequestParam("selectedValue") String selectedValue) {
    System.out.println(selectedValue);
    rowcount = Integer.parseInt(selectedValue);
    pagenumbers = service.getPanigation(rowcount, pageno);
    List<AccountResponse> list = service.getFirstPage(rowcount, sortBy, sortDir);
    pageno = 1;
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/account/table-account.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    List<AccountResponse> list = service.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
    totalpage = service.getPageNumber(rowcount);
    pagenumbers = service.getPanigation(rowcount, pageno);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/account/table-account.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    List<AccountResponse> list = service.getPageNo(this.pageno - 1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    pagenumbers = service.getPanigation(rowcount, this.pageno);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    return "/admin/pages/account/table-account.html";
  }

  // end
  @GetMapping("index")
  public String getAccountIndexpages(Model model) {
    List<AccountResponse> list = service.getFirstPage(rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/account/table-account.html";
  }

  @ModelAttribute("accountRequest")
  public AccountRequest setSignUpForm() {
    return accountRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    accountRequest = new AccountRequest();
    model.addAttribute("listCustomer", customerService.getComboBox());
    model.addAttribute("accountRequest", accountRequest);
    return "/admin/pages/account/form-account.html";
  }

  @GetMapping("delete")
  public String deleteAccount(Model model, @RequestParam("id") String id) {
    service.deleteAccount(UUID.fromString(id));
    return "redirect:table-account";
  }

  @GetMapping("edit")
  public String editAccount(Model model, @RequestParam("id") String id) {
    model.addAttribute("accountRequest", service.getAccountRequetById(UUID.fromString(id)));
    model.addAttribute("listCustomer", customerService.getAllCustomers());
    return "/admin/pages/account/form-account.html";
  }

  @PostMapping("store")
  public String storeAccount(Model model, @Valid @ModelAttribute("accountRequest") AccountRequest accountRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      model.addAttribute("listCustomer", customerService.getComboBox());
      return "/admin/pages/account/form-account.html";
    } else {
      service.saveAccount(accountRequest);
      return "redirect:table-account";
    }
  }

  @PostMapping("update")
  public String update(@Valid @ModelAttribute("accountRequest") AccountRequest accountRequest,
      BindingResult theBindingResult, Model model) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/account/form-account.html";
    }
    service.updateAccount(accountRequest);
    return "redirect:table-account";
  }
}
