package datn.goodboy.controller.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.OrderCounterRequest;
import datn.goodboy.service.CounterService;
import datn.goodboy.service.CustomerService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.ProductDetailService;
import datn.goodboy.service.VoucherService;

@RestController("counterController")
@RequestMapping("/admin/counter")
public class CounterController {

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProductDetailService productDetailService;

  @Autowired
  private VoucherService voucherService;
  @Autowired
  private CounterService countService;

  @GetMapping("voucherAble")
  public ResponseEntity<List<Voucher>> getAbleVoucher() {
    return ResponseEntity.ok().body(voucherService.getAllVoucherAble());
  }

  @GetMapping("voucher/{id}")
  public ResponseEntity<Voucher> getVoucher(@PathVariable("id") int id) {
    return ResponseEntity.ok().body(voucherService.getVoucherByIde(id));
  }

  @GetMapping("employees")
  public ResponseEntity<List<Employee>> getAllEmp() {
    return ResponseEntity.ok().body(employeeService.getAllEmployee());
  }

  @GetMapping("customers")
  public ResponseEntity<List<Employee>> getAllCustomer() {
    return ResponseEntity.ok().body(employeeService.getAllEmployee());
  }

  @PostMapping("customer")
  public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
    System.out.println(customer);
    return ResponseEntity.ok().body(customerService.saveCustomer(customer));
  }

  @GetMapping("customers/search")
  public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam("searchtext") String searchtext) {
    return ResponseEntity.ok().body(customerService.searchEmployees(searchtext));
  }

  @GetMapping("customers/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("id") UUID idcustomer) {
    return ResponseEntity.ok().body(customerService.getCustomerById(idcustomer).get());
  }

  @GetMapping("productDetails")
  public ResponseEntity<List<ProductDetail>> getAllProductDetails() {
    return ResponseEntity.ok().body(productDetailService.getAllProductDetail());
  }

  @GetMapping("productDetails/{id}")
  public ResponseEntity<ProductDetail> getProduct(@PathVariable("id") int id) {
    Optional<ProductDetail> proc = productDetailService.getProductDetailById(id);
    return ResponseEntity.ok().body(productDetailService.getProductDetailById(id).get());
  }

  @GetMapping("orderDetail")
  public ResponseEntity<OrderCounterRequest> getOrderDetail() {
    OrderCounterRequest.Product pr = new OrderCounterRequest.Product(1, 2);
    OrderCounterRequest orderCounterRequest = new OrderCounterRequest();
    orderCounterRequest.setProducts(Arrays.asList(pr));
    ;
    return ResponseEntity.ok().body(orderCounterRequest);
  }

  @PostMapping("checkout")
  public ResponseEntity<Bill> checkOutBill(@RequestBody OrderCounterRequest orderCounterRequest) {
    if (!orderCounterRequest.hasValidationError()) {
      Bill bill = countService.saveBill(orderCounterRequest);
      return ResponseEntity.ok().body(bill);
    }
    return null;
  }
}
