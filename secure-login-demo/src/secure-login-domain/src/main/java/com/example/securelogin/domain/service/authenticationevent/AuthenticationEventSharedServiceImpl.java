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
package com.example.securelogin.domain.service.authenticationevent;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import com.example.securelogin.domain.model.FailedAuthentication;
import com.example.securelogin.domain.model.SuccessfulAuthentication;
import com.example.securelogin.domain.repository.authenticationevent.FailedAuthenticationRepository;
import com.example.securelogin.domain.repository.authenticationevent.SuccessfulAuthenticationRepository;
import com.example.securelogin.domain.service.account.AccountSharedService;

@Service
@Transactional
public class AuthenticationEventSharedServiceImpl implements
		AuthenticationEventSharedService {

	@Inject
	ClassicDateFactory dateFactory;

	@Inject
	FailedAuthenticationRepository failedAuthenticationRepository;

	@Inject
	SuccessfulAuthenticationRepository successAuthenticationRepository;

	@Inject
	AccountSharedService accountSharedService;

	@Transactional(readOnly = true)
	@Override
	public List<SuccessfulAuthentication> findLatestSuccessEvents(
			String username, int count) {
		return successAuthenticationRepository.findLatest(username, count);
	}

	@Transactional(readOnly = true)
	@Override
	public List<FailedAuthentication> findLatestFailureEvents(String username,
			int count) {
		return failedAuthenticationRepository.findLatest(username, count);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void authenticationSuccess(String username) {
		SuccessfulAuthentication successEvent = new SuccessfulAuthentication();
		successEvent.setUsername(username);
		successEvent.setAuthenticationTimestamp(dateFactory.newTimestamp()
				.toLocalDateTime());

		successAuthenticationRepository.create(successEvent);
		deleteFailureEventByUsername(username);
	}

	@Override
	public void authenticationFailure(String username) {
		if (accountSharedService.exists(username)) {
			FailedAuthentication failureEvents = new FailedAuthentication();
			failureEvents.setUsername(username);
			failureEvents.setAuthenticationTimestamp(dateFactory.newTimestamp()
					.toLocalDateTime());

			failedAuthenticationRepository.create(failureEvents);
		}
	}

	@Override
	public int deleteFailureEventByUsername(String username) {
		return failedAuthenticationRepository.deleteByUsername(username);
	}

}
