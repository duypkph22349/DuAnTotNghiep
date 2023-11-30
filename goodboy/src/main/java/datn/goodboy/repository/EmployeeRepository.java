package datn.goodboy.repository;

import datn.goodboy.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(value = "SELECT emp.email FROM Employee emp ")
    List<String> getListEmail();

    @Query(value = "SELECT emp FROM Employee emp WHERE emp.email LIKE CONCAT('%', :email, '%')")
    List<Employee> getEmployeesByEmail(@Param("email") String email);

    @Query(value = "SELECT emp FROM Employee emp WHERE emp.email LIKE CONCAT('%', :email, '%')")
    List<Employee> hasEmailis(@Param("email") String email);
}
