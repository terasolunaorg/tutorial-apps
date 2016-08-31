package com.example.session.domain.service.account;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.session.domain.model.Account;
import com.example.session.domain.repository.account.AccountRepository;

@Service
public class AccountService {

	@Inject
	AccountRepository accountRespository;

	@Inject
	PasswordEncoder passwordEncoder;

	public Account findOne(String email) {
		Account account = accountRespository.findOne(email);

		if (account == null) {
			throw new ResourceNotFoundException("アカウントが存在しません");
		}

		return account;
	}

	public void update(Account account) {
		accountRespository.update(account);
	}

	public void create(Account account, String password) {

		account.setEncodedPassword(passwordEncoder.encode(password));
		accountRespository.create(account);
	}
}
