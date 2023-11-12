package datn.goodboy.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import datn.goodboy.model.request.ProductDetailRequest;
import datn.goodboy.model.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.RolesService;

@Controller
@RequestMapping("admin/employee")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolesService rolesService;
    private EmployeeResponse employeeResponse;
    public int rowcount = 10;
    public int[] pagenumbers;
    public String sortBy = "createdAt";
    public boolean sortDir = false;
    public int pageno = 0;
    public int totalpage = 0;

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



    @GetMapping("/getcountrow")
    public String getCountRow(Model model, @RequestParam("selectedValue") String selectedValue) {
        System.out.println(selectedValue);
        rowcount = Integer.parseInt(selectedValue);
        pagenumbers = employeeService.getPanigation(rowcount, pageno);
        this.pageno = 1;
        List<EmployeeResponse> list = employeeService.getPageNo(1, rowcount, sortBy, sortDir);
        totalpage = employeeService.getPageNumber(rowcount);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/employee/table-employee.html"; // Redirect back to the form page
    }

    @GetMapping("sort")
    public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
                              @RequestParam("sortDir") boolean sordir) {
        this.sortBy = sortby;
        this.sortDir = sordir;
        this.pageno = 1;
        List<EmployeeResponse> list = employeeService.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
        totalpage = employeeService.getPageNumber(rowcount);
        pagenumbers = employeeService.getPanigation(rowcount, pageno);
        model.addAttribute("list", list);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/employee/table-employee.html";
    }

    @GetMapping("/page")
    public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
        if (pageno <= 1) {
            this.pageno = 1;
            pageno = 1;
        }
        this.pageno = pageno;
        List<EmployeeResponse> list = employeeService.getPageNo(this.pageno, rowcount, sortBy, sortDir);
        totalpage = employeeService.getPageNumber(rowcount);
        model.addAttribute("totalpage", totalpage);
        pagenumbers = employeeService.getPanigation(rowcount, this.pageno);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", this.pageno);
        model.addAttribute("list", list);
        model.addAttribute("rowcount", rowcount);
        return "/admin/pages/employee/table-employee.html";
    }


}
