/*
 * Copyright (C) 2013-2018 NTT DATA Corporation
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
package org.terasoluna.securelogin.app.common.validation;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class StrongPasswordValidator implements
		ConstraintValidator<StrongPassword, Object> {

	@Inject
	@Named("characteristicPasswordValidator")
	PasswordValidator characteristicPasswordValidator;

	private String usernamePropertyName;

	private String newPasswordPropertyName;

	@Override
	public void initialize(StrongPassword constraintAnnotation) {
		usernamePropertyName = constraintAnnotation.usernamePropertyName();
		newPasswordPropertyName = constraintAnnotation
				.newPasswordPropertyName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String username = (String) beanWrapper
				.getPropertyValue(usernamePropertyName);
		String newPassword = (String) beanWrapper
				.getPropertyValue(newPasswordPropertyName);

		RuleResult result = characteristicPasswordValidator
				.validate(PasswordData.newInstance(newPassword, username, null));

		if (result.isValid()) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();

			for (String message : characteristicPasswordValidator
					.getMessages(result)) {
				context.buildConstraintViolationWithTemplate(message)
						.addPropertyNode(newPasswordPropertyName)
						.addConstraintViolation();
			}
			return false;
		}
	}
}
