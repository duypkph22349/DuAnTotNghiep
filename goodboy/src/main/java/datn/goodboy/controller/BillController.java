package datn.goodboy.Controller;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "/admin/pages/bill/bill";
    }
    @GetMapping("/bill-detail")
    public String getBillDetail(@RequestParam("id") Integer id,Model model){
        try{
            model.addAttribute("bill",billService.getBillById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/pages/bill/bill2";
    }

    @GetMapping("/bill-detail-update-status")
    public String updateStatusBillDetail(@RequestParam("id") Integer id,
                                         @RequestParam("status") Integer status){
        try{
            billService.updateStatus(id,status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/bill/bill-detail?id=" + id;
    }






}
