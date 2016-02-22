package com.example.session.domain.service.userdetails;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.session.domain.model.Account;

public class AccountDetails extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Account account;

	public AccountDetails(Account account) {
		super(account.getEmail(), account.getEncodedPassword(), AuthorityUtils
				.createAuthorityList("ROLE_USER"));
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
