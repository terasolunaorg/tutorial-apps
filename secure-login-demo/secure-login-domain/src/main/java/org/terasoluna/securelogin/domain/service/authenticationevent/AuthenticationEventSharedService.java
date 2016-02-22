package org.terasoluna.securelogin.domain.service.authenticationevent;

import java.util.List;

import org.terasoluna.securelogin.domain.model.FailedAuthentication;
import org.terasoluna.securelogin.domain.model.SuccessfulAuthentication;

public interface AuthenticationEventSharedService {

	List<SuccessfulAuthentication> findLatestSuccessEvents(
			String username, int count);

	List<FailedAuthentication> findLatestFailureEvents(
			String username, int count);

	void authenticationSuccess(String username);

	void authenticationFailure(String username);

	int deleteFailureEventByUsername(String username);
}
