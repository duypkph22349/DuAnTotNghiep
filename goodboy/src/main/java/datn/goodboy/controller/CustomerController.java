package datn.goodboy.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import datn.goodboy.model.entity.Address;
import datn.goodboy.model.request.CustomerRequest;
import datn.goodboy.service.AddressService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.service.CustomerService;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    TrangThaiConvert convert;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerRequest customerRequest;

    @ModelAttribute("convert")
    public TrangThaiConvert convert() {
        return convert;
    }

    @GetMapping({"/get-all", ""})
    public String hienThi(Model model,
                          @RequestParam(name = "pageSize", defaultValue = "6") Integer pageSize,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "selectedProvinceId", required = false) String selectedProvinceId,
                          @RequestParam(name = "selectedDistrictId", required = false) String selectedDistrictId) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Customer> page = customerService.getPage(pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("list", page.getContent());
        return "/admin/pages/customer/form-customer";
    }

    // /admin/customer/add
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        customerRequest = new CustomerRequest();
        model.addAttribute("customerRequest", customerRequest); // Add an empty customer object to the model
        return "/admin/pages/customer/customer_modal";

    }

    @ModelAttribute("customer")
    public Customer getCustomer() {
        return new Customer();
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("customerRequest") CustomerRequest customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//           model.addAttribute("customer", customer); // Đưa đối tượng customer vào model để giữ giá trị đã nhập
            System.out.println(bindingResult.hasErrors());
            return "/admin/pages/customer/customer_modal";
        } else {
            customer.setStatus(1);
            customer.setCreatedAt(LocalDateTime.now());
            customerService.updateCustomer(customer);
        }
        return "redirect:/admin/customer/get-all";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("detail") CustomerRequest customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/admin/pages/customer/customer-detail";
        } else {

            customer.setUpdatedAt(LocalDateTime.now());
            customerService.updateCustomer(customer);
        }

        return "redirect:/admin/customer/get-all";
    }

    @GetMapping("delete")
    public String deleteVoucher(Model model, @RequestParam("id") UUID id) {
        customerService.deleteVoucher(id);
        return "redirect:/admin/customer/get-all";
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        List<Address> addresses = addressService.getAllAddressByIdCustomer(id);
        model.addAttribute("id_customer", id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
            model.addAttribute("addresss", addresses);
        } else {
            model.addAttribute("detail", null);
        }
        System.out.println(addressService.getAllAddressByIdCustomer(id));
        return "/admin/pages/customer/customer-detail";
    }


    @PostMapping("detail/{id}/save_address")
    public String saveAddress(@ModelAttribute("addresss") Address addresss) {
        System.out.println(addresss.getId_customer().getId());
        addressService.getSave(addresss);
        return "redirect:/admin/customer/detail/" + addresss.getId_customer().getId();
    }


    @GetMapping("detail/{id}/edit_address/{id_address}")
    public String editAddress( @PathVariable("id_address") UUID id_address, Model model){
        model.addAttribute("detail_address", addressService.findById(id_address));
        return "/admin/pages/customer/customer-detail";
    }




    @GetMapping("detail/{id}/delete_address/{id_address}")
    public String deleteAddress(@PathVariable("id") UUID id, @PathVariable("id_address") UUID id_address){
        addressService.deleteById(id_address);
        return "redirect:/admin/customer/detail/" + id;
    }
}
