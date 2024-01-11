package datn.goodboy.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.response.EmployeeResponse;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.RolesService;
import datn.goodboy.utils.convert.TrangThaiConvert;
import org.springframework.web.multipart.MultipartFile;

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
        return "admin/pages/employee/create-employee";
    }

    @ModelAttribute("employee")
    public Employee getEmployee() {
        return new Employee();
    }

    @PostMapping("/add")
    public String addEmployee(Model model, @Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult,
                              @RequestParam("imageFiles") List<MultipartFile> imageFiles) {


        if(bindingResult.hasErrors()){
            model.addAttribute("roles", rolesService.getAllRoles());
            return "admin/pages/employee/create-employee";
        }
        else {
            try {
                Employee savedEmployee = employeeService.saveEmployeeImage(employee, imageFiles);
                employee.setImage(savedEmployee.getImage());
                employeeService.saveEmployee(employee);
            } catch (IOException e) {
                e.printStackTrace();
                return "errorPage";
            }
        }

        return "redirect:/admin/employee/hien-thi";
    }


    @PostMapping("/update")
    public String update(Model model, @Valid @ModelAttribute("detail") Employee employee, BindingResult bindingResult,
                                @RequestParam("imageFiles") List<MultipartFile> imageFiles) {
        if(bindingResult.hasErrors()){
            model.addAttribute("roles", rolesService.getAllRoles());
            return "admin/pages/employee/detail-employee";
        }
        else {
            if (imageFiles != null && !imageFiles.get(0).isEmpty()) {
                try {
                    Employee savedEmployee = employeeService.saveEmployeeImage(employee, imageFiles);
                    employee.setImage(savedEmployee.getImage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return "errorPage";
                }
            } else {
                Optional<Employee> existingEmployee = employeeService.findByIdEmployee(employee.getId());
                employee.setImage(existingEmployee.get().getImage());
            }
        }


        employeeService.saveEmployee(employee);
        System.out.println(employee);
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

    @GetMapping("/detailAccount/{id}")
    public String detailAcount2(Model model, @PathVariable("id") UUID id){
        Optional<Employee> customer = employeeService.findByIdEmployee(id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
            model.addAttribute("roles", rolesService.getAllRoles());
        } else {
            model.addAttribute("detail", null);
        }
        return "admin/pages/employee/detail-account";
    }

    @GetMapping("/detail-account/{id}")
    public String detailAccount(Model model, @PathVariable("id") UUID id){
        Optional<Employee> customer = employeeService.findByIdEmployee(id);
        if (customer.isPresent()) {
            model.addAttribute("detail", customer.get());
            model.addAttribute("roles", rolesService.getAllRoles());
        } else {
            model.addAttribute("detail", null);
        }
        return "admin/pages/employee/update-account";
    }

    @PostMapping("/update-account")
    public String updateAccount(Model model, @Valid @ModelAttribute("detail") Employee employee, BindingResult bindingResult,
                         @RequestParam("imageFiles") List<MultipartFile> imageFiles) {

        if(bindingResult.hasErrors()){
            model.addAttribute("roles", rolesService.getAllRoles());
            return "admin/pages/employee/update-account";
        }
        else {
            if (imageFiles != null && !imageFiles.get(0).isEmpty()) {
                try {
                    Employee savedEmployee = employeeService.saveEmployeeImage(employee, imageFiles);
                    employee.setImage(savedEmployee.getImage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return "errorPage";
                }
            } else {
                Optional<Employee> existingEmployee = employeeService.findByIdEmployee(employee.getId());
                employee.setImage(existingEmployee.get().getImage());
            }
        }

        employeeService.saveEmployee(employee);
        return "redirect:/admin/pages/dashboard";
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
