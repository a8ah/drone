package com.demo.drone.data.common.validators.drone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DroneSerialValidator implements ConstraintValidator<DroneSerial, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        Pattern pat = Pattern.compile("^[\\d]+");
        Matcher mat = pat.matcher(value);  

        if (mat.matches())
            return true;
        
        return false;
    }

}
