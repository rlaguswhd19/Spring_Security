package com.hj.security.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = this.accountRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Not Found" + email));
		
		return User.builder()
				.username(account.getEmail())
				.password(account.getPassword())
				.roles(account.getRole())
				.build();
	}

	public Account createAccount(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		return this.accountRepository.save(account);
	}

}
