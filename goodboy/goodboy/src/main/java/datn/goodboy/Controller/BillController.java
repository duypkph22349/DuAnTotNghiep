package datn.goodboy.Controller;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.service.BillService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;



    @GetMapping("/hien-thi")
    public String hienThi(Model model,@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Bill> bill = billService.getPage(pageable);
        model.addAttribute("totalPage", bill.getTotalPages());
        model.addAttribute("billPage", bill.getContent());
//        model.addAttribute("customer", customerService.getAllCustomers());
//        model.addAttribute("employee", employeeService.getAllEmployee());
//        model.addAttribute("pay", payService.getAllPay());

        return "admin/pages/bill/bill-detail";

    }






}
