package org.terasoluna.securelogin.domain.service.passwordreissue;

import java.time.LocalDateTime;

import org.terasoluna.securelogin.domain.model.PasswordReissueInfo;

public interface PasswordReissueService {

	String createAndSendReissueInfo(String username);

	PasswordReissueInfo findOne(String token);

	boolean resetPassword(String username, String token, String secret,
			String rawPassword);

	boolean removeExpired(LocalDateTime date);
}
