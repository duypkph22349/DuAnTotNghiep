package datn.goodboy.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.response.TopProductSales;

@Repository
public interface ThongKeRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "SELECT COUNT(b) FROM Bill b WHERE b.createdAt BETWEEN :dateFrom AND :dateTo")
    int totalBill(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

    @Query(value = "SELECT SUM(b.total_money) FROM Bill b WHERE b.createdAt BETWEEN :dateFrom AND :dateTo")
    int totalIncome(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

    @Query(value = "SELECT SUM(bd.quantity) " +
            "FROM BillDetail bd " +
            "JOIN bd.idBill b " +
            "JOIN bd.productDetail pd " +
            "WHERE b.createdAt BETWEEN :dateFrom AND :dateTo AND b.deleted = false")
    int totalProductSale(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

//     @Query("SELECT new datn.goodboy.model.response.TopProductSales(pd, pd.name, pd.price, SUM(bd.quantity), SUM(bd.totalMoney)) "
//             +
//             "FROM BillDetail bd " +
//             "JOIN bd.idBill b " +
//             "JOIN bd.productDetail pd " +
//             "WHERE b.createdAt BETWEEN :dateFrom AND :dateTo AND b.status != -1 AND b.deleted = false "
//             +
//             "GROUP BY pd.id, pd.name, pd.price, pd.code, pd.createdAt, pd.deleted , pd  " + // Add pd.created_at to the
//                                                                                             // GROUP BY clause
//             "ORDER BY SUM(bd.totalMoney) DESC")
//     List<TopProductSales> getTopProductsSale(@Param("dateFrom") LocalDateTime dateFrom,
//             @Param("dateTo") LocalDateTime dateTo);

}
