package datn.goodboy.repository;

import datn.goodboy.model.entity.Roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
  @Query(value = "SELECT role FROM Roles role WHERE role.name LIKE 'EMPLOYEE'")
  Optional<Roles> getEmployeeRole();

  @Query(value = "SELECT role FROM Roles role WHERE role.name LIKE 'ADMIN'")
  Optional<Roles> getAdminRole();
}
