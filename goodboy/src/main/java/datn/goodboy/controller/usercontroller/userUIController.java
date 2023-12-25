package datn.goodboy.controller.usercontroller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.service.BillService;


@Controller
@RequestMapping("/shop/product/")
public class userUIController {



    @Autowired
    private BillService billService;

    @Autowired
    private BillRepository billRepository;

    @GetMapping({ "/don_hang", "" })
    public String viewOderStatus(Model model) {
        UUID customerId = billService.getCustomerId();
        int billCount = billService.getBillCountByStatus(3);
        if (customerId != null) {
            List<Bill> bills = billService.findBillsByCustomerId(customerId);
            model.addAttribute("bills", bills);
            model.addAttribute("billDetail", bills);
            model.addAttribute("billCount", billCount);
        }
        return "/user/order_status";
    }

    @PostMapping("/don_hang/{id}")
    @ResponseBody
    public boolean updateStatus(@PathVariable("id") Integer id) {
        try {
            Optional<Bill> optionalBill = billRepository.findById(id);
            if (optionalBill.isPresent()) {
                Bill bill = optionalBill.get();
                bill.setStatus(5);
                billRepository.save(bill);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
