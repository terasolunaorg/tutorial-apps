package org.terasoluna.securelogin.app.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { StrongPasswordValidator.class })
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface StrongPassword {
	String message() default "{org.terasoluna.securelogin.app.common.validation.StrongPassword.message}";

	Class<?>[] groups() default {};

	String usernamePropertyName();

	String newPasswordPropertyName();

	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		StrongPassword[] value();
	}

	Class<? extends Payload>[] payload() default {};
}
