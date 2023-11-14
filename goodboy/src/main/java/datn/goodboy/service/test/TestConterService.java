package datn.goodboy.service.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.OrderCounterRequest;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.EmployeeRepository;
import datn.goodboy.repository.ProductDetailRepository;

@Service
public class TestConterService {
  @Autowired
  BillRepository billRepository;
  @Autowired
  EmployeeRepository empRepository;
  @Autowired
  ProductDetailRepository productDetailRepository;

  public Bill saveBill(OrderCounterRequest request) {
    Bill bill = new Bill();
    bill.setConfirmation_date(LocalDateTime.now()); // You may need to adjust this based on your logic
    bill.setCustomer_name(request.getCustomerName());
    bill.setPhone(request.getPhoneNumber());
    bill.setAddress(request.getSpecificAddress() + ", " + request.getFullAddress());
    bill.setStatus(1);
    bill.setLoaiDon(request.getOrderTypes());
    bill.setCreatedAt(LocalDateTime.now());
    Optional<Employee> emp = empRepository.findById(request.getEmployeeID());
    if (emp.isPresent()) {
      bill.setEmployee(emp.get());
    }
    List<OrderCounterRequest.Product> products = request.getProducts();
    for (OrderCounterRequest.Product product : products) {
      Optional<ProductDetail> productdetail = productDetailRepository.findById(product.getId());
      if (productdetail.isPresent()) {
        BillDetail billDetail = new BillDetail();
        billDetail.setIdBill(bill);
        billDetail.setProductDetail(productdetail.get());
        billDetail.setQuantity(product.getQuantity());
        billDetail.setTotalMoney(BigDecimal.valueOf(product.getQuantity() * (productdetail.get().getPrice())));
        billDetail.setCreatedAt(LocalDateTime.now());
        billDetail.setStatus(1);
        billDetail.setDeleted(false);
      }
    }
    return bill;
  }
}
