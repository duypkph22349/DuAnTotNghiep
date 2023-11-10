package datn.goodboy.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.response.CustomerComboboxResponse;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
  @Query(value = "SELECT new datn.goodboy.model.response.CustomerComboboxResponse(c.id, c.name + ' - '+ c.phone) FROM Customer c")
  public List<CustomerComboboxResponse> getCombobox();

}
