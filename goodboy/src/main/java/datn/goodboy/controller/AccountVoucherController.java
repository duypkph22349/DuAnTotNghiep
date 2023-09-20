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

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.request.AccountRequest;
import datn.goodboy.model.response.AccountResponse;
import datn.goodboy.service.AccountService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/pages")
public class AccountVoucherController {
  @GetMapping("account-voucher")
  public String mmAccountVoucher() {
    return "/admin/pages/account/account-voucher.html";
  }

  // @Autowired
  // private AccountService service;
  // @Autowired
  // private Account accountRequest;
  // public int rowcount = 10;
  // public int[] pagenumbers;
  // public String sortBy = "email";
  // public boolean sortDir = true;

  // // panigation and sort
  // @GetMapping("/getcountrow")
  // public String handleSubmit(Model model, @RequestParam("selectedValue") String selectedValue) {
  //   System.out.println(selectedValue);
  //   if (selectedValue.equals("ALL")) {
  //     rowcount = Integer.MAX_VALUE;
  //   } else {
  //     rowcount = Integer.parseInt(selectedValue);
  //   }
  //   pagenumbers = service.getPageNumber(rowcount);
  //   List<AccountResponse> list = service.getFirstPage(rowcount, sortBy, sortDir);
  //   model.addAttribute("list", list);
  //   model.addAttribute("pagenumber", pagenumbers);
  //   return "Redirect:account-voucher"; // Redirect back to the form page
  // }

  // @GetMapping("last")
  // public String getLastPage(Model model) {
  //   List<AccountResponse> list = service.getLastPage(rowcount, sortBy, sortDir);
  //   model.addAttribute("list", list);
  //   return "/admin/pages/account/account-voucher.html";
  // }

  // @GetMapping("sort")
  // public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
  //     @RequestParam("sortDir") boolean sordir) {
  //   sortBy = sortby;
  //   sortDir = sordir;
  //   List<AccountResponse> list = service.getFirstPage(rowcount, sortBy, sortDir);
  //   model.addAttribute("list", list);
  //   model.addAttribute("pagenumber", pagenumbers);
  //   return "/admin/pages/account/account-voucher.html";
  // }

  // @GetMapping("first")
  // public String getFirstPages(Model model) {
  //   List<AccountResponse> list = service.getFirstPage(rowcount, sortBy, sortDir);
  //   pagenumbers = service.getPageNumber(rowcount);
  //   model.addAttribute("pagenumber", pagenumbers);
  //   model.addAttribute("list", list);
  //   return "/admin/pages/account/account-voucher.html";
  // }

  // @GetMapping("/page")
  // public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
  //   List<AccountResponse> list = service.getPageNo(pageno - 1, rowcount, sortBy, sortDir);
  //   model.addAttribute("pagenumber", pagenumbers);
  //   model.addAttribute("list", list);
  //   return "/admin/pages/account/account-voucher.html";
  // }

  // @GetMapping("index")
  // public String getAccountIndexpages(Model model) {
  //   List<AccountResponse> list = service.getFirstPage(rowcount, sortBy, sortDir);
  //   model.addAttribute("list", list);
  //   return "/admin/pages/account/account-voucher.html";
  // }

  // @ModelAttribute("accountRequest")
  // public Account setSignUpForm() {
  //   return accountRequest;
  // }

  // @GetMapping("create")
  // public String goToCreateForm() {
  //   accountRequest = new Account();
  //   return "/admin/pages/account/account-voucher.html/form.html";
  // }

  // @GetMapping("delete")
  // public String deleteAccount(Model model, @RequestParam("id") String id) {
  //   service.deleteAccount(UUID.fromString(id));
  //   return "redirect:account-voucher";
  // }

  // @GetMapping("edit")
  // public String editAccount(Model model, @RequestParam("id") String id) {
  //   model.addAttribute("accountRequest", service.getAccountById(UUID.fromString(id)));
  //   return "/admin/pages/account/account-voucher.html";
  // }

  // @PostMapping("store")
  // public String storeAccount(Model model, @Valid @ModelAttribute("accountRequest") Account accountRequest,
  //     BindingResult theBindingResult) {
  //   System.out.println(accountRequest);
  //   if (theBindingResult.hasErrors()) {
  //     return "/admin/pages/account/account-voucher.html/form.html";
  //   } else {
  //     service.saveAccount(accountRequest);
  //     model.addAttribute("list", service.getFirstPage(rowcount, sortBy, sortDir));
  //     return "redirect:account-voucher";
  //   }
  // }

  // @PostMapping("update")
  // public String update(@Valid @ModelAttribute("accountRequest") AccountRequest accountRequest,
  //     BindingResult theBindingResult, Model model) {
  //   if (theBindingResult.hasErrors()) {
  //     return "/admin/pages/account/account-voucher.html";
  //   }
  //   service.updateAccount(accountRequest);
  //   model.addAttribute("list", service.getFirstPage(rowcount, sortBy, sortDir));
  //   return "/admin/pages/account/account-voucher.html";
  // }
}
