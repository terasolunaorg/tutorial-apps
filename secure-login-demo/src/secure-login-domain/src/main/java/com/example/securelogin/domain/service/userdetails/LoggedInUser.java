/*
 * Copyright (C) 2013-2018 NTT DATA Corporation
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
package com.example.securelogin.domain.service.userdetails;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.example.securelogin.domain.model.Account;

public class LoggedInUser extends User {
	private static final long serialVersionUID = 1L;

	private final Account account;

	private final LocalDateTime lastLoginDate;

	public LoggedInUser(Account account, boolean isLocked,
			LocalDateTime lastLoginDate,
			List<SimpleGrantedAuthority> authorities) {
		super(account.getUsername(), account.getPassword(), true, true, true,
				!isLocked, authorities);
		this.account = account;
		this.lastLoginDate = lastLoginDate;
	}

	public Account getAccount() {
		return account;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

}
