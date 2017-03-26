package com.epam.www.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by Farkas on 2017.03.26..
 */
public class ValidationErrorBuilder {
    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}
