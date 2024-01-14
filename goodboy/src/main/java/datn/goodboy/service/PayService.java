package datn.goodboy.service;

import java.time.LocalDateTime;
import java.util.Optional;

import datn.goodboy.service.serviceinterface.PayMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Pay;
import datn.goodboy.repository.PayRepository;

@Service
public class PayService  {
    @Autowired
    PayRepository payRepository;

    public Optional<Pay> findPayById(int id) {
        return payRepository.findById(id);
    }

    public void updatePaymentMethod(int id, String paymentMethod) {
        Optional<Pay> optionalPay = payRepository.findById(id);
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            pay.setPayment_method(paymentMethod);
            payRepository.save(pay);
        }
    }

    public Pay getCashMethod() {
        Optional<Pay> optionalPay = payRepository.getByNameMethod("CASH");
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            return optionalPay.get();
        }
        Pay paymethod = new Pay();
        paymethod.setPayment_method("CASH");
        paymethod.setDeleted(0);
        paymethod.setStatus(1);
        paymethod.setCreated_at(LocalDateTime.now());
        return payRepository.save(paymethod);
    }

    public Pay getTransferMethod() {
        Optional<Pay> optionalPay = payRepository.getByNameMethod("TRANSFER");
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            return optionalPay.get();
        }
        Pay paymethod = new Pay();
        paymethod.setPayment_method("TRANSFER");
        paymethod.setDeleted(0);
        paymethod.setStatus(1);
        paymethod.setCreated_at(LocalDateTime.now());
        return payRepository.save(paymethod);
    }

    public Pay getThanhToanTaiQuayMethod() {
        Optional<Pay> optionalPay = payRepository.getByNameMethod("COUNTER");
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            return optionalPay.get();
        }
        Pay paymethod = new Pay();
        paymethod.setPayment_method("COUNTER");
        paymethod.setDeleted(0);
        paymethod.setStatus(1);
        paymethod.setCreated_at(LocalDateTime.now());
        return payRepository.save(paymethod);
    }

    public Pay getVNPay() {
        Optional<Pay> optionalPay = payRepository.getByNameMethod("VNPAY");
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            return optionalPay.get();
        }
        Pay paymethod = new Pay();
        paymethod.setPayment_method("VNPAY");
        paymethod.setDeleted(0);
        paymethod.setStatus(1);
        paymethod.setCreated_at(LocalDateTime.now());
        return payRepository.save(paymethod);
    }

    public Pay getCashOnDelivery() {
        Optional<Pay> optionalPay = payRepository.getByNameMethod("Thanh toán khi nhận hàng");
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            return optionalPay.get();
        }
        Pay paymethod = new Pay();
        paymethod.setPayment_method("Thanh toán khi nhận hàng");
        paymethod.setDeleted(0);
        paymethod.setStatus(1);
        paymethod.setCreated_at(LocalDateTime.now());
        return payRepository.save(paymethod);
    }
    // public Page<Pay> getPage(Pageable pageable){
    // return payRepository.findAll(pageable);
    // }
    //
    // public Pay getById(Integer id) {
    // return payRepository.findById(id).get();
    // }
    //
    // public ArrayList<Pay> getAllPay(){
    // return (ArrayList<Pay>) payRepository.findAll();
    // }
    //
    // public Pay savePay(Pay pay) {
    //
    // return payRepository.save(pay);
    // }
    //
    // public Pay update(Integer id, Pay pay) {
    // Pay pay1 = payRepository.findById(id).get();
    // pay1.setUpdate_at(pay.getUpdate_at());
    // pay1.setPayment_method(pay.getPayment_method());
    // return payRepository.save(pay1);
    // }

    // public void updatePaymentMethod(Integer id) {
    // // Tìm kiếm đối tượng Pay trong cơ sở dữ liệu bằng ID
    // Optional<Pay> paymentOptional = payRepository.findById(id);
    //
    // // Kiểm tra xem có đối tượng Payment có tồn tại không
    // if (paymentOptional.isPresent()) {
    // Pay payment = paymentOptional.get();
    //
    // // Cập nhật giá trị paymentMethod
    // payment.setPayment_method("Updated Payment Method");
    //
    // // Lưu lại vào cơ sở dữ liệu
    // payRepository.save(payment);
    // } else {
    // // Xử lý trường hợp không tìm thấy đối tượng Payment với ID cụ thể
    // throw new RuntimeException("Không tìm thấy đối tượng Payment với ID: " + id);
    // }
    // }
}
