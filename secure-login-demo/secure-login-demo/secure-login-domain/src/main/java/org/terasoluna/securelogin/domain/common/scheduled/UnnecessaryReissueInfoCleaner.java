package org.terasoluna.securelogin.domain.common.scheduled;

import javax.inject.Inject;

import org.terasoluna.gfw.common.date.ClassicDateFactory;
import org.terasoluna.securelogin.domain.service.passwordreissue.PasswordReissueService;

public class UnnecessaryReissueInfoCleaner {

	@Inject
	ClassicDateFactory dateFactory;

	@Inject
	PasswordReissueService passwordReissueService;

	public void cleanup() {
		passwordReissueService.removeExpired(dateFactory.newTimestamp()
				.toLocalDateTime());
	}

}
