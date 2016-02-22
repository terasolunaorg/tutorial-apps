package org.terasoluna.securelogin.domain.service.account;

import java.time.LocalDateTime;

import org.terasoluna.securelogin.domain.model.Account;

public interface AccountSharedService {
	Account findOne(String username);

	LocalDateTime getLastLoginDate(String username);

	boolean exists(String username);
	
	boolean isLocked(String username);

	boolean isInitialPassword(String username);

	boolean isCurrentPasswordExpired(String username);

	boolean updatePassword(String username, String rawPassword);

	void clearPasswordValidationCache(String username);
}
