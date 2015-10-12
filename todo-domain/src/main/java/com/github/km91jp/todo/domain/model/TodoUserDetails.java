package com.github.km91jp.todo.domain.model;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class TodoUserDetails extends User {

	private final Account account;

	public TodoUserDetails(Account account) {
		super(account.getUserId(), account.getPassword(), AuthorityUtils
				.createAuthorityList("ROLE_USER"));
		this.account = account;
	}

	public Account getAccount() {
		return this.account;
	}

	private static final long serialVersionUID = 188434013066256809L;

}
