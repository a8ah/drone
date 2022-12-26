package com.demo.drone.data.common.validators.medication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MedicationNameValidator implements ConstraintValidator<MedicationName, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        Pattern pat = Pattern.compile("^[\\w]+(-?[\\w]+)*");
        Matcher mat = pat.matcher(value);  

        if (mat.matches())
            return true;
        
        return false;
    }

}
