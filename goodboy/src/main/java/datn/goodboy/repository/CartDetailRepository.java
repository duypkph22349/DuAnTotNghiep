package datn.goodboy.repository;

import datn.goodboy.model.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {


    @Query("SELECT SUM(price) FROM CartDetail ")
    BigDecimal getTotal(List<CartDetail> list);
}
