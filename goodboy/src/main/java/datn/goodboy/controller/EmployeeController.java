package datn.goodboy.controller;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.response.AccountResponse;
import datn.goodboy.model.response.EmployeeResponse;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("admin/employee")
@Controller
public class EmployeeController {
    public int rowcount = 10;
    public int[] pagenumbers;
    public String sortBy = "email";
    public boolean sortDir = true;
    public int pageno = 0;
    public int totalpage = 0;

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

    @GetMapping("/employees")
    public String getEmployeeList(@RequestParam(value = "sort", defaultValue = "asc") String sort, Model model) {
        List<Employee> employees;

        if (sort.equals("asc")) {
            employees = employeeService.getEmployeesSortedByCodeAsc();
        } else {
            employees = employeeService.getEmployeesSortedByCodeDesc();
        }

        model.addAttribute("employees", employees);
        return "admin/pages/employee/table-employee";
    }



}
