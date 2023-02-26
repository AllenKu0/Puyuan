package com.example.puyuan.my_utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {Password.PasswordValidator.class})
@Repeatable(Password.List.class)
public @interface Password {
    // TODO: 2023/2/17 密碼規則尚未添加
    String regexp() default ".*";
    String message() default "密碼格式不符";
    Class<?>[] groups() default {}; // groups用來指定分組，可以讓校驗採取不同的機制，當前默認未指定任何分組機制，默認每次都要進行校驗。
    Class<? extends Payload>[] payload() default {};
    jakarta.validation.constraints.Pattern.Flag[] flags() default {};
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Password[] value();
    }

    class PasswordValidator implements ConstraintValidator<Password, String> {
        private Password password;
        @Override
        public void initialize(Password constraintAnnotation) {
            password = constraintAnnotation;
        }
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            String regexp = password.regexp();
            return value.matches(regexp);
        }
    }
}
