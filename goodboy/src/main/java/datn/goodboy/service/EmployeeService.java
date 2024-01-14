package datn.goodboy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import datn.goodboy.model.entity.Voucher;
import datn.goodboy.model.request.EmployeeRequest;
import datn.goodboy.model.request.VoucherRequest;
import datn.goodboy.service.cloud.CloudinaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.response.EmployeeResponse;
import datn.goodboy.repository.EmployeeRepository;
import datn.goodboy.service.serviceinterface.PanigationInterface;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeService implements PanigationInterface<Employee> {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    CloudinaryImageService cloudService;

    public Page<Employee> getPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public ArrayList<Employee> getAllEmployee() {
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }


    public Employee saveEmployeeImage(EmployeeRequest employeeRequest, List<MultipartFile> listImage) throws IOException {
        Employee employee = new Employee();
        employee.setEmail(employeeRequest.getEmail());
        employee.setName(employeeRequest.getName());
        employee.setRoles(employeeRequest.getRoles());
        employee.setGender(employeeRequest.isGender());
        employee.setPhone(employeeRequest.getPhone());
        employee.setCccd(employeeRequest.getCccd());
        employee.setBirth_date(employeeRequest.getBirth_date());
        employee.setCity(employeeRequest.getCity());
        employee.setAddress(employeeRequest.getAddress());
        employee.setCountry(employeeRequest.getCountry());
        employee.setFulladdress(employeeRequest.getFulladdress());
        employee.setStatus(employeeRequest.getStatus());
        employee.setDeleted(false);
        Employee savedEmployee = employeeRepository.save(employee);
        List<String> listURL = new ArrayList<>();
        boolean hasNewImage = false;

        if (!listImage.isEmpty()) {
            for (MultipartFile multipartFile : listImage) {
                if (!multipartFile.isEmpty()) {
                    try {
                        String imageUrl = cloudService.saveImage(multipartFile);
                        listURL.add(imageUrl);
                        hasNewImage = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (hasNewImage) {
                savedEmployee.setImage(listURL.get(0));
                employeeRepository.save(savedEmployee);
            }
        }

        return savedEmployee;
    }


    public Employee updateEmployeeImage(EmployeeRequest request, List<MultipartFile> listImage) {
        Optional<Employee> employee = employeeRepository.findById(request.getId());
        if (employee.isPresent()) {
            Employee employee1 = employee.get();
            employee1.setEmail(request.getEmail());
            employee1.setName(request.getName());
            employee1.setRoles(request.getRoles());
            employee1.setGender(request.isGender());
            employee1.setPhone(request.getPhone());
            employee1.setCccd(request.getCccd());
            employee1.setBirth_date(request.getBirth_date());
            employee1.setCity(request.getCity());
            employee1.setAddress(request.getAddress());
            employee1.setCountry(request.getCountry());
            employee1.setFulladdress(request.getFulladdress());
            employee1.setStatus(request.getStatus());

            Employee savedEmployee = employeeRepository.save(employee1);
            List<String> listURL = new ArrayList<>();
            boolean hasNewImage = false;

            if (!listImage.isEmpty()) {
                for (MultipartFile multipartFile : listImage) {
                    if (!multipartFile.isEmpty()) {
                        try {
                            String imageUrl = cloudService.saveImage(multipartFile);
                            listURL.add(imageUrl);
                            hasNewImage = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (hasNewImage) {
                    savedEmployee.setImage(listURL.get(0));
                    employeeRepository.save(savedEmployee);
                }
            }
            return savedEmployee;
        }else {
            return null;
        }
    }

    public EmployeeRequest getEmployeeRequetById(UUID id) {
        Optional<EmployeeRequest> response = employeeRepository.getEmployeeFindById(id);
        if (response.isPresent()) {
            return response.get();
        } else {
            return null;
        }
    }


    public void deleteEmployee(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            if (employee.get().isDeleted()) {
                employee.get().setDeleted(false);
            } else {
                employee.get().setDeleted(true);
            }
            employeeRepository.save(employee.get());
        }
    }

    public Optional<Employee> findByIdEmployee(UUID id) {

        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
        if (pageNo > getPageNumber(pageSize)) {
            return null;
        }
        List<Employee> ChiTietSanPhams;
        ChiTietSanPhams = new ArrayList<>();
        Sort sort;
        if (sortDir) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        // findAll method and pass pageable instance
        Page<Employee> page = employeeRepository.findAll(pageable);
        ChiTietSanPhams = page.getContent();
        return ChiTietSanPhams;
    }

    @Override
    public int getPageNumber(int rowcount) {
        Pageable pageable = PageRequest.of(0, rowcount);
        Page<Employee> page = employeeRepository.findAll(pageable);
        int totalPage = page.getTotalPages();
        return totalPage;
    }

    @Override
    public int[] getPanigation(int rowcount, int pageno) {
        Pageable pageable = PageRequest.of(0, rowcount);
        Page<Employee> page = employeeRepository.findAll(pageable); // findAll()
        int totalPage = page.getTotalPages();
        int[] rs;
        if (totalPage <= 1) {
            int[] rs1 = { 1 };
            return rs1;
        } else if (totalPage <= 3) {
            rs = new int[totalPage];
            for (int i = 1; i <= totalPage; i++) {
                rs[i - 1] = i;
            }
            return rs;
        } else {
            rs = new int[3];
            if (pageno <= 2) {
                int[] rs1 = { 1, 2, 3 };
                rs = rs1;
            }
            if (pageno > 2) {
                if (pageno < totalPage - 1) {
                    int[] rs1 = { pageno - 1, pageno, pageno + 1 };
                    rs = rs1;
                }
                if (pageno >= totalPage - 1) {
                    int[] rs1 = { totalPage - 2, totalPage - 1, totalPage };
                    rs = rs1;
                }
            }
            return rs;
        }
    }

    // that write funtion

    public Employee updateInfo(Employee employee) {
        // return employeeRepository.save(employee);
        return null;
    }
}
