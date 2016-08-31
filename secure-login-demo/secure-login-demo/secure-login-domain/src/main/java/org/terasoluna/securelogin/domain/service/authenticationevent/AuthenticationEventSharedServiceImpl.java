package org.terasoluna.securelogin.domain.service.authenticationevent;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import org.terasoluna.securelogin.domain.model.FailedAuthentication;
import org.terasoluna.securelogin.domain.model.SuccessfulAuthentication;
import org.terasoluna.securelogin.domain.repository.authenticationevent.FailedAuthenticationRepository;
import org.terasoluna.securelogin.domain.repository.authenticationevent.SuccessfulAuthenticationRepository;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;

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
