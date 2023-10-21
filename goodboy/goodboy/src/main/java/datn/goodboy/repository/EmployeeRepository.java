package datn.goodboy.repository;

import datn.goodboy.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByOrderByCodeAsc();
    List<Employee> findAllByOrderByCodeDesc();

    Page<Employee> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
