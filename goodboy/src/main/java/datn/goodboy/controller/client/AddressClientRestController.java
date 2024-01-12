package datn.goodboy.controller.client;

import datn.goodboy.model.request.AddressRequest;
import datn.goodboy.service.client.AddressClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client/address")
public class AddressClientRestController {

    @Autowired
    private AddressClientService addressClientService;

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

    @PutMapping("/change-status")
    public ResponseEntity<?> changeStatus(@RequestParam("id") UUID id){
        try{
            return ResponseEntity.ok(addressClientService.changeStatus(id));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
