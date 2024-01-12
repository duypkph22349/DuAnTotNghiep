package datn.goodboy.service.client;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.model.entity.VoucherDetail;
import datn.goodboy.model.request.BillClientRequest;
import datn.goodboy.repository.*;
import datn.goodboy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        }else{
            bill.setPay(payService.getCashOnDelivery());
            bill.setStatus_pay(0);
        }

        for(BillDetail billDetail: bill.getBillDetail()){
            ProductDetail productDetail = billDetail.getProductDetail();
            if(productDetail.getQuantity() < billDetail.getQuantity()){
                throw  new RuntimeException("Số lượng sản phẩm hiện tại không đủ. Vui lòng trở lại giỏ hàng");
            }
            productDetail.setQuantity(productDetail.getQuantity() - billDetail.getQuantity());
            productDetailRepository.save(productDetail);
            billDetail.setIdBill(bill);
        }
        billRepository.save(bill);

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


}
