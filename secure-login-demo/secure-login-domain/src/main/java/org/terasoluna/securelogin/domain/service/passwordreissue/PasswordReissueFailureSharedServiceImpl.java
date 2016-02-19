package org.terasoluna.securelogin.domain.service.passwordreissue;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import org.terasoluna.securelogin.domain.model.FailedPasswordReissue;
import org.terasoluna.securelogin.domain.repository.passwordreissue.FailedPasswordReissueRepository;
import org.terasoluna.securelogin.domain.repository.passwordreissue.PasswordReissueInfoRepository;

@Service
@Transactional
public class PasswordReissueFailureSharedServiceImpl implements
		PasswordReissueFailureSharedService {

	@Inject
	ClassicDateFactory dateFactory;
	
	@Inject
	FailedPasswordReissueRepository failedPasswordReissueRepository;

	@Inject
	PasswordReissueInfoRepository passwordReissueInfoRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void resetFailure(String username, String token) {
		FailedPasswordReissue event = new FailedPasswordReissue();
		event.setToken(token);
		event.setAttemptDate(dateFactory.newTimestamp().toLocalDateTime());
		failedPasswordReissueRepository.create(event);

	}

}
