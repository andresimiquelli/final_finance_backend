package com.andresimiquelli.finalfinance.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.andresimiquelli.finalfinance.controllers.exceptions.FieldMessage;
import com.andresimiquelli.finalfinance.dto.UserPutDTO;

public class UserPutValidator implements ConstraintValidator<UserPutValidation, UserPutDTO> {
	
	@Override
	public void initialize(UserPutValidation ann) {
	}

	@Override
	public boolean isValid(UserPutDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getPassword() != null)
			if(!objDto.getPassword().equals(objDto.getPasswordConfirmation()))
				list.add(new FieldMessage("passwordConfirmation", "Password Confirmation is required."));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
