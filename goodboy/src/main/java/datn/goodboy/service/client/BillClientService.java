package datn.goodboy.service.client;

import datn.goodboy.model.entity.*;
import datn.goodboy.model.request.BillClientRequest;
import datn.goodboy.repository.*;
import datn.goodboy.security.service.AccountInfoService;
import datn.goodboy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BillClientService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PayService payService;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherDetailRepository voucherDetailRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private AccountInfoService userService;

    @Autowired
    private CustomerRepository customerRepository;

    public Bill addBill(BillClientRequest req) {
        Bill bill = req.getBill();

        bill.setAddress(req.getAddress());
        bill.setMoney_ship(req.getShip_fee());
        bill.setNote(req.getNote());
        bill.setCustomer_name(req.getName());
        bill.setPhone(req.getPhone_number());
        bill.setDeposit(req.getTotal_money());
        bill.setReduction_amount(bill.getTotal_money() - (bill.getDeposit() -  bill.getMoney_ship()));
        bill.setLoaiDon(1);
        bill.setStatus(1);
        if(req.getPayment_method() == 2){
            bill.setPay(payService.getVNPay());
            bill.setStatus_pay(1);
            bill.setStatus(2);
        }else{
            bill.setPay(payService.getCashOnDelivery());
            bill.setStatus_pay(0);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Account user = userService.getAccountByEmail(authentication.getName());
            bill.setCustomer(user.getCustomer());
        }

        for(BillDetail billDetail: bill.getBillDetail()){
            ProductDetail productDetail = productDetailRepository.findProductByLongId(billDetail.getProductDetail().getId());
            if(productDetail.getQuantity() < billDetail.getQuantity()){
                throw  new RuntimeException("Số lượng sản phẩm hiện tại không đủ. Vui lòng trở lại giỏ hàng");
            }
            productDetail.setQuantity(productDetail.getQuantity() - billDetail.getQuantity());
            productDetailRepository.save(productDetail);
            billDetail.setIdBill(bill);
        }
        bill = billRepository.save(bill);

        for(BillDetail billDetail: bill.getBillDetail()){
            billDetailRepository.save(billDetail);
        }

        if(req.getCoupoun() != null){
            VoucherDetail voucherDetail = new VoucherDetail();
            voucherDetail.setBill(bill);
            voucherDetail.setVoucher(req.getCoupoun());
            voucherDetail.setMoney_before_reduction(bill.getTotal_money());
            voucherDetail.setMoney_after_reduction(bill.getDeposit() -  bill.getMoney_ship());
            voucherDetail.setMoney_reduction(bill.getTotal_money() - (bill.getDeposit() -  bill.getMoney_ship()));
            voucherDetail.setStatus(0);
            voucherDetailRepository.save(voucherDetail);
        }
        return bill;
    }

    public Bill getBillById(String id){
        Bill bill =  billRepository.findById(Integer.parseInt(id)).get();

        if(bill == null){
            throw new RuntimeException("Không tìm thấy hóa đơn.");
        }
        return bill;
    }

    public Bill getBillByCode(String code){
        Bill bill = billRepository.findByCode(code).get();
        if(bill == null){
            throw new RuntimeException("Không tìm hóa đơn.");
        }
        return bill;
    }
}
