package datn.goodboy.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import datn.goodboy.model.entity.Account;

/**
 * AccountRepository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

  @Query(value = "SELECT acc.email FROM Account acc ")
  List<String> getListEmail();

  @Query(value = "SELECT acc FROM Account acc WHERE acc.email LIKE CONCAT('%', :email, '%')")
  List<Account> getAccountByEmail(@Param("email") String email);

  @Query(value = "SELECT acc FROM Account acc WHERE acc.email LIKE CONCAT('%', :email, '%')")
  Account getAccountByEmails(@Param("email") String email);
  @Query(value = "SELECT acc FROM Account acc WHERE acc.email LIKE CONCAT('%', :email, '%')")
  List<Account> hasEmailis(@Param("email") String email);
  @Query(value = "SELECT acc FROM Account acc WHERE acc.email LIKE :email")
  Account fillAcccoutbyEmail(@Param("email") String email);


}
