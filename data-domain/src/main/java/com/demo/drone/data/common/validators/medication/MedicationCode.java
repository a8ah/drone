package com.demo.drone.data.common.validators.medication;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented

@Constraint(validatedBy = MedicationCodeValidator.class)

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})

@Retention(RetentionPolicy.RUNTIME)

public @interface MedicationCode {

    String message() default "Allowed only upper case letters, underscore and numbers";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

}
