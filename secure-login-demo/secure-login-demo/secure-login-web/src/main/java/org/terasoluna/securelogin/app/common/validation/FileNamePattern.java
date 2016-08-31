package org.terasoluna.securelogin.app.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { FileNamePatternValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNamePattern {

	String message() default "{org.terasoluna.securelogin.app.common.validation.FileNamePattern.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String pattern() default "";

	@Target({ ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List {
		FileExtension[] value();
	}

}
