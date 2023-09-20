package datn.goodboy.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datn.goodboy.model.entity.Account;
import datn.goodboy.model.response.AccountResponse;

/**
 * AccountRepository
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {
  @Query(value="SELECT new datn.goodboy.model.response.AccountResponse(acc.id,acc.customer.name ,acc.customer.phone, acc.email, acc.status) FROM Account acc")
  Page<AccountResponse> getPageAccountRepose(Pageable page); 
}
