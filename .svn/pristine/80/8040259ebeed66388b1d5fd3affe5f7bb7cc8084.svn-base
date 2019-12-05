package com.isolver.common.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserWithSalt extends User {
	private static final long serialVersionUID = 1L;

	private String salt;

	private Long id;

	public UserWithSalt(String username, String salt, String password,
			Collection<? extends GrantedAuthority> authorities, Long id) {
		super(username, salt + "," + password, authorities);
		this.salt = salt;
		this.id = id;
	}

	public String getSalt() {
		return salt;
	}

	public Long getId() {
		return id;
	}
}
