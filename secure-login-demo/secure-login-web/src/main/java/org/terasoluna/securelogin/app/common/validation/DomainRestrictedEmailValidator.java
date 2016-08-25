package org.terasoluna.securelogin.app.common.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DomainRestrictedEmailValidator implements
		ConstraintValidator<DomainRestrictedEmail, CharSequence> {

	private Set<String> allowedDomains;

	private boolean allowSubDomain;

	@Override
	public void initialize(DomainRestrictedEmail constraintAnnotation) {
		allowedDomains = new HashSet<String>(Arrays.asList(constraintAnnotation
				.allowedDomains()));
		allowSubDomain = constraintAnnotation.allowSubDomain();
	}

	@Override
	public boolean isValid(CharSequence value,
			ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		for (String domain : allowedDomains) {
			if (value.toString().endsWith("@" + domain)
					|| (allowSubDomain && value.toString().endsWith(
							"." + domain))) {
				return true;
			}
		}
		return false;
	}

}
