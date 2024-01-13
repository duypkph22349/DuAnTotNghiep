package datn.goodboy.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Bill;
import datn.goodboy.model.entity.ProductDetail;

@Repository
public interface ThongKeRepository extends JpaRepository<Bill, Integer> {
        @Query(value = "SELECT COUNT(b) FROM Bill b WHERE b.createdAt BETWEEN :dateFrom AND :dateTo")
        int totalBill(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

        @Query(value = "SELECT SUM(b.deposit) FROM Bill b WHERE b.createdAt BETWEEN :dateFrom AND :dateTo AND b.status = 5")
        Long totalIncome(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

        @Query(value = "SELECT SUM(b.deposit) FROM Bill b WHERE EXTRACT(HOUR FROM b.createdAt) = :hour " +
                        "AND b.createdAt BETWEEN :dateFrom AND :dateTo  AND b.status = 5")
        Long totalIncomeForHour(@Param("hour") int hour,
                        @Param("dateFrom") LocalDateTime dateFrom,
                        @Param("dateTo") LocalDateTime dateTo);

        @Query(value = "SELECT SUM(b.deposit) FROM Bill b WHERE DATEPART(dw, b.created_at) = :dayOfWeek + 1 " +
                        "AND b.created_at BETWEEN :dateFrom AND :dateTo  AND b.status = 5", nativeQuery = true)
        Long totalIncomeDayInWeeks(@Param("dayOfWeek") int dayOfWeek,
                        @Param("dateFrom") LocalDateTime dateFrom,
                        @Param("dateTo") LocalDateTime dateTo);

        @Query(value = "SELECT SUM(bd.quantity) " +
                        "FROM BillDetail bd " +
                        "JOIN bd.idBill b " +
                        "JOIN bd.productDetail pd " +
                        "WHERE b.createdAt BETWEEN :dateFrom AND :dateTo AND b.deleted = false  AND b.status = 5")
        int totalProductSale(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

        @Query(value = "SELECT\r\n TOP 5" + //
                        "  pd.[id] ,pd.[name] ,pd.[price]\r\n" + //
                        ",sum(bd.[quantity])\r\n" + //
                        ",sum(bd.[total_money])\r\n" + //
                        "from [bill_detail] bd join [bill] b on b.[id]=bd.[id_bill]\r\n" + //
                        "        join [product_detail] pd on pd.[id]=bd.[id_product_detail]\r\n" + //
                        "where (b.[created_at] BETWEEN :dateFrom AND :dateTo) AND b.[status] = 5 and b.[deleted] = 0\r\n"
                        + //
                        "group by pd.[id],pd.[name],pd.[price]\r\n" + //
                        "order by sum(bd.[total_money]) desc", nativeQuery = true)
        List<Object> getTopProductsSale(@Param("dateFrom") LocalDateTime dateFrom,
                        @Param("dateTo") LocalDateTime dateTo);

        @Query("SELECT pd FROM ProductDetail pd WHERE pd.id = :id")
        Optional<ProductDetail> getProductByLongId(@Param("id") Long id);
}
