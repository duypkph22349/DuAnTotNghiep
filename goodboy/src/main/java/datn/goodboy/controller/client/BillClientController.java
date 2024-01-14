package datn.goodboy.controller.client;

import datn.goodboy.service.client.BillClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/bill")
public class BillClientController {

    @Autowired
    private BillClientService billClientService;

    @GetMapping("/detail/{id}")
    public String getBillDetail(Model model, @PathVariable("id")String id) {
        model.addAttribute("bill", billClientService.getBillById(id));
        return "user2/bill_detail";
    }

    @GetMapping("/find-bill")
    public String findBill(Model model) {
        return "user2/find_bill";
    }
}
