package datn.goodboy.repository;

import datn.goodboy.model.entity.Cart;
import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
//    @Autowired(required = false)
//    Cart findByMaKH(Customer customer);
//
//    Cart findByMaNV(Employee employee);
}
