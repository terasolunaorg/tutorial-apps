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
package org.terasoluna.securelogin.domain.service.passwordchange;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.terasoluna.securelogin.domain.service.account.AccountSharedService;

@Service
@Transactional
public class PasswordChangeServiceImpl implements PasswordChangeService {

	@Inject
	AccountSharedService accountSharedService;

	@Override
	public boolean updatePassword(String username, String rawPassword) {
		return accountSharedService.updatePassword(username, rawPassword);
	}

}
