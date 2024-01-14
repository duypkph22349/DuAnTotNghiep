package datn.goodboy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.Pay;
import datn.goodboy.model.entity.PayDetail;
import datn.goodboy.model.entity.PayDetailId;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.OrderCounterRequest;
import datn.goodboy.repository.BillDetailRepository;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.EmployeeRepository;
import datn.goodboy.repository.PayDetailRepository;
import jakarta.annotation.PostConstruct;

@Service
public class ConterService {
  @Autowired
  BillRepository billRepository;
  @Autowired
  BillDetailRepository billDetailRepository;
  @Autowired
  PayDetailRepository paydetailepository;
  @Autowired
  EmployeeRepository empRepository;
  @Autowired
  ProductDetailService productDetailService;
  @Autowired
  PayService payService;
  @Autowired
  CustomerService cusService;

  @Autowired
  VoucherService voucherService;
  public Pay cashpay;
  public Pay transderPay;
  public Pay counterPay;

  @PostConstruct
  public void initializePayments() {
    cashpay = payService.getCashMethod();
    transderPay = payService.getTransferMethod();
    counterPay = payService.getThanhToanTaiQuayMethod();
  }

  public Bill saveBill(OrderCounterRequest request) {
    Bill bill = new Bill();
    bill.setCustomer_name(request.getCustomerName());
    bill.setPhone(request.getPhoneNumber());
    bill.setStatus(1);
    bill.setConfirmation_date(LocalDateTime.now());
    bill.setLoaiDon(request.getOrderTypes());
    bill.setDeleted(false);
    bill.setReduction_amount(0d);
    bill.setDeposit(0.0);
    bill.setCustomer(cusService.getCounterCustomer());
    Optional<Employee> emp = empRepository.findById(request.getEmployeeID());
    if (emp.isPresent()) {
      bill.setEmployee(emp.get());
    }
    if (request.getOrderTypes() == 0) {
      bill.setPay(payService.getThanhToanTaiQuayMethod());
    } else {
      bill.setPay(payService.getTransferMethod());
    }
    bill = billRepository.save(bill);
    Double total = 0d;
    // bill detail
    List<OrderCounterRequest.Product> products = request.getProducts();
    for (OrderCounterRequest.Product product : products) {
      Optional<ProductDetail> productdetail = productDetailService.getProductDetailById(product.getId());
      if (productdetail.isPresent()) {
        productDetailService.saleProduct(productdetail.get().getId(), product.getQuantity());
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

    // appy voucher
    if (request.getVoucher() > 0) {
      voucherService.useVoucher(bill, request.getVoucher());
    }
    // thanh toan
    if (request.getOrderTypes() == 0) {
      if (request.getCashMoney() > 0) {
        PayDetail payDetail = new PayDetail();
        payDetail.setId(new PayDetailId(bill.getId(), cashpay.getId()));
        payDetail.setPay(cashpay);
        payDetail.setBill(bill);
        payDetail.setTotalMoney(request.getCashMoney());
        payDetail.setStatus(true);
        paydetailepository.save(payDetail);
      }
      if (request.getTransferMoney() > 0) {
        PayDetail payDetail = new PayDetail();
        payDetail.setId(new PayDetailId(bill.getId(), transderPay.getId()));
        payDetail.setPay(transderPay);
        payDetail.setBill(bill);
        payDetail.setTotalMoney(request.getTransferMoney());
        payDetail.setStatus(true);
        paydetailepository.save(payDetail);
      }
      bill.setConfirmation_date(LocalDateTime.now());
      bill.setCompletion_date(LocalDateTime.now());
      bill.setPay(counterPay);
      bill.setStatus(5);
    } else if (request.getOrderTypes() == 1) {
      bill.setConfirmation_date(LocalDateTime.now());
      bill.setAddress(request.getSpecificAddress() + ", " + request.getFullAddress());
      bill.setMoney_ship((double) request.getTotalShip());
      bill.setStatus(2);
      bill.setPay(payService.getTransferMethod());
    }
    bill.setNote(request.getNote());
    return billRepository.save(bill);
  }
}
