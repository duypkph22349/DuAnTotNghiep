package datn.goodboy.service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.Cart;
import datn.goodboy.repository.BillRepository;
import datn.goodboy.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {

        this.billRepository = billRepository;
    }


    public Page<Bill> getPage(Pageable pageable){
        return billRepository.findAll(pageable);
    }

    public ArrayList<Bill> getAllBill(){
        return (ArrayList<Bill>) billRepository.findAll();
    }


    public Bill saveBill(Bill bill) {

        return billRepository.save(bill);
    }

    public void deleteBill(int id) {

        billRepository.deleteById(id);
    }

    public Optional<Bill> findByIdBill(int id) {

        return billRepository.findById(id);
    }

}
