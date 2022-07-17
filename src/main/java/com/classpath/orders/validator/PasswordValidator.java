package com.classpath.orders.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//rules for password validation
		return true;
	}

	

}
