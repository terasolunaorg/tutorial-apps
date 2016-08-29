package org.terasoluna.securelogin.domain.service.passwordreissue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.passay.CharacterRule;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.securelogin.domain.common.message.MessageKeys;
import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.model.PasswordReissueInfo;
import org.terasoluna.securelogin.domain.repository.passwordreissue.FailedPasswordReissueRepository;
import org.terasoluna.securelogin.domain.repository.passwordreissue.PasswordReissueInfoRepository;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.mail.PasswordReissueMailSharedService;

@Service
@Transactional
public class PasswordReissueServiceImpl implements PasswordReissueService {

	@Inject
	ClassicDateFactory dateFactory;

	@Inject
	PasswordReissueFailureSharedService passwordReissueFailureSharedService;

	@Inject
	PasswordReissueMailSharedService mailSharedService;

	@Inject
	PasswordReissueInfoRepository passwordReissueInfoRepository;

	@Inject
	FailedPasswordReissueRepository failedPasswordReissueRepository;

	@Inject
	AccountSharedService accountSharedService;

	@Inject
	PasswordEncoder passwordEncoder;

	@Inject
	PasswordGenerator passwordGenerator;

	@Resource(name = "passwordGenerationRules")
	List<CharacterRule> passwordGenerationRules;

	@Value("${security.tokenLifeTimeSeconds}")
	int tokenLifeTimeSeconds;

	@Value("${app.host}")
	String host;

	@Value("${app.port}")
	String port;

	@Value("${app.contextPath}")
	String contextPath;

	@Value("${app.passwordReissueProtocol}")
	String protocol;

	@Value("${security.tokenValidityThreshold}")
	int tokenValidityThreshold;

	@Override
	public String createAndSendReissueInfo(String username) {

		String rowSecret = passwordGenerator.generatePassword(10,
				passwordGenerationRules);

		if (!accountSharedService.exists(username)) {
			return rowSecret;
		}

		Account account = accountSharedService.findOne(username);

		String token = UUID.randomUUID().toString();

		LocalDateTime expiryDate = dateFactory.newTimestamp().toLocalDateTime()
				.plusSeconds(tokenLifeTimeSeconds);

		PasswordReissueInfo info = new PasswordReissueInfo();
		info.setUsername(username);
		info.setToken(token);
		info.setSecret(passwordEncoder.encode(rowSecret));
		info.setExpiryDate(expiryDate);

		passwordReissueInfoRepository.create(info);

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		uriBuilder.scheme(protocol).host(host).port(port).path(contextPath)
				.pathSegment("reissue").pathSegment("resetpassword")
				.queryParam("form").queryParam("token", info.getToken());
		String passwordResetUrl = uriBuilder.build().toString();

		mailSharedService.send(account.getEmail(), passwordResetUrl);

		return rowSecret;

	}

	@Override
	@Transactional(readOnly = true)
	public PasswordReissueInfo findOne(String token) {
		PasswordReissueInfo info = passwordReissueInfoRepository.findOne(token);

		if (info == null) {
			throw new ResourceNotFoundException(ResultMessages.error().add(
					MessageKeys.E_SL_PR_5002, token));
		}

		if (dateFactory.newTimestamp().toLocalDateTime()
				.isAfter(info.getExpiryDate())) {
			throw new BusinessException(ResultMessages.error().add(
					MessageKeys.E_SL_PR_2001));
		}

		int count = failedPasswordReissueRepository.countByToken(token);
		if (count >= tokenValidityThreshold) {
			throw new BusinessException(ResultMessages.error().add(
					MessageKeys.E_SL_PR_5004));
		}

		return info;
	}

	@Override
	public boolean resetPassword(String username, String token, String secret,
			String rawPassword) {
		PasswordReissueInfo info = this.findOne(token);
		if (!passwordEncoder.matches(secret, info.getSecret())) {
			passwordReissueFailureSharedService.resetFailure(username, token);
			throw new BusinessException(ResultMessages.error().add(
					MessageKeys.E_SL_PR_5003));
		}
		failedPasswordReissueRepository.deleteByToken(token);
		passwordReissueInfoRepository.delete(token);

		return accountSharedService.updatePassword(username, rawPassword);

	}

	@Override
	public boolean removeExpired(LocalDateTime date) {
		failedPasswordReissueRepository.deleteExpired(date);
		passwordReissueInfoRepository.deleteExpired(date);
		return true;
	}

}
