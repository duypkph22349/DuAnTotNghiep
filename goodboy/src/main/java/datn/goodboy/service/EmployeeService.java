package datn.goodboy.service;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.entity.Roles;
import datn.goodboy.repository.EmployeeRepository;
import datn.goodboy.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Page<Employee> getPage(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    public ArrayList<Employee> getAllEmployee(){
        return (ArrayList<Employee>) employeeRepository.findAll();
    }


    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(UUID id) {

        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findByIdEmployee(UUID id) {

        return employeeRepository.findById(id);
    }
}
