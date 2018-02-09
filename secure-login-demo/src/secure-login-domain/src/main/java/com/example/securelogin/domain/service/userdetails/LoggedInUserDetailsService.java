/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.securelogin.domain.model.Account;
import com.example.securelogin.domain.model.Role;
import com.example.securelogin.domain.service.account.AccountSharedService;

@Service
public class LoggedInUserDetailsService implements UserDetailsService {

	@Inject
	AccountSharedService accountSharedService;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			Account account = accountSharedService.findOne(username);
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			for (Role role : account.getRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"
						+ role.getRoleValue()));
			}
			return new LoggedInUser(account,
					accountSharedService.isLocked(username),
					accountSharedService.getLastLoginDate(username),
					authorities);
		} catch (ResourceNotFoundException e) {
			throw new UsernameNotFoundException("user not found", e);
		}
	}

}
