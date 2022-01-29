package com.andresimiquelli.finalfinance.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.andresimiquelli.finalfinance.controllers.exceptions.FieldMessage;
import com.andresimiquelli.finalfinance.dto.EntryPostDTO;

public class EntryPostValidator implements ConstraintValidator<EntryPostValidation, EntryPostDTO>{

	@Override
	public void initialize(EntryPostValidation ann) {}
	
	@Override
	public boolean isValid(EntryPostDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getPeriodId() == null) {
			
			if(objDto.getPeriodYear() == null) {
				list.add(new FieldMessage("periodYear","If periodId is not present, periodYear can't be null."));
			}
			
			if(objDto.getPeriodMonth() == null) {
				list.add(new FieldMessage("periodMonth","If periodId is not present, periodMonth can't be null."));
			}
			
			if(objDto.getWalletId() == null) {
				list.add(new FieldMessage("walletId","If periodId is not present, walletId can't be null."));
			}
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
			
		return list.isEmpty();
	}

}
