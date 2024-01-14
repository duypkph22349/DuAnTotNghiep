package datn.goodboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.service.BillService;

@Controller
@RequestMapping("/admin/bill")
public class BillController {

    @Autowired
    private BillService billService;

    // @Autowired
    // private BillDetailService billDetailService;

    @GetMapping({ "/dsBill", "" })
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Bill> bill = billService.getPage(pageable);
        model.addAttribute("totalPage", bill.getTotalPages());
        model.addAttribute("billPage", bill.getContent());
        // model.addAttribute("customer", customerService.getAllCustomers());
        // model.addAttribute("employee", employeeService.getAllEmployee());
        // model.addAttribute("pay", payService.getAllPay());

        return "/admin/pages/bill/bill";
    }

    @GetMapping("/bill-detail")
    public String getBillDetail(@RequestParam("id") Integer id, Model model) {
        try {
            model.addAttribute("bill", billService.getBillById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/pages/bill/bill2";
    }

    @GetMapping("/bill-detail-update-status")
    public String updateStatusBillDetail(@RequestParam("id") Integer id,
                                         @RequestParam("status") Integer status) {
        try {
            if (status == 5) {
                billService.updateStatusAndPayStatus(id, status);
            } else {
                billService.updateStatus(id, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/bill/bill-detail?id=" + id;
    }

    @GetMapping("/bill-detail-cancel")
    public String cancelStatusBillDetail(@RequestParam("id") Integer id,
                                         @RequestParam("status") Integer status, @RequestParam("idBill") Integer ibBill,
                                         @RequestParam("note") String note) {
        try {
            billService.updateStatusAndNote(id, status, note);

            //
            billService.cancelBill(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/bill/bill-detail?id=" + id;
    }

    @GetMapping("/bill-detail-deposit")
    public String updatedeposit(@RequestParam("id") Integer id,
                                @RequestParam("deposit") float deposit, @RequestParam("idBill") Integer ibBill){
        try {
            billService.updatedeposit(id, deposit);

            //
            billService.cancelBill(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/bill/bill-detail?id=" + id;
    }
}
