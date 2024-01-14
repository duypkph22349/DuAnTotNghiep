package datn.goodboy.repository;

import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.request.EmployeeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(value = "SELECT emp.email FROM Employee emp ")
    List<String> getListEmail();

    @Query(value = "SELECT emp FROM Employee emp WHERE emp.email LIKE CONCAT('%', :email, '%')")
    List<Employee> getEmployeesByEmail(@Param("email") String email);

    @Query(value = "SELECT emp FROM Employee emp WHERE emp.email LIKE CONCAT('%', :email, '%')")
    List<Employee> hasEmailis(@Param("email") String email);

    @Query("SELECT new datn.goodboy.model.request.EmployeeRequest(employee.id, employee.roles, employee.image, employee.email, employee.name, employee.gender, employee.phone, employee.birth_date,employee.cccd,employee.password, employee.address, employee.city, employee.country, employee.fulladdress, employee.createdAt, employee.updatedAt, employee.deleted, employee.status, employee.actived) FROM Employee employee WHERE employee.id = :id")
    Optional<EmployeeRequest> getEmployeeFindById(@Param("id") UUID id);
}
