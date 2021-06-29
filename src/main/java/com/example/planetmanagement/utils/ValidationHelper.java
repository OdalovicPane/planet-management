package com.example.planetmanagement.utils;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

public class ValidationHelper {

    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> boolean validate(T data, List<String> errorMessage) {
        Set<ConstraintViolation<T>> violations = factory.getValidator().validate(data);
        for (ConstraintViolation<T> violation : violations) {
            errorMessage.add(violation.getMessage());
        }
        return CollectionUtils.isEmpty(violations);
    }

}
