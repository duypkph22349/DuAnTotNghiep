package datn.goodboy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.Pay;
import datn.goodboy.model.entity.PayDetail;
import datn.goodboy.model.entity.PayDetailId;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.request.OrderCounterRequest;
import datn.goodboy.repository.BillDetailRepository;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.EmployeeRepository;
import datn.goodboy.service.EmailService;
import datn.goodboy.repository.PayDetailRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CounterService {
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

  @Autowired
  EmailService emailService;
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
    bill.setDeposit(0d);
    if (request.getCustomerID() == null) {
      bill.setCustomer(cusService.getCounterCustomer());
    } else {
      Optional<Customer> customer = cusService.getCustomerById(request.getCustomerID());
      if (customer.isPresent()) {
        bill.setCustomer(customer.get());
      }
    }
    Optional<Employee> emp = empRepository.findById(request.getEmployeeID());
    if (emp.isPresent()) {
      bill.setEmployee(emp.get());
    }
    if (request.getOrderTypes() == 0) {
      bill.setPay(payService.getThanhToanTaiQuayMethod());
    } else {
      bill.setPay(payService.getTransferMethod());
    }
    // bill = billRepository.save(bill);
    Double total = 0d;
    // bill detail
    List<OrderCounterRequest.Product> products = request.getProducts();
    for (OrderCounterRequest.Product product : products) {
      Optional<ProductDetail> productdetail = productDetailService.getProductDetailById(product.getId());
      if (productdetail.isPresent()) {
        productDetailService.saleProduct(productdetail.get().getId(), product.getQuantity());
        BillDetail billDetail = new BillDetail();
        billDetail.setProductDetail(productdetail.get());
        billDetail.setIdBill(bill);
        billDetail.setQuantity(product.getQuantity());
        total += product.getQuantity() * (productdetail.get().getPrice());
        billDetail.setTotalMoney(Double.valueOf(product.getQuantity() * (productdetail.get().getPrice())));
        billDetail.setCreatedAt(LocalDateTime.now());
        billDetail.setStatus(1);
        billDetail.setDeleted(false);
        bill.getBillDetail().add(billDetail);
      }
    }
    bill.setTotal_money(total);
    bill = billRepository.save(bill);
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
      bill.setStatus_pay(1);
    } else if (request.getOrderTypes() == 1) {
      bill.setConfirmation_date(LocalDateTime.now());
      bill.setAddress(request.getSpecificAddress() + ", " + request.getFullAddress());
      bill.setMoney_ship((double) request.getTotalShip());
      bill.setStatus(2);
      bill.setPay(payService.getTransferMethod());
    }
    bill.setDeposit((bill.getTotal_money() == null ? 0 : bill.getTotal_money())
        + (bill.getMoney_ship() == null ? 0 : bill.getTotal_money())
        - (bill.getReduction_amount() == null ? 0 : bill.getReduction_amount()));
    bill.setNote(request.getNote());
    bill = billRepository.save(bill);
    // appy voucher

    if (request.getVoucher() > 0) {
      voucherService.useVoucher(bill, request.getVoucher());
    }

    Bill finalBill = billRepository.save(bill);
    if (finalBill.getCustomer() != null) {
      if (finalBill.getCustomer().getAccount() != null) {
        CompletableFuture.runAsync(() -> {
          emailService.sendEmailBill(finalBill.getId(), "Đơn hàng của bạn");
        });
      }
    }
    return finalBill;
  }

  public Bill saveWaitBill(OrderCounterRequest orderCounterRequest) {
    return null;
  }
}
