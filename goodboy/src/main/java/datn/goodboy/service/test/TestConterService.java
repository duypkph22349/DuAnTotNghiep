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
import datn.goodboy.model.entity.PayDetail;
import datn.goodboy.model.entity.PayDetailId;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.OrderCounterRequest;
import datn.goodboy.repository.BillDetailRepository;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.EmployeeRepository;
import datn.goodboy.repository.PayDetailRepository;
import datn.goodboy.repository.ProductDetailRepository;
import datn.goodboy.service.PayService;

@Service
public class TestConterService {
  @Autowired
  BillRepository billRepository;
  @Autowired
  BillDetailRepository billDetailRepository;
  @Autowired
  PayDetailRepository paydetailepository;
  @Autowired
  EmployeeRepository empRepository;
  @Autowired
  ProductDetailRepository productDetailRepository;
  @Autowired
  PayService payService;

  public Bill saveBill(OrderCounterRequest request) {
    Bill bill = new Bill();
    bill.setConfirmation_date(LocalDateTime.now());
    bill.setCustomer_name(request.getCustomerName());
    bill.setPhone(request.getPhoneNumber());
    bill.setStatus(1);
    bill.setLoaiDon(request.getOrderTypes());
    bill.setConfirmation_date(LocalDateTime.now());
    bill.setLoaiDon(request.getOrderTypes());
    bill.setDeleted(false);
    bill.setReduction_amount(0);
    bill.setDeposit(0);
    bill.setReduction_amount(0);
    Optional<Employee> emp = empRepository.findById(request.getEmployeeID());
    if (emp.isPresent()) {
      bill.setEmployee(emp.get());
      bill.setCustomer_name(emp.get().getName());
    }
    bill = billRepository.save(bill);
    // thanh toan
    if (request.getOrderTypes() == 0) {
      if (request.getCashMoney() >= 0) {
        PayDetail payDetail = new PayDetail();
        payDetail.setId(new PayDetailId(bill.getId(), payService.getCashMethod().getId()));
        payDetail.setTotalMoney(request.getCashMoney());
        payDetail.setStatus(true);
      } else if (request.getTransferMoney() >= 0) {
        PayDetail payDetail = new PayDetail();
        payDetail.setId(new PayDetailId(bill.getId(), payService.getTransferMethod().getId()));
        payDetail.setTotalMoney(request.getTransferMoney());
        payDetail.setStatus(true);
      }
      bill.setCompletion_date(LocalDateTime.now());
      bill.setStatus(1);
    } else if (request.getOrderTypes() == 1) {
      bill.setAddress(request.getSpecificAddress() + ", " + request.getFullAddress());
      bill.setMoney_ship(request.getTotalShip());
      bill.setStatus(0);
      bill.setPay(payService.getTransferMethod());
    }
    bill.setNote(request.getNote());
    Double total = 0d;
    // bill detail
    List<OrderCounterRequest.Product> products = request.getProducts();
    for (OrderCounterRequest.Product product : products) {
      Optional<ProductDetail> productdetail = productDetailRepository.findById(product.getId());
      if (productdetail.isPresent()) {
        BillDetail billDetail = new BillDetail();
        billDetail.setIdBill(bill);
        billDetail.setProductDetail(productdetail.get());
        billDetail.setQuantity(product.getQuantity());
        total += product.getQuantity() * (productdetail.get().getPrice());
        billDetail.setTotalMoney(Double.valueOf(product.getQuantity() * (productdetail.get().getPrice())));
        billDetail.setCreatedAt(LocalDateTime.now());
        billDetail.setStatus(1);
        billDetail.setDeleted(false);
        billDetailRepository.save(billDetail);
      }
    }
    bill.setTotal_money(total);
    return bill;
  }
}
