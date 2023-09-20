package datn.goodboy.controller;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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

    @PostMapping("/add")
    public String add(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/admin/employee/hien-thi";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        employeeService.deleteEmployee(id);
        return "redirect:/admin/employee/hien-thi";
    }
    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id){
        Optional<Employee> customer = employeeService.findByIdEmployee(id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
        } else {
            model.addAttribute("detail", null);
        }
        return "";
    }

}
