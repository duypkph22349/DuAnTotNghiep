package datn.goodboy.service.client;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.VoucherDetail;
import datn.goodboy.model.request.BillClientRequest;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.VoucherDetailRepository;
import datn.goodboy.repository.VoucherRepository;
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

    public Bill addBill(BillClientRequest req) {
        Bill bill = req.getBill();

        bill.setAddress(req.getAddress());
        bill.setMoney_ship(req.getShip_fee());
        bill.setNote(req.getNote());
        bill.setCustomer_name(req.getName());
        bill.setPhone(req.getPhone_number());
        bill.setDeposit(req.getTotal_money());
        bill.setLoaiDon(1);
        bill.setStatus(1);
        if(req.getPayment_method() == 2){
            bill.setPay(payService.getVNPay());
            bill.setStatus_pay(1);
        }else{
            bill.setPay(payService.getCashOnDelivery());
            bill.setStatus_pay(0);
        }

        if(req.getCoupoun() != null){
            VoucherDetail voucherDetail = new VoucherDetail();
            voucherDetail.setBill(bill);
            voucherDetail.setVoucher(req.getCoupoun());
            voucherDetail.setMoney_before_reduction(bill.getTotal_money() + bill.getMoney_ship());
            voucherDetail.setMoney_after_reduction(bill.getDeposit());
            voucherDetail.setMoney_reduction(bill.getDeposit() - bill.getTotal_money() - bill.getMoney_ship());
            voucherDetail.setStatus(0);
            voucherDetailRepository.save(voucherDetail);
        }

        billRepository.save(bill);
        return bill;
    }


}
