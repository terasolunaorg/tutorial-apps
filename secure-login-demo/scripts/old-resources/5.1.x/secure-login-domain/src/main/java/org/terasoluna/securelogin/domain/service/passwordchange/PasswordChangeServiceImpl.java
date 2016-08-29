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
