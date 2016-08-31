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
