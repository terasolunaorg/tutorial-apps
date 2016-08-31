package org.terasoluna.securelogin.domain.service.unlock;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.authenticationevent.AuthenticationEventSharedService;

@Transactional
@Service
public class UnlockServiceImpl implements UnlockService {

	@Inject
	AccountSharedService accountSharedService;

	@Inject
	AuthenticationEventSharedService authenticationEventSharedService;

	@Override
	public void unlock(String username) {
		authenticationEventSharedService.deleteFailureEventByUsername(username);
	}

}
