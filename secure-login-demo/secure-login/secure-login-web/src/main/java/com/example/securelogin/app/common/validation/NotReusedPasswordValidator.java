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
package com.example.securelogin.app.common.validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import com.example.securelogin.domain.model.Account;
import com.example.securelogin.domain.model.PasswordHistory;
import com.example.securelogin.domain.model.Role;
import com.example.securelogin.domain.service.account.AccountSharedService;
import com.example.securelogin.domain.service.passwordhistory.PasswordHistorySharedService;

public class NotReusedPasswordValidator implements
                                        ConstraintValidator<NotReusedPassword, Object> {

    @Inject
    ClassicDateFactory dateFactory;

    @Inject
    AccountSharedService accountSharedService;

    @Inject
    PasswordHistorySharedService passwordHistorySharedService;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    @Named("encodedPasswordHistoryValidator")
    PasswordValidator encodedPasswordHistoryValidator;

    @Value("${security.passwordHistoricalCheckingCount}")
    int passwordHistoricalCheckingCount;

    @Value("${security.passwordHistoricalCheckingPeriod}")
    int passwordHistoricalCheckingPeriod;

    private String usernamePropertyName;

    private String newPasswordPropertyName;

    private String message;

    @Override
    public void initialize(NotReusedPassword constraintAnnotation) {
        usernamePropertyName = constraintAnnotation.usernamePropertyName();
        newPasswordPropertyName = constraintAnnotation
                .newPasswordPropertyName();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        String username = (String) beanWrapper.getPropertyValue(
                usernamePropertyName);
        String newPassword = (String) beanWrapper.getPropertyValue(
                newPasswordPropertyName);

        Account account = accountSharedService.findOne(username);
        String currentPassword = account.getPassword();

        boolean result = checkNewPasswordDifferentFromCurrentPassword(
                newPassword, currentPassword, context);
        if (result && account.getRoles().contains(Role.ADMIN)) {
            result = checkHistoricalPassword(username, newPassword, context);
        }

        return result;
    }

    private boolean checkNewPasswordDifferentFromCurrentPassword(
            String newPassword, String currentPassword,
            ConstraintValidatorContext context) {
        if (!passwordEncoder.matches(newPassword, currentPassword)) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(newPasswordPropertyName)
                    .addConstraintViolation();
            return false;
        }
    }

    private boolean checkHistoricalPassword(String username, String newPassword,
            ConstraintValidatorContext context) {
        LocalDateTime useFrom = dateFactory.newTimestamp().toLocalDateTime()
                .minusMinutes(passwordHistoricalCheckingPeriod);
        List<PasswordHistory> historyByTime = passwordHistorySharedService
                .findHistoriesByUseFrom(username, useFrom);
        List<PasswordHistory> historyByCount = passwordHistorySharedService
                .findLatest(username, passwordHistoricalCheckingCount);
        List<PasswordHistory> history = historyByCount.size() > historyByTime
                .size() ? historyByCount : historyByTime;

        List<PasswordData.Reference> historyData = new ArrayList<>();
        for (PasswordHistory h : history) {
            historyData.add(new PasswordData.HistoricalReference(h
                    .getPassword()));
        }

        PasswordData passwordData = new PasswordData(username, newPassword, historyData);
        RuleResult result = encodedPasswordHistoryValidator.validate(
                passwordData);

        if (result.isValid()) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    encodedPasswordHistoryValidator.getMessages(result).get(0))
                    .addPropertyNode(newPasswordPropertyName)
                    .addConstraintViolation();
            return false;
        }
    }
}
