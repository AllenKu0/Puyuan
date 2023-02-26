package com.example.puyuan.my_utils.validator;


import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {Phone.PhoneValidator.class}) //指定約束處理器，也就是指定由哪個類負責實際的驗證處理
@Repeatable(Phone.List.class)
public @interface Phone {
    String regexp() default "^09[0-9]{8}$";
    String message() default "手機格式不符";
    Class<?>[] groups() default {}; // groups用來指定分組，可以讓校驗採取不同的機制，當前默認未指定任何分組機制，默認每次都要進行校驗。
    Class<? extends Payload>[] payload() default {};
    jakarta.validation.constraints.Pattern.Flag[] flags() default {};
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Phone[] value();
    }

    class PhoneValidator implements ConstraintValidator<Phone, String> {
        private Phone phone; // 註解物件

        @Override
        public void initialize(Phone constraintAnnotation) {
            // 初始化【Phone】物件
            phone = constraintAnnotation;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            // 獲取【phone】物件的手機格式驗證表達式
            String regexp = phone.regexp();
            return value.matches(regexp);
        }
    }
}
