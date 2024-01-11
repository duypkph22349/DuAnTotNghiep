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

import datn.goodboy.model.entity.Promotion;
import datn.goodboy.service.PromotionRequest;
import datn.goodboy.service.PromotionService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/promotion")
public class PromotionController {

  @Autowired
  private PromotionService service;
  @Autowired
  TrangThaiConvert convert;

  @ModelAttribute("convert")
  public TrangThaiConvert convert() {
    return convert;
  }

  @Autowired
  private PromotionRequest promotionRequest;
  public int rowcount = 10;
  public int statusfilter = 0;
  public String textSearch = "";

  public int[] pagenumbers;
  public String sortBy = "updatedAt";
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

  // panigation and sort
  @GetMapping("/getcountrow")
  public String getCountRow(Model model, @RequestParam("selectedValue") String selectedValue) {
    rowcount = Integer.parseInt(selectedValue);
    this.pageno = 1;
    List<Promotion> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/promotion/table-promotion.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    List<Promotion> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/promotion/table-promotion.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    List<Promotion> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/promotion/table-promotion.html";
  }

  // end
  @GetMapping({ "index", "" })
  public String getPromotionIndexpages(Model model) {
    this.pageno = 1;
    List<Promotion> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("statusfilter", this.statusfilter);
    model.addAttribute("textSearch", this.textSearch);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    model.addAttribute("rowcount", rowcount);
    return "/admin/pages/promotion/table-promotion.html";
  }

  @ModelAttribute("promotionRequest")
  public PromotionRequest setSignUpForm() {
    return promotionRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    promotionRequest = new PromotionRequest();
    model.addAttribute("promotionRequest", promotionRequest);
    return "/admin/pages/promotion/form-promotion.html";
  }

  @GetMapping("delete")
  public String deletePromotion(Model model, @RequestParam("id") UUID id) {
    service.deletePromotion(id);
    return "redirect:index";
  }

  @GetMapping("edit")
  public String editPromotion(Model model, @RequestParam("id") UUID id) {
    model.addAttribute("promotionRequest",
        service.getPromotionRequetById(id));
    return "/admin/pages/promotion/update-promotion.html";
  }

  @PostMapping("store")
  public String storePromotion(Model model,
      @Valid @ModelAttribute("promotionRequest") PromotionRequest promotionRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/promotion/form-promotion.html";
    } else {
      if (promotionRequest.validateHasError()) {
        model.addAttribute("validateerrors", promotionRequest.ValidateError());
        return "/admin/pages/promotion/form-promotion.html";
      }
      service.savePromotion(promotionRequest);
      return "redirect:index";
    }
  }

  @PostMapping("update")
  public String update(@Valid @ModelAttribute("promotionRequest") PromotionRequest promotionRequest,
      BindingResult theBindingResult, Model model) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/promotion/update-promotion.html";
    }
    service.updatePromotion(promotionRequest);
    return "redirect:index";
  }
}
