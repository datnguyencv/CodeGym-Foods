package com.example.codegymfoods.service.employee.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateOfBirthValidator implements ConstraintValidator<YearOldValid, String> {
    @Override
    public void initialize(YearOldValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDate birthdate = LocalDate.parse(value);
            LocalDate now = LocalDate.now();
            Period period = Period.between(birthdate, now);
            int age = period.getYears();
            return age >= 16 && age < 100;
        } catch (Exception e) {
            return false;
        }
    }
}
