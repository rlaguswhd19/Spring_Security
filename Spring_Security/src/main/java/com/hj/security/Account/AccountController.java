package com.hj.security.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@GetMapping("/{role}/{email}/{password}")
	public Account createAccount(@ModelAttribute Account account) {
		return this.accountService.createAccount(account);
	}
	
}
