package com.hj.security.form;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

	public void dashboard() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// 누구?인지에 대한 정보
		Object principal = authentication.getPrincipal();
		
		// 사용자의 권한이 들어가 있다.
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		// 인증된 사용자인지 true ? false
		boolean authenticatied = authentication.isAuthenticated();
		
	}	
	
}
