package com.hj.security.Account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByEmail(String email);

}
