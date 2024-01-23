package com.kkraljic.shortener.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class RedirectTypeValidator implements ConstraintValidator<ValidateRedirectType, Integer> {

    private Set<Integer> allowedValues;

    @Override
    public void initialize(ValidateRedirectType constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.value()).boxed().collect(toSet());
    }

    @Override
    public boolean isValid(final Integer i, final ConstraintValidatorContext constraintValidatorContext) {
        return i == null || allowedValues.contains(i);
    }
}