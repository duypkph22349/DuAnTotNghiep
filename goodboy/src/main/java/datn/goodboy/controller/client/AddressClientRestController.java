package datn.goodboy.controller.client;

import datn.goodboy.model.entity.Address;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.request.AddressRequest;
import datn.goodboy.service.AddressService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.client.AddressClientService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/client/address")
public class AddressClientRestController {

    @Autowired
    private AddressClientService addressClientService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TrangThaiConvert convert;

    @Autowired
    private AddressService addressService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllByIdCustomer(){
        try {
            return ResponseEntity.ok(addressClientService.findAllByIdCustomer());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewAddress(@RequestBody AddressRequest req){
        try {
            return ResponseEntity.ok(addressClientService.addNewAddress(req));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-customer")
    public ResponseEntity<?> addNewAddressCustomer(@RequestBody AddressRequest req,
                                                   @RequestParam("id_customer") String idCustomer){
        try {
            return ResponseEntity.ok(addressClientService.addNewAddressCustomer(req, idCustomer));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") UUID id){
        try{
            return ResponseEntity.ok(addressClientService.changeStatus(id));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") UUID id){
        try{
            return ResponseEntity.ok(addressClientService.deleteAddress(id));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/find-all-customer/{id}")
    public List<Address> detail(@PathVariable("id") UUID id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        List<Address> addresses = addressService.getAllAddressByIdCustomer(id);
        return addresses;
    }

}
