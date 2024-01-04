package datn.goodboy.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.model.entity.CartDetail;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.AccountRepository;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.CartDetailRepository;
import datn.goodboy.repository.CartRepository;
import datn.goodboy.repository.ProductDetailRepository;

@Service("testBillService")
public class BillService {
  @Autowired
  CartRepository cartRepository;
  @Autowired
  CartDetailRepository cartDetailRepository;
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  ProductDetailRepository productDetailRepository;
  @Autowired
  BillRepository billRepository;

  public Bill getCheckOutPage(List<Integer> cartDetails) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUserName = authentication.getName();
      Account account = accountRepository.fillAcccoutbyEmail(currentUserName);
      Bill bill = new Bill();
      bill.setCustomer(account.getCustomer());
      bill.setCustomer_name(account.getCustomer().getName());
      bill.setPhone(account.getCustomer().getPhone());
      bill.setAddress(account.getCustomer().getAddress());
      bill.setStatus(0);
      bill.setLoaiDon(1);
      bill.setStatus_pay(0);
      List<BillDetail> billDetails = cartDetails.stream()
          .map(idcartdetails -> cartDetailRepository.findById(idcartdetails))
          .filter(Optional::isPresent)
          .map(Optional::get)
          .map(cartDetail -> {
            BillDetail billDetail = new BillDetail();
            billDetail.setIdBill(bill);
            billDetail.setProductDetail(cartDetail.getProductDetail());
            billDetail.setQuantity(cartDetail.getQuantity());
            billDetail
                .setTotalMoney(Double.valueOf(cartDetail.getProductDetail().getPrice() * cartDetail.getQuantity()));
            return billDetail;
          })
          .collect(Collectors.toList());
      bill.setBillDetail(billDetails);
      double totalMoney = bill.getBillDetail().stream().mapToDouble(BillDetail::getTotalMoney).sum();
      bill.setTotal_money(totalMoney);
      bill.setDeposit(0d);
      bill.setMoney_ship(0d);
      return bill;
    }
    return null;
  }

  public Customer getCustomer() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUserName = authentication.getName();
      Account account = accountRepository.fillAcccoutbyEmail(currentUserName);
      return account.getCustomer();
    }
    return null;
  }

  public Bill cancelInvoice(int idBill) {
    Optional<Bill> bill = billRepository.findById(idBill);
    if (bill.isPresent()) {
      if (bill.get().getStatus() == 1) {
        bill.get().setStatus(-1);
        bill.get().getBillDetail().stream().forEach(billdetail -> {
          Optional<ProductDetail> productDetails = productDetailRepository
              .findById(billdetail.getProductDetail().getId());
          if (productDetails.isPresent()) {
            productDetails.get()
                .setQuantity(productDetails.get().getQuantity() + billdetail.getQuantity());
            productDetailRepository.save(productDetails.get());
          }
        });
      }
    }
    return null;
  }

  public Bill saveHoaDon(Bill bill) {
    billRepository.save(bill);
    return null;
  }

  public void succeedBill(int idBill) {
    Optional<Bill> bill = billRepository.findById(idBill);
    if (bill.isPresent()) {
      bill.get().getBillDetail().stream().forEach(billdetail -> {
        Optional<ProductDetail> productDetails = productDetailRepository
            .findById(billdetail.getProductDetail().getId());
        if (productDetails.isPresent()) {
          productDetails.get()
              .setQuantity(productDetails.get().getQuantity() - billdetail.getQuantity());
          productDetailRepository.save(productDetails.get());
        }
      });
    }
  }

}
