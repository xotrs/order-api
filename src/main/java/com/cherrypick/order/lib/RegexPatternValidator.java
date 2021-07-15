package com.cherrypick.order.lib;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
public class RegexPatternValidator implements ConstraintValidator<RegexPattern, String> {
    private Pattern pattern;

    @Override
    public void initialize(RegexPattern constraintAnnotation) {
        try {
            pattern = Pattern.compile(constraintAnnotation.regex());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("regex syntax invalid", e);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        log.info("test");
        log.info(value);
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
