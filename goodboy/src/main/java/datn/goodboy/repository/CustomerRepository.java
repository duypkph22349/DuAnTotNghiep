package datn.goodboy.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.response.CustomerResponse;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
  @Query(value = "SELECT new datn.goodboy.model.response.CustomerResponse(c.id, c.code, c.name, c.gender, c.birth_date, c.phone, c.address, c.city, c.country, c.status) FROM Customer c")
  public Page<CustomerResponse> getPageNo(Pageable page);

  @Query(value = "SELECT new datn.goodboy.model.response.CustomerResponse(c.id, c.code, c.name, c.gender, c.birth_date, c.phone, c.address, c.city, c.country, c.status) FROM Customer c")
  public List<CustomerResponse> getAllResponse();
}
// UUID id;
// String code;
// String name;
// boolean gender;
// LocalDateTime birth_date;
// String phone;
// String address;
// String city;
// String country;
// int status;
// (id,code,name,gender,birth_date,phone,address,city,country,status) FROM
// customer