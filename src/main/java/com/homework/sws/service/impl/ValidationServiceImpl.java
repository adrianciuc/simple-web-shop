package com.homework.sws.service.impl;

import com.homework.sws.bean.Parameter;
import com.homework.sws.service.ValidationService;

import java.util.Map;

public class ValidationServiceImpl implements ValidationService {

    public static final String NO_VALIDATOR_STRING_FORMAT = "There is no validator for parameter of type %s. " +
            "The value to validate for this parameter was %s";
    private final Map<Parameter, Validator> validators;

    public ValidationServiceImpl(Map<Parameter, Validator> validators) {
        this.validators = validators;
    }

    @Override
    public void validate(Parameter parameter, String parameterValue) {
        Validator validator = this.validators.get(parameter);
        if (validator == null) {
            String message = String.format(NO_VALIDATOR_STRING_FORMAT, parameter, parameterValue);
            throw new NoValidatorRegistredForParameter(message);
        } else {
            validator.validate(parameterValue);
        }
    }
}
