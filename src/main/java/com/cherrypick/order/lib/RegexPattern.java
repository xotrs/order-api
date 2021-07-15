package com.cherrypick.order.lib;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = RegexPatternValidator.class)
@Documented
public @interface RegexPattern {
    String regex();

    String message() default "is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
