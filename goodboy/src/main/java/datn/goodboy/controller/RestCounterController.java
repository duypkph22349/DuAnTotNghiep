package datn.goodboy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.OrderCounterRequest;
import datn.goodboy.service.ConterService;
import datn.goodboy.service.EmployeeService;
import datn.goodboy.service.ProductDetailService;
import datn.goodboy.service.VoucherService;

@RestController("countercartresttest")
@RequestMapping("rest/data/counter")
public class RestCounterController {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private ProductDetailService productDetailService;

  @Autowired
  private VoucherService voucherService;
  @Autowired
  private ConterService countService;
  @GetMapping("voucherAble")
    public ResponseEntity<List<Voucher>> getAbleVoucher(){
      return ResponseEntity.ok().body(voucherService.getAllVoucherAble());
    }
  @GetMapping("employees")
  public ResponseEntity<List<Employee>> getAllEmp() {
    return ResponseEntity.ok().body(employeeService.getAllEmployee());
  }

  @GetMapping("customers")
  public ResponseEntity<List<Employee>> getAllCustomer() {
    return ResponseEntity.ok().body(employeeService.getAllEmployee());
  }

  @GetMapping("productDetails")
  public ResponseEntity<List<ProductDetail>> getAllProductDetails() {
    return ResponseEntity.ok().body(productDetailService.getPageNo(1, 20, "createdAt", true));
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
  public ResponseEntity<OrderCounterRequest> checkOutBill(@RequestBody OrderCounterRequest orderCounterRequest) {
    System.out.println(orderCounterRequest.toString());
    if (!orderCounterRequest.hasValidationError()) {
      Bill bill = countService.saveBill(orderCounterRequest);
      return ResponseEntity.ok().body(orderCounterRequest);
    }
    return null;
  }
}
