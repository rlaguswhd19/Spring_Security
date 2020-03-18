package com.hj.security.Account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;
	
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
	@WithMockUser(username = "admin@naver.com", roles = "admin")
	public void admin_admin() throws Exception {
		// data에 있다고 하는게 아니라 user가 로그인을 한 상태라고 가정을 하는것이다.
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
