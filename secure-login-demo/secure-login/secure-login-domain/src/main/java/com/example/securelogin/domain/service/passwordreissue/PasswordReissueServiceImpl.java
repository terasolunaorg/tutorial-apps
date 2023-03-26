/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.securelogin.domain.service.passwordreissue;

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
import com.example.securelogin.domain.common.message.MessageKeys;
import com.example.securelogin.domain.model.Account;
import com.example.securelogin.domain.model.PasswordReissueInfo;
import com.example.securelogin.domain.repository.passwordreissue.FailedPasswordReissueRepository;
import com.example.securelogin.domain.repository.passwordreissue.PasswordReissueInfoRepository;
import com.example.securelogin.domain.service.account.AccountSharedService;
import com.example.securelogin.domain.service.mail.PasswordReissueMailSharedService;

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

        String encodeSecret = passwordEncoder.encode(rowSecret);

        if (accountSharedService.exists(username)) {

            Account account = accountSharedService.findOne(username);

            String token = UUID.randomUUID().toString();

            LocalDateTime expiryDate = dateFactory.newTimestamp()
                    .toLocalDateTime().plusSeconds(tokenLifeTimeSeconds);

            PasswordReissueInfo info = new PasswordReissueInfo();
            info.setUsername(username);
            info.setToken(token);
            info.setSecret(encodeSecret);
            info.setExpiryDate(expiryDate);

            passwordReissueInfoRepository.create(info);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .newInstance();
            uriBuilder.scheme(protocol).host(host).port(port).path(contextPath)
                    .pathSegment("reissue").pathSegment("resetpassword")
                    .queryParam("form").queryParam("token", info.getToken());
            String passwordResetUrl = uriBuilder.build().toString();

            mailSharedService.send(account.getEmail(), passwordResetUrl);

        }
        return rowSecret;
    }

    @Override
    @Transactional(readOnly = true)
    public PasswordReissueInfo findOne(String token) {
        PasswordReissueInfo info = passwordReissueInfoRepository.findById(token)
                .orElse(null);

        if (info == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    MessageKeys.E_SL_PR_5002, token));
        }

        if (dateFactory.newTimestamp().toLocalDateTime().isAfter(info
                .getExpiryDate())) {
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
        passwordReissueInfoRepository.deleteByToken(token);

        return accountSharedService.updatePassword(username, rawPassword);

    }

    @Override
    public boolean removeExpired(LocalDateTime date) {
        failedPasswordReissueRepository.deleteExpired(date);
        passwordReissueInfoRepository.deleteExpired(date);
        return true;
    }

}
