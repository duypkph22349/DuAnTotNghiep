package datn.goodboy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import datn.goodboy.controller.RestController.OpenApiClient;
import datn.goodboy.model.entity.DiaChi.District;
import datn.goodboy.model.entity.DiaChi.Province;
import datn.goodboy.model.entity.DiaChi.Ward;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Customer;
import datn.goodboy.model.response.CustomerResponse;
import datn.goodboy.repository.CustomerRepository;

@Service
public class CustomerService {

    // Declare the repository as final to ensure its immutability
    private final CustomerRepository customerRepository;

    @Autowired
    private final OpenApiClient openApiClient;

    public CustomerService(CustomerRepository customerRepository, OpenApiClient openApiClient) {
        this.customerRepository = customerRepository;
        this.openApiClient = openApiClient;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Page<Customer> getPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Optional<Customer> getCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
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

//  public List<Province> getAllProvinces() {
//    String apiUrl = "https://provinces.open-api.vn/api/p/";
//    ResponseEntity<List<Province>> response = restTemplate.exchange(
//            apiUrl,
//            HttpMethod.GET,
//            null,
//            new ParameterizedTypeReference<List<Province>>() {}
//    );
//    System.out.println("Service"+response.getBody());
//
//    return response.getBody();
//  }
//
//  public List<District> getDistrictsByProvinceId(String provinceId) {
//    String apiUrl = "https://provinces.open-api.vn/api/d/";
//    String fullUrl = apiUrl + provinceId;
//    ResponseEntity<List<District>> response = restTemplate.exchange(
//            fullUrl,
//            HttpMethod.GET,
//            null,
//            new ParameterizedTypeReference<List<District>>() {}
//    );
//    return response.getBody();
//  }
//
//  public List<Ward> getWardsByDistrictId(String districtId) {
//    String apiUrl = "https://provinces.open-api.vn/api/w/";
//    String fullUrl = apiUrl + districtId;
//    ResponseEntity<List<Ward>> response = restTemplate.exchange(
//            fullUrl,
//            HttpMethod.GET,
//            null,
//            new ParameterizedTypeReference<List<Ward>>() {}
//    );
//    return response.getBody();
//  }

//    public List<Province> getAllProvinces() {
//        return openApiClient.getAllProvinces();
//    }
//
//    public List<District> getAllDistricts() {
//        return openApiClient.getAllDistricts();
//    }
//
//    public List<Ward> getAllWards() {
//        return openApiClient.getAllWards();
//    }

  // Declare the repository as final to ensure its immutability
  private final CustomerRepository customerRepository;

  // Use constructor-based dependency injection
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Page<Customer> getPage(Pageable pageable) {
    return customerRepository.findAll(pageable);
  }
  public Optional<Customer> getCustomerById(UUID id) {
    return customerRepository.findById(id);
  }

  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public void deleteCustomer(UUID id) {
    customerRepository.deleteById(id);
  }

  public List<CustomerResponse> getPageNo(int pageNo) {
    return customerRepository.getPageNo(PageRequest.of(pageNo, 3)).getContent();
  }
}
