package com.demo.drone.data.common.validators.drone;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented

@Constraint(validatedBy = DroneSerialValidator.class)

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})

@Retention(RetentionPolicy.RUNTIME)

public @interface DroneSerial {

    String message() default "Allowed only numbers.";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

}
