package com.demo.drone.data.common.validators.drone;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumModelValidator implements ConstraintValidator<EnumModel, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        List list = Arrays.asList(new String[]{"LIGHTWEIGHT","MIDDLEWEIGHT","CRUISERWEIGHT","HEAVYWEIGHT"});
        var x = list.contains(value);
        return x;
    }

}
