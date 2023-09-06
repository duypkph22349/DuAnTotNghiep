package datn.goodboy.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import datn.goodboy.model.entity.Account;

/**
 * AccountRepository
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
