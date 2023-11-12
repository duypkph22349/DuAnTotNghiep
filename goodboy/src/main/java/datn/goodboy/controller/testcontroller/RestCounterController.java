package datn.goodboy.controller.testcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.service.CartDetailService;
import datn.goodboy.service.CartService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.ProductDetailService;

@RestController("countercartresttest")
@RequestMapping("rest/data/counter")
public class RestCounterController {

  @Autowired
  private CartService cartService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private ProductDetailService productDetailService;

  @Autowired
  private CartDetailService icartService;

  @GetMapping("employees")
  public ResponseEntity<List<Employee>> getAllEmp() {
    return ResponseEntity.ok().body(employeeService.getAllEmployee());
  }

  @GetMapping("customers")
  public ResponseEntity<List<Customer>> getAllCustomer() {
    return ResponseEntity.ok().body(customerService.getAllCustomers());
  }

  @GetMapping("productDetails")
  public ResponseEntity<List<ProductDetail>> getAllProductDetails() {
    return ResponseEntity.ok().body(productDetailService.getPageNo(1, 20, "createdAt", true));
  }

  @GetMapping("productDetails/{id}")
  public ResponseEntity<ProductDetail> getProduct(@PathVariable("id") int id) {
    System.out.println(id);
    return ResponseEntity.ok().body(productDetailService.getPageNo(1, 20, "createdAt", true).get(0));
  }
}
