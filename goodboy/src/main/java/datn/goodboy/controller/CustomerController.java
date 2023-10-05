package datn.goodboy.controller;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/get-all")
    public String hienThi(Model model,
                          @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Customer> page = customerService.getPage(pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("list", page.getContent());
        return "/admin/pages/customer/form-customer";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/admin/customer/get-all";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/customer/get-all";
    }
    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id){
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
        } else {
            model.addAttribute("detail", null);
        }
        return "/admin/pages/customer/customer-detail";
    }
}
