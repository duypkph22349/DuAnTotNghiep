package datn.goodboy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.VoucherRequest;
import datn.goodboy.service.VoucherService;
import datn.goodboy.utils.convert.TrangThaiConvert;
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
  private VoucherRequest voucherRequest;
  public int rowcount = 10;
  public int statusfilter = 0;
  public String textSearch = "";

  public int[] pagenumbers;
  public String sortBy = "updated_at";
  public boolean sortDir = false;
  public int pageno = 0;
  public int totalpage = 0;

  @ModelAttribute("rowcount")
  public int rowcount() {
    return rowcount;
  }

  @ModelAttribute("statusfilter")
  public int statusfille() {
    return statusfilter;
  }

  @ModelAttribute("textSearch")
  public String textSearch() {
    return textSearch;
  }

  // panigation and sort
  @GetMapping("/getcountrow")
  public String getCountRow(Model model, @RequestParam("selectedValue") String selectedValue) {
    rowcount = Integer.parseInt(selectedValue);
    this.pageno = 1;
    List<Voucher> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, this.textSearch, this.statusfilter);
    pagenumbers = service.getPanigation(rowcount, pageno, this.textSearch, this.statusfilter);
    totalpage = service.getPageNumber(rowcount, this.textSearch, this.statusfilter);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/voucher/table-voucher.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    List<Voucher> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, this.textSearch, this.statusfilter);
    pagenumbers = service.getPanigation(rowcount, pageno, this.textSearch, this.statusfilter);
    totalpage = service.getPageNumber(rowcount, this.textSearch, this.statusfilter);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/voucher/table-voucher.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    List<Voucher> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, this.textSearch, this.statusfilter);
    pagenumbers = service.getPanigation(rowcount, pageno, this.textSearch, this.statusfilter);
    totalpage = service.getPageNumber(rowcount, this.textSearch, this.statusfilter);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/voucher/table-voucher.html";
  }

  // end
  @GetMapping({ "index", "" })
  public String getVoucherIndexpages(Model model) {
    this.pageno = 1;
    List<Voucher> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, this.textSearch, this.statusfilter);
    pagenumbers = service.getPanigation(rowcount, pageno, this.textSearch, this.statusfilter);
    totalpage = service.getPageNumber(rowcount, this.textSearch, this.statusfilter);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/voucher/table-voucher.html";
  }

  @ModelAttribute("voucherRequest")
  public VoucherRequest setSignUpForm() {
    return voucherRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    voucherRequest = new VoucherRequest();
    model.addAttribute("voucherRequest", voucherRequest);
    return "/admin/pages/voucher/form-voucher.html";
  }

  @GetMapping("delete")
  public String deleteVoucher(Model model, @RequestParam("id") int id) {
    service.deleteVoucher(id);
    return "redirect:index";
  }

  @GetMapping("edit")
  public String editVoucher(Model model, @RequestParam("id") int id) {
    model.addAttribute("voucherRequest",
        service.getVoucherRequetById(id));
    return "/admin/pages/voucher/update-voucher.html";
  }

  @PostMapping("store")
  public String storeVoucher(Model model, @Valid @ModelAttribute("voucherRequest") VoucherRequest voucherRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/voucher/form-voucher.html";
    } else {
      if (voucherRequest.validateHasError()) {
        model.addAttribute("validateerrors", voucherRequest.ValidateError());
        return "/admin/pages/voucher/form-voucher.html";
      }
      service.saveVoucher(voucherRequest);
      return "redirect:index";
    }
  }

  @PostMapping("update")
  public String update(@Valid @ModelAttribute("voucherRequest") VoucherRequest voucherRequest,
      BindingResult theBindingResult, Model model) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/voucher/update-voucher.html";
    }
    service.updateVoucher(voucherRequest);
    return "redirect:index";
  }

  @GetMapping("/search")
  public String search(Model model,
      @RequestParam(name = "txtSearch", required = false) String search,
      @RequestParam(name = "status", defaultValue = "0") int status) {
    if (search != null) {
      this.textSearch = search;
    } else {
      this.textSearch = "";
    }
    this.statusfilter = status;
    this.pageno = 1;
    List<Voucher> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir, this.textSearch, this.statusfilter);
    pagenumbers = service.getPanigation(rowcount, pageno, this.textSearch, this.statusfilter);
    totalpage = service.getPageNumber(rowcount, this.textSearch, this.statusfilter);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/voucher/table-voucher.html";
  }
}
