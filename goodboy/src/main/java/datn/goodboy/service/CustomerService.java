package datn.goodboy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.response.CustomerComboboxResponse;
import datn.goodboy.model.response.CustomerResponse;
import datn.goodboy.repository.CustomerRepository;

@Service
public class CustomerService {

  // Declare the repository as final to ensure its immutability
  private final CustomerRepository customerRepository;

  // Use constructor-based dependency injection
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public List<CustomerComboboxResponse> getComboBox() {
    return customerRepository.getCombobox();
  }
  public Optional<Customer> getCustomerById(UUID id) {
    return customerRepository.findById(id);
  }

  public Customer saveCustomer(Customer account) {
    return customerRepository.save(account);
  }

  public void deleteCustomer(UUID id) {
    customerRepository.deleteById(id);
  }

  public List<CustomerResponse> getPageNo(int pageNo) {
    return customerRepository.getPageNo(PageRequest.of(pageNo, 3)).getContent();
  }
  // public List<CustomerResponse> getPageNo(int pageNo) {
  // return customerRepository.getAllResponse();
  // }
  // manager
}
