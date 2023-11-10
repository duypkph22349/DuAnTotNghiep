package datn.goodboy.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.response.CustomerComboboxResponse;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
  public Page<Customer> getPageNo(Pageable page);

  public List<Customer> getAllResponse();

  @Query(value = "SELECT new datn.goodboy.model.response.CustomerComboboxResponse(c.id, c.name + ' - '+ c.phone) FROM Customer c")
  public List<CustomerComboboxResponse> getCombobox();

}
