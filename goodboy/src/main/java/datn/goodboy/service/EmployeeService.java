package datn.goodboy.service;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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

    public ArrayList<Employee> getAll(){
        return (ArrayList<Employee>) employeeRepository.findAll();
    }


    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findByIdEmployee(int id) {
        return employeeRepository.findById(id);
    }
}
