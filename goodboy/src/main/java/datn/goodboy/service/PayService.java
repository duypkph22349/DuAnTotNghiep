package datn.goodboy.service;

import datn.goodboy.model.entity.Bill;
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

    @Autowired
    private PayRepository payRepository;

    @Autowired
    public PayService(PayRepository payRepository) {

        this.payRepository = payRepository;
    }


    public Page<Pay> getPage(Pageable pageable){
        return payRepository.findAll(pageable);
    }

    public ArrayList<Pay> getAllPay(){
        return (ArrayList<Pay>) payRepository.findAll();
    }

    public Pay savePay(Pay pay) {

        return payRepository.save(pay);
    }

    public Optional<Pay> findByIdPay(int id) {

        return payRepository.findById(id);
    }
}
