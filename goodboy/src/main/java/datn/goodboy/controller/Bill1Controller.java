package datn.goodboy.controller;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.service.Bill1Service;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/bill")
public class Bill1Controller {
    @Autowired
    private Bill1Service bill1Service;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/dsBill")
    public String hienThi(Model model, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Bill> pageHD = bill1Service.findAllBill(pageable);
        model.addAttribute("totalPage", pageHD.getTotalPages());
        model.addAttribute("lstBill", pageHD.getContent());
        return "admin/pages/bill1/hien-thi";
    }

    @GetMapping("/tim-HD-theo-ma")
    public String seachHD(Model model, @RequestParam(defaultValue = "1") int page,@RequestParam(required = false) String ma) {
        Page<Bill> pageHD;
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
        if (ma == null || ma.isBlank()) {
            pageHD = bill1Service.findAllBill(pageable);
        } else {
            pageHD = bill1Service.searchBillByCode(ma, pageable);
        }
        model.addAttribute("lstBill", pageHD);
        model.addAttribute("ma", ma);
        return "admin/pages/bill1/hien-thi";
    }
}