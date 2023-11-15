package datn.goodboy.service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Brand;
import datn.goodboy.model.entity.Pay;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PayService {
    private final PayRepository payRepository;


    @Autowired
    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

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

//    public Page<Pay> getPage(Pageable pageable){
//        return payRepository.findAll(pageable);
//    }
//
//    public Pay getById(Integer id) {
//        return payRepository.findById(id).get();
//    }
//
//    public ArrayList<Pay> getAllPay(){
//        return (ArrayList<Pay>) payRepository.findAll();
//    }
//
//    public Pay savePay(Pay pay) {
//
//        return payRepository.save(pay);
//    }
//
//    public Pay update(Integer id, Pay pay) {
//        Pay pay1 = payRepository.findById(id).get();
//        pay1.setUpdate_at(pay.getUpdate_at());
//        pay1.setPayment_method(pay.getPayment_method());
//        return payRepository.save(pay1);
//    }

//    public void updatePaymentMethod(Integer id) {
//        // Tìm kiếm đối tượng Pay trong cơ sở dữ liệu bằng ID
//        Optional<Pay> paymentOptional = payRepository.findById(id);
//
//        // Kiểm tra xem có đối tượng Payment có tồn tại không
//        if (paymentOptional.isPresent()) {
//            Pay payment = paymentOptional.get();
//
//            // Cập nhật giá trị paymentMethod
//            payment.setPayment_method("Updated Payment Method");
//
//            // Lưu lại vào cơ sở dữ liệu
//            payRepository.save(payment);
//        } else {
//            // Xử lý trường hợp không tìm thấy đối tượng Payment với ID cụ thể
//            throw new RuntimeException("Không tìm thấy đối tượng Payment với ID: " + id);
//        }
//    }
}
