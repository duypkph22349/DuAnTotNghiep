package datn.goodboy.controller.rest;

import datn.goodboy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin/customer")
public class CustomerAdminController {
    @Autowired
    private AddressService addressService;

    @GetMapping("details/{id_address}")
    public Object editAddresss(@PathVariable("id_address") UUID id_address) {
        return addressService.findById(id_address);
    }

}
