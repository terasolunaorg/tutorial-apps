package org.terasoluna.securelogin.app.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { FileExtensionValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileExtension {
	String message() default "{org.terasoluna.securelogin.app.common.validation.FileExtension.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] extensions();

	boolean ignoreCase() default true;

	@Target({ ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List {
		FileExtension[] value();
	}
}