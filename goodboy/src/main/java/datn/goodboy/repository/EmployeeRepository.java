package datn.goodboy.repository;

import java.util.UUID;

import datn.goodboy.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
