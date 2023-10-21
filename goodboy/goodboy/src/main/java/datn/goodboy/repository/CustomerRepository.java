package datn.goodboy.repository;

import datn.goodboy.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
//    @Query(value = "SELECT new datn.goodboy.model.response.CustomerResponse(c.id, c.code, c.name, c.gender, c.birth_date, c.phone, c.address, c.city, c.country, c.status) FROM Customer c")
//    public Page<CustomerResponse> getPageNo(Pageable page);
//
//    @Query(value = "SELECT new datn.goodboy.model.response.CustomerResponse(c.id, c.code, c.name, c.gender, c.birth_date, c.phone, c.address, c.city, c.country, c.status) FROM Customer c")
//    public List<CustomerResponse> getAllResponse();
//
//    @Query(value = "SELECT new datn.goodboy.model.response.CustomerComboboxResponse(c.id, c.name + ' - '+ c.phone) FROM Customer c")
//    public List<CustomerComboboxResponse> getCombobox();
}
