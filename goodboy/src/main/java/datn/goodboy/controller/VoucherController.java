package datn.goodboy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.request.VoucherRequest;
import datn.goodboy.model.response.VoucherResponse;
import datn.goodboy.service.VoucherService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import datn.goodboy.service.CustomerService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/voucher")
public class VoucherController {

  @Autowired
  private VoucherService service;
  @Autowired
  TrangThaiConvert convert;

  @ModelAttribute("convert")
  public TrangThaiConvert convert() {
    return convert;
  }

  @Autowired
  private CustomerService customerService;
  @Autowired
  private VoucherRequest voucherRequest;
  private VoucherResponse voucherResponse;
  public int rowcount = 10;
  public int[] pagenumbers;
  public String sortBy = "name";
  public boolean sortDir = true;
  public int pageno = 0;
  public int totalpage = 0;

  // panigation and sort
  @GetMapping("/getcountrow")
  public String handleSubmit(Model model, @RequestParam("selectedValue") String selectedValue) {
    System.out.println(selectedValue);
    rowcount = Integer.parseInt(selectedValue);
    pagenumbers = service.getPanigation(rowcount, pageno);
    this.pageno = 1;
    List<VoucherResponse> list = service.getPageNo(1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/voucher/table-voucher.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    List<VoucherResponse> list = service.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
    totalpage = service.getPageNumber(rowcount);
    pagenumbers = service.getPanigation(rowcount, pageno);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/voucher/table-voucher.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    List<VoucherResponse> list = service.getPageNo(this.pageno - 1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    pagenumbers = service.getPanigation(rowcount, this.pageno);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    return "/admin/pages/voucher/table-voucher.html";
  }

  // end
  @GetMapping("index")
  public String getVoucherIndexpages(Model model) {
    this.pageno = 1;
    List<VoucherResponse> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/voucher/table-voucher.html";
  }

  @ModelAttribute("voucherRequest")
  public VoucherRequest setSignUpForm() {
    return voucherRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    voucherRequest = new VoucherRequest();
    voucherRequest.setStatus(1);
    model.addAttribute("listCustomer", customerService.getComboBox());
    model.addAttribute("voucherRequest", voucherRequest);
    return "/admin/pages/voucher/form-voucher.html";
  }

  @GetMapping("delete")
  public String deleteVoucher(Model model, @RequestParam("id") String id) {
    // service.deleteVoucher(UUID.fromString(id));
    return "redirect:table-voucher";
  }

  @GetMapping("edit")
  public String editVoucher(Model model, @RequestParam("id") String id) {
    // model.addAttribute("voucherRequest",
    // service.getVoucherRequetById(UUID.fromString(id)));
    model.addAttribute("listCustomer", customerService.getAllCustomers());
    return "/admin/pages/voucher/form-voucher.html";
  }

  @PostMapping("store")
  public String storeVoucher(Model model, @Valid @ModelAttribute("voucherRequest") VoucherRequest voucherRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      model.addAttribute("listCustomer", customerService.getComboBox());
      return "/admin/pages/voucher/form-voucher.html";
    } else {
      // service.saveVoucher(voucherRequest);
      return "redirect:table-voucher";
    }
  }

  @PostMapping("update")
  public String update(@Valid @ModelAttribute("voucherRequest") VoucherRequest voucherRequest,
      BindingResult theBindingResult, Model model) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/voucher/form-voucher.html";
    }
    // service.updateVoucher(voucherRequest);
    return "redirect:table-voucher";
  }
}
