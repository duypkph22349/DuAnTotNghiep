package datn.goodboy.service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Brand;
import datn.goodboy.repository.Bill1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Bill1Service {
    @Autowired
    private Bill1Repository bill1Repository;

    public Page<Bill> findAllBill(Pageable pageable){
        return  bill1Repository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<Bill> searchBillByCode(String ma, Pageable pageable) {
        return bill1Repository.findByCodeContains(ma, pageable);
    }
}