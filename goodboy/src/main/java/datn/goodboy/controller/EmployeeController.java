package datn.goodboy.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.ProductDetailRequest;
import datn.goodboy.model.response.EmployeeResponse;
import datn.goodboy.utils.convert.TrangThaiConvert;
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

    @Autowired
    TrangThaiConvert convert;

    @ModelAttribute("convert")
    public TrangThaiConvert convert() {
        return convert;
    }

    private EmployeeResponse employeeResponse;
    public int rowcount = 10;
    public int[] pagenumbers;
    public String sortBy = "name";
    public boolean sortDir = true;
    public int pageno = 0;
    public int totalpage = 0;

    @GetMapping("/getcountrow")
    public String getCountRow(Model model, @RequestParam("selectedValue") String selectedValue) {
        rowcount = Integer.parseInt(selectedValue);
        pagenumbers = employeeService.getPanigation(rowcount, pageno);
        this.pageno = 1;
        List<Employee> list = employeeService.getPageNo(1, rowcount, sortBy, sortDir);
        totalpage = employeeService.getPageNumber(rowcount);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        return "admin/pages/employee/table-employee";
    }


    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        this.pageno = 1;
        List<Employee> list = employeeService.getPageNo(this.pageno, rowcount, sortBy, sortDir);
        pagenumbers = employeeService.getPanigation(rowcount, pageno);
        totalpage = employeeService.getPageNumber(rowcount);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("list", list);
        model.addAttribute("pagenumber", pagenumbers);
        model.addAttribute("crpage", pageno);
        model.addAttribute("rowcount", rowcount);
        model.addAttribute("list", employeeService.getAllEmployee());
        model.addAttribute("roles", rolesService.getAllRoles());
        return "admin/pages/employee/table-employee";

    }


    @GetMapping("/form-add")
    public String add(Model model) {
        model.addAttribute("roles", rolesService.getAllRoles());
        return "admin/pages/employee/table-employee";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute Employee employee) {
        model.addAttribute("roles", rolesService.getAllRoles());
        employeeService.saveEmployee(employee);
        return "redirect:/admin/employee/hien-thi";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam("id") UUID id) {
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




    @GetMapping("sort")
    public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
                              @RequestParam("sortDir") boolean sordir) {
        this.sortBy = sortby;
        this.sortDir = sordir;
        this.pageno = 1;
        List<Employee> list = employeeService.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
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
        List<Employee> list = employeeService.getPageNo(this.pageno, rowcount, sortBy, sortDir);
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
