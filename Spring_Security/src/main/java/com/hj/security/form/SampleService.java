package com.hj.security.form;

import org.springframework.stereotype.Service;

import com.hj.security.Account.Account;
import com.hj.security.Account.AccountContext;

@Service
public class SampleService {

	public void dashboard() {
		Account account = AccountContext.getAccount();
		System.out.println("==============================");
		System.out.println(account);
		System.out.println("==============================");
	}	
	
}
