package datn.goodboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
//    @Autowired(required = false)
//    Cart findByMaKH(Customer customer);
//
//    Cart findByMaNV(Employee employee);
}
