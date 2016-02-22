package org.terasoluna.securelogin.domain.service.passwordreissue;

public interface PasswordReissueFailureSharedService {

	void resetFailure(String username, String token);

}
