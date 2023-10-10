package datn.goodboy.repository;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findAllByOrderByCodeAsc();
    List<Employee> findAllByOrderByCodeDesc();


}
