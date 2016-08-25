package org.terasoluna.securelogin.app.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Documented
@Constraint(validatedBy = {})
@Target({ FIELD })
@Retention(RUNTIME)
@ReportAsSingleViolation
@Pattern(regexp = "^[\\r\\n\\P{Cntrl}]*$")
public @interface NotContainControlCharsExceptNewlines {
	String message() default "{org.terasoluna.securelogin.app.common.validation.NotContainControlCharsExceptNewlines.message}";

	Class<?>[] groups() default {};

	@Target({ FIELD })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		NotContainControlCharsExceptNewlines[] value();
	}

	Class<? extends Payload>[] payload() default {};
}
