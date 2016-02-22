package com.example.session.domain.service.userdetails;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.session.domain.model.Account;
import com.example.session.domain.service.account.AccountService;

@Service
public class AccountDetailsService implements UserDetailsService {

	@Inject
	AccountService accountService;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		try {
			Account account = accountService.findOne(email);
			return new AccountDetails(account);
		} catch (ResourceNotFoundException e) {
			throw new UsernameNotFoundException("ユーザが存在しません", e);
		}

	}

}
