package com.hj.security.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class Account {
	
	@Id @GeneratedValue
	private Integer id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String role;

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
}
