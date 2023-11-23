package datn.goodboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.BillDetail;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {

//    Page<BillDetail> findByDeletedFalse(Pageable pageable);
//
//    List<BillDetail> findAll();
//
//    Optional<Bill> findByCode(String code);

}
