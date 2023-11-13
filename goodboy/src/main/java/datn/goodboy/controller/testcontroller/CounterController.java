package datn.goodboy.controller.testcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.response.CustomerResponse;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.ProductDetailService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("countercarttest")
@RequestMapping("test/counter")
public class CounterController {
  // @Autowired
  // private CartService cartService;

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
    return "admin/pages/cartcounter/thatcc.html";
  }

  @PostMapping("checkout")
  public String checkOutOrder(Model model) {
    return "admin/pages/cartcounter/thatcc.html";
  }
}
