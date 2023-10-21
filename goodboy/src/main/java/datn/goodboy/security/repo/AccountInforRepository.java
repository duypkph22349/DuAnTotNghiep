package datn.goodboy.security.repo;

/**
 * InnerAccountInforRepository
 */
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datn.goodboy.model.entity.Account;

/**
 * UserInforController
 */
public interface AccountInforRepository extends JpaRepository<Account, UUID> {
  @Query("SELECT user from Account user WHERE user.email = :username")
  Optional<Account> getuser(@Param("username") String username);
}