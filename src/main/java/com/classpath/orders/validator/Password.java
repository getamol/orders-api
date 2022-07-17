package com.classpath.orders.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Constraint(validatedBy = {PasswordValidator.class })
public @interface Password {
	
	String message() default "invalid email address specified";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


	String regexp() default ".*";

	/**
	 * @return used in combination with {@link #regexp()} in order to specify a regular
	 * expression option
	 */
	Pattern.Flag[] flags() default { };


}
