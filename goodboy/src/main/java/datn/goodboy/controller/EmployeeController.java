package datn.goodboy.controller;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.service.CartService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/employee")
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolesService rolesService;

    private int currentProductCode = 1;
    @GetMapping("/show")
    public String show(){
        return "/admin/pages/employee/employee";
    }

    @GetMapping("/get-all")
    public List<Employee> getAll(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get-page")
    public ResponseEntity<Page<Employee>> getPage(@RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return ResponseEntity.ok().body(employeeService.getPage(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Employee b, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            return ResponseEntity.ok(objectErrorList);
        }
        String newCartCode = "BR" + String.format("%d", currentProductCode);
        b.setCode(newCartCode);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        b.setStatus(1);
        currentProductCode++;
        return ResponseEntity.ok(employeeService.saveEmployee(b));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update1(Model model, @PathVariable("id") UUID id, @RequestBody Employee employee) {
        Optional<Employee> employeeOptional = employeeService.findByIdEmployee(id);
        if (employeeOptional.isPresent()) {
            model.addAttribute("detail", employeeOptional.get());
            model.addAttribute("showCustomer", rolesService.getAllRoles());
        } else {
            model.addAttribute("detail", null);
        }
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update2(@RequestBody Employee employee, @PathVariable("id")UUID id){
        employee.setId(id);
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }
}
