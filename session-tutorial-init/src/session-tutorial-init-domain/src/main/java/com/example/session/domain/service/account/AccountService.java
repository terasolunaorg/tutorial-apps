/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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
