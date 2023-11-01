package datn.goodboy.Controller.rest;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.Pay;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Admin {
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    PayService payService;

    @PostMapping("/add-customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        try{
            customerService.addCoustomer(customer);
            return ResponseEntity.ok("succ");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/add-empolyee")
    public ResponseEntity<?> addEmpolyee(@RequestBody Employee employee){
        try{
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok("succ");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/add-pay")
    public ResponseEntity<?> addPay(@RequestBody Pay pay){
        try{
            payService.savePay(pay);
            return ResponseEntity.ok("succ");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
