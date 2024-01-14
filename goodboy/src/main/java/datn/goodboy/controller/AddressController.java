package datn.goodboy.controller;

import datn.goodboy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/customer")
public class AddressController {
    @Autowired
    private AddressService addressService;



}
