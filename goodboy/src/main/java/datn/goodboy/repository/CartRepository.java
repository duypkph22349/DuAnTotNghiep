package datn.goodboy.repository;

import datn.goodboy.model.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Cart;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
//    @Autowired(required = false)
//    Cart findByMaKH(Customer customer);
//
//    Cart findByMaNV(Employee employee);

    @Query(value = """
            SELECT SUM(cd.quantity) as quantity FROM cart c
            JOIN carts_detail cd ON cd.id_cart = c.id
            WHERE c.id_customer = :id_customer
            GROUP BY c.id
    """, nativeQuery = true)
    Integer getQuantityByIdCustomer(@Param("id_customer") UUID idCustomer);

    @Query(value = "SELECT * FROM voucher " ,nativeQuery = true)
    Object findVoucherByCode(@Param("code") String code);
}
