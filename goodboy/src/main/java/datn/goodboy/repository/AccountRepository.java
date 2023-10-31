package datn.goodboy.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.entity.Employee;
import datn.goodboy.model.response.AccountResponse;

/**
 * AccountRepository
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {
  @Query(value = "SELECT new datn.goodboy.model.response.AccountResponse(acc.id,acc.customer.name ,acc.customer.phone, acc.email, acc.status) FROM Account acc")
  Page<AccountResponse> getPageAccountRepose(Pageable page);

  @Query(value = "SELECT acc.email FROM Account acc ")
  List<String> getListEmail();

  @Query(value = "SELECT acc FROM Account acc WHERE acc.email LIKE :email")
  List<Account> getAccountByEmail(@Param("email") String email);
}
