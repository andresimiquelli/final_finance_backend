package com.andresimiquelli.finalfinance.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.andresimiquelli.finalfinance.controllers.exceptions.FieldMessage;
import com.andresimiquelli.finalfinance.dto.UserPostDTO;

public class UserPostValidator implements ConstraintValidator<UserPostValidation, UserPostDTO> {

	@Override
	public void initialize(UserPostValidation ann) {
	}

	@Override
	public boolean isValid(UserPostDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(!objDto.getEmail().equals(objDto.getEmailConfirmation()))
			list.add(new FieldMessage("emailConfirmation","Email and Email Confirmation must be equal."));
		
		if(!objDto.getPassword().equals(objDto.getPasswordConfirmation()))
			list.add(new FieldMessage("PasswordConfirmation","Password and Password Confirmation must be equal."));

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
