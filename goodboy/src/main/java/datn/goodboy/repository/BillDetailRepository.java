package datn.goodboy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.BillDetail;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {

    @Query("SELECT bd FROM BillDetail bd WHERE bd.idBill.id =:idBill AND bd.productDetail.id =:idProduct")
    BillDetail findByIdBillAndIdProduct(int idBill, int idProduct);

    @Query("SELECT bd FROM BillDetail bd WHERE bd.idBill.id =:idBill")
    List<BillDetail> findByIdListBill(int idBill);

}
