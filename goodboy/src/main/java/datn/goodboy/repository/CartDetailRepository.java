package datn.goodboy.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {

//    CartDetail findByMaGHAndMactsp(Cart cart, ProductDetail productDetail);
    @Query("SELECT SUM(cd.price) FROM CartDetail cd WHERE cd.cart.id IN :cartIds")
    BigDecimal getTotal(@Param("cartIds") List<Integer> cartIds);

    @Query("SELECT SUM(cd.quantity) FROM CartDetail cd WHERE cd.cart.id IN :cartIds")
    Integer getQuantity(@Param("cartIds") List<Integer> cartIds);

//    @Query("SELECT COUNT(cd.id) FROM CartDetail cd WHERE cd.cart.id IN :cartIds")
//    Integer getTotalCart(Integer soluong);


    List<CartDetail> findAllByCartId(int cartId);
}
