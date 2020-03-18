package com.hj.security.Account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AccountService accountService;
	
	@Test
	@WithAnonymousUser // .with(anonymous())를 쓰지 않아도 됀다.
	public void index_anonymous() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	@withUser // .with(user)를 사용하지 않아도 됀다.
	public void index_user() throws Exception {
		// data에 있다고 하는게 아니라 user가 로그인을 한 상태라고 가정을 하는것이다.
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	@withUser
	public void admin_user() throws Exception {
		// data에 있다고 하는게 아니라 user가 로그인을 한 상태라고 가정을 하는것이다.
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "admin@naver.com", roles = "ADMIN")
	public void admin_admin() throws Exception {
		// data에 있다고 하는게 아니라 user가 로그인을 한 상태라고 가정을 하는것이다.
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void login_success() throws Exception {
		String email = "user@naver.com";
		String password = "user";
		
		Account user = this.saveUser(email, password); //user에는 passwordEncoder로 인해 인코딩된 password가 들어있다.
		
		mockMvc.perform(formLogin().user(email).password(password))
			.andExpect(authenticated());
	}
	
	@Test
	@Transactional // test가 끝나면 db변경사항을 rollback해준다.
	public void login_fail() throws Exception {
		String email = "user@naver.com";
		String password = "user";
		
		Account user = this.saveUser(email, password); //user에는 passwordEncoder로 인해 인코딩된 password가 들어있다.
		
		mockMvc.perform(formLogin().user(email).password("12345"))
			.andExpect(unauthenticated());
	}
	
	public Account saveUser(String email, String password) {
		Account account = Account.builder()
				.email(email)
				.password(password)
				.role("USER")
				.build();
		return this.accountService.newAccount(account);
	}
}
