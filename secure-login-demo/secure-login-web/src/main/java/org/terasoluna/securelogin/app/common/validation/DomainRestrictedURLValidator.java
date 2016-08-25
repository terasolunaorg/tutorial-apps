package org.terasoluna.securelogin.app.common.validation;

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
