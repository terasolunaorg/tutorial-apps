package org.terasoluna.securelogin.app.common.validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;

public class OldPasswordValidator implements
		ConstraintValidator<ConfirmOldPassword, Object> {

	@Inject
	AccountSharedService accountSharedService;

	@Inject
	PasswordEncoder passwordEncoder;

	private String usernamePropertyName;

	private String oldPasswordPropertyName;

	private String message;

	@Override
	public void initialize(ConfirmOldPassword constraintAnnotation) {
		usernamePropertyName = constraintAnnotation.usernamePropertyName();
		oldPasswordPropertyName = constraintAnnotation.oldPasswordPropertyName();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String username = (String) beanWrapper.getPropertyValue(usernamePropertyName);
		String oldPassword = (String) beanWrapper
				.getPropertyValue(oldPasswordPropertyName);

		Account account = accountSharedService.findOne(username);
		String currentPassword = account.getPassword();

		return checkOldPasswordMacheWithCurrentPassword(oldPassword,
				currentPassword, context);
	}

	private boolean checkOldPasswordMacheWithCurrentPassword(
			String oldPassword, String currentPassword,
			ConstraintValidatorContext context) {
		if (passwordEncoder.matches(oldPassword, currentPassword)) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(oldPasswordPropertyName).addConstraintViolation();
			return false;
		}
	}

}
