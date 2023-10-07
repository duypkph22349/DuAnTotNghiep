package datn.goodboy.controller;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.DiaChi.District;
import datn.goodboy.model.entity.DiaChi.Province;
import datn.goodboy.model.entity.DiaChi.Ward;
import datn.goodboy.model.entity.Location;
import datn.goodboy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "selectedProvinceId", required = false) String selectedProvinceId,
                          @RequestParam(name = "selectedDistrictId", required = false) String selectedDistrictId) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Customer> page = customerService.getPage(pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("list", page.getContent());

//        List<Province> provinces = customerService.getAllProvinces();
//        List<District> districts = customerService.getAllDistricts();
//        List<Ward> wards = customerService.getAllWards();
//        model.addAttribute("provinces", provinces);
//        model.addAttribute("districts", districts);
//        model.addAttribute("wards", wards);
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
    public String detail(Model model, @PathVariable("id") UUID id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
        } else {
            model.addAttribute("detail", null);
        }
        return "/admin/pages/customer/customer-detail";
    }


}
