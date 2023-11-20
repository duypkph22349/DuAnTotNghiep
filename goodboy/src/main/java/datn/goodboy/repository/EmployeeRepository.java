package datn.goodboy.repository;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query(value = "SELECT new datn.goodboy.model.response.EmployeeResponse(employee.id ,employee.roles.name ,employee.code,employee.name ,employee.gender ,employee.birth_date ,employee.address ,employee.phone ,employee.email,employee.status ,employee.cccd ,employee.image,employee.password,employee.country,employee.city,employee.fulladdress,employee.deleted) FROM Employee employee")
    Page<EmployeeResponse> getResponsePage(Pageable pageable);

    @Query(value = "SELECT emp.email FROM Employee emp ")
    List<String> getListEmail();

    @Query(value = "SELECT emp FROM Employee emp WHERE emp.email LIKE :email")
    List<Employee> getEmployeesByEmail(@Param("email") String email);

    @Query(value = "SELECT emp FROM Employee emp WHERE emp.email LIKE :email")
    List<Employee> hasEmailis(@Param("email") String email);
}
