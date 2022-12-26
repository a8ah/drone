package com.demo.drone.data.common.validators.medication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MedicationCodeValidator implements ConstraintValidator<MedicationCode, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        Pattern pat = Pattern.compile("^[A-Z0-9]+[_A-Z0-9]*");
        Matcher mat = pat.matcher(value);  

        if (mat.matches())
            return true;
        
        return false;
    }

}
