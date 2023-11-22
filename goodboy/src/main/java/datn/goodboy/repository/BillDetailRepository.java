package datn.goodboy.repository;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {

//    Page<BillDetail> findByDeletedFalse(Pageable pageable);
//
//    List<BillDetail> findAll();
//
//    Optional<Bill> findByCode(String code);

}
