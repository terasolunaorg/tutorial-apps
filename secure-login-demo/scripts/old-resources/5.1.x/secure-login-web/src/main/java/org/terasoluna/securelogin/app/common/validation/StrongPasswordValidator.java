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
