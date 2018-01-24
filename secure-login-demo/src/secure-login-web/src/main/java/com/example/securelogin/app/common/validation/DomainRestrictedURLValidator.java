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
package com.example.securelogin.app.common.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class DomainRestrictedURLValidator implements
		ConstraintValidator<DomainRestrictedURL, CharSequence> {

	private static final Pattern URL_REGEX = Pattern
			.compile("(?i)^(?:[a-z](?:[-a-z0-9\\+\\.])*)" + // protocol
					":(?:\\/\\/([^\\/:]+)" + // auth+host/ip
					"(?::([0-9]*))?" + // port
					"(?:\\/.*)*)$");

	private Set<String> allowedDomains;

	@Override
	public void initialize(DomainRestrictedURL constraintAnnotation) {
		allowedDomains = new HashSet<String>(Arrays.asList(constraintAnnotation
				.allowedDomains()));
	}

	@Override
	public boolean isValid(CharSequence value,
			ConstraintValidatorContext context) {
		Matcher urlMatcher = URL_REGEX.matcher(value);
		if (urlMatcher.matches()) {
			String host = urlMatcher.group(1);
			for (String domain : allowedDomains) {
				if (StringUtils.hasLength(host) && host.endsWith("." + domain)) {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

}
