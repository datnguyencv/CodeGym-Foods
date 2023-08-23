package com.example.codegymfoods.service.employee.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface YearOldValid {
    String message() default "Bạn nhập ngày sinh chưa chính xác. Tuổi phải lớn hơn 16 và nhỏ hơn 100";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
