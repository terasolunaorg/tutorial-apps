/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {
	private String PropertyName;

	private String confirmPropertyName;

	private String message;

	@Override
	public void initialize(Confirm constraintAnnotation) {
		PropertyName = constraintAnnotation.propertyName();
		confirmPropertyName = "confirm" + StringUtils.capitalize(PropertyName);
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Object propertyValue = beanWrapper.getPropertyValue(PropertyName);
		Object confirmPropertyValue = beanWrapper.getPropertyValue(confirmPropertyName);
		boolean matched = ObjectUtils.nullSafeEquals(propertyValue,
				confirmPropertyValue);
		if (matched) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(confirmPropertyName).addConstraintViolation();
			return false;
		}
	}

}
