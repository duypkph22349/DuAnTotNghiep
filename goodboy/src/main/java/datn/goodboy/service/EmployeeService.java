package datn.goodboy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

@Service
public class EmployeeService implements PanigationInterface<Employee> {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public ArrayList<Employee> getAllEmployee() {
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

//    public void deleteEmployee(UUID id) {
//
//        employeeRepository.deleteById(id);
//    }

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
