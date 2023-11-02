package datn.goodboy.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.RolesService;

@RequestMapping("admin/employee")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolesService rolesService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("list", employeeService.getAllEmployee());
        model.addAttribute("roles", rolesService.getAllRoles());
        return "admin/pages/employee/table-employee";
    }


    @GetMapping("/form-add")
    public String add(Model model) {
        model.addAttribute("roles", rolesService.getAllRoles());
        return "admin/pages/employee/create-employee";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/admin/employee/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        employeeService.deleteEmployee(id);
        return "redirect:/admin/employee/hien-thi";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id){
        Optional<Employee> customer = employeeService.findByIdEmployee(id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
            model.addAttribute("roles", rolesService.getAllRoles());
        } else {
            model.addAttribute("detail", null);
        }
        return "admin/pages/employee/detail-employee";
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update2(@RequestBody Employee employee, @PathVariable("id")UUID id){
//        employee.setId(id);
//        employeeService.saveEmployee(employee);
//        return ResponseEntity.ok(employee);
//    }


}
