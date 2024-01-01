package datn.goodboy.repository;

import datn.goodboy.model.entity.Product;
import datn.goodboy.model.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.BillDetail;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {

//    Page<BillDetail> findByDeletedFalse(Pageable pageable);
//
//    List<BillDetail> findAll();
//
//    Optional<Bill> findByCode(String code);
        @Query("SELECT bd.productDetail, SUM(bd.quantity) AS totalQuantity " +
                "FROM BillDetail bd " +
                "GROUP BY bd.productDetail " +
                "ORDER BY totalQuantity DESC")
        List<ProductDetail> findTop10BestProducts();
    // Page<BillDetail> findByDeletedFalse(Pageable pageable);
    //
    // List<BillDetail> findAll();
    //
    // Optional<Bill> findByCode(String code);

    @Query("SELECT bd FROM BillDetail bd WHERE bd.idBill.id =:idBill AND bd.productDetail.id =:idProduct")
    BillDetail findByIdBillAndIdProduct(int idBill, int idProduct);

        @Query("SELECT bd.productDetail, SUM(bd.quantity) AS totalQuantity FROM BillDetail bd GROUP BY bd.productDetail")
        List<ProductDetail> countTotalQuantityByProductDetail();
}
