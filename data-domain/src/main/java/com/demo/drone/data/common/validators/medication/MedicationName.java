package com.demo.drone.data.common.validators.medication;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented

@Constraint(validatedBy = MedicationNameValidator.class)

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})

@Retention(RetentionPolicy.RUNTIME)

public @interface MedicationName {

    String message() default "Allowed only letters, numbers, ‘-‘, ‘_’";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

}
