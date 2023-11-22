package datn.goodboy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.ProductDetailService;

@Controller("countercarttest")
@RequestMapping("admin/counter")
public class CounterController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private ProductDetailService productDetailService;

  @ModelAttribute("employees")
  public List<Employee> getAllEmp() {
    return employeeService.getAllEmployee();
  }

  @ModelAttribute("customers")
  public List<Customer> getAllCustomer() {
    return customerService.getAllCustomers();
  }

  @ModelAttribute("productDetails")
  public List<ProductDetail> getAllProductDetails() {
    return productDetailService.getPageNo(1, 20, "createdAt", true);
  }

  @GetMapping("")
  public String getOrderPage(Model model) {
    return "admin/pages/cartcounter/counter.html";
  }

  @PostMapping("viewordetail/{id}")
  public String checkOutOrder(@PathVariable("id") int id, Model model) {

    return "admin/pages/cartcounter/order-detail.html";
  }
}
