package com.homework.sws.service.impl;

import com.homework.sws.controller.ValidationException;

public abstract class LongTypeIdValidator implements Validator {

    public static final String PARAMETER_IS_BLANK_STRING_FORMAT = "%s parameter is blank";
    public static final String PARAMETER_IS_NOT_VALID_INTEGER_STRING_FORMAT = "%s parameter is not valid integer";

    @Override
    public void validate(String categoryId) {
        if (categoryId == null || categoryId.trim().isEmpty()) {
            throw new ValidationException(String.format(PARAMETER_IS_BLANK_STRING_FORMAT, this.getParameterName()));
        }
        try {
            Long.parseLong(categoryId);
        } catch (NumberFormatException ex) {
            String message = String.format(PARAMETER_IS_NOT_VALID_INTEGER_STRING_FORMAT, this.getParameterName());
            throw new ValidationException(message, ex);
        }
    }

    abstract String getParameterName();
}
