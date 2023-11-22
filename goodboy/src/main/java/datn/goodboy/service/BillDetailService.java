package datn.goodboy.service;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import datn.goodboy.repository.BillDetailRepository;
import datn.goodboy.repository.BillRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillDetailService {

//    @Autowired
//    private BillRepository billRepository;
//
//    @Autowired
//    private BillDetailRepository billDetailRepository;
//
//    public Page<BillDetail> getPage(Pageable pageable){
//        return billDetailRepository.findByDeletedFalse(pageable);
//    }
//
//    public List<BillDetail> getAll(){
//        return (List<BillDetail>) billDetailRepository.findAll();
//    }
//
//    public BillDetail getBillDetailById(Integer idBill) throws NotFoundException {
//        return  billDetailRepository.findById(idBill)
//                .orElseThrow(() -> new NotFoundException("Not found"));
//    }
//
//    public Optional<BillDetail> findByIdBill(int idBill) {
//
//        return billDetailRepository.findById(idBill);
//    }

}
