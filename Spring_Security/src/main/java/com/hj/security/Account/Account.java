package com.hj.security.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Account {
	
	@Id @GeneratedValue
	private Integer id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String role;
	
}
