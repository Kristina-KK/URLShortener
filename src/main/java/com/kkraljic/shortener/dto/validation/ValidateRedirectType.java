package com.kkraljic.shortener.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = RedirectTypeValidator.class)
public @interface ValidateRedirectType {

    String message() default "Allowed redirect types are 301 and 302";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // array of allowed values
    int[] value();
}