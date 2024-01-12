package datn.goodboy.service;

import datn.goodboy.model.entity.ProductDetail;
import datn.goodboy.repository.BillDetailRepository;
import datn.goodboy.repository.BillRepository;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillDetailService {

    // @Autowired
    // private BillRepository billRepository;
    //
    // @Autowired
    // private BillDetailRepository billDetailRepository;
    //
    // public Page<BillDetail> getPage(Pageable pageable){
    // return billDetailRepository.findByDeletedFalse(pageable);
    // }
    //
    // public List<BillDetail> getAll(){
    // return (List<BillDetail>) billDetailRepository.findAll();
    // }
    //
    // public BillDetail getBillDetailById(Integer idBill) throws NotFoundException
    // {
    // return billDetailRepository.findById(idBill)
    // .orElseThrow(() -> new NotFoundException("Not found"));
    // }
    //
    // public Optional<BillDetail> findByIdBill(int idBill) {
    //
    // return billDetailRepository.findById(idBill);
    // }

}
