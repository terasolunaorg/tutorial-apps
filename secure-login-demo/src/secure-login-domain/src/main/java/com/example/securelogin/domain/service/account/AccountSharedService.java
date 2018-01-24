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
package com.example.securelogin.domain.service.account;

import java.time.LocalDateTime;

import com.example.securelogin.domain.model.Account;
import com.example.securelogin.domain.model.AccountImage;

public interface AccountSharedService {
	Account findOne(String username);

	LocalDateTime getLastLoginDate(String username);
	
	String create(Account account, String imageId);

	boolean exists(String username);
	
	boolean isLocked(String username);

	boolean isInitialPassword(String username);

	boolean isCurrentPasswordExpired(String username);

	boolean updatePassword(String username, String rawPassword);

	void clearPasswordValidationCache(String username);

	AccountImage getImage(String username);
}
