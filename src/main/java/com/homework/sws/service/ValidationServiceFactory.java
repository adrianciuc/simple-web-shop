package com.homework.sws.service;

import com.homework.sws.bean.Parameter;
import com.homework.sws.service.impl.CategoryIdValidator;
import com.homework.sws.service.impl.ProductIdValidator;
import com.homework.sws.service.impl.ValidationServiceImpl;
import com.homework.sws.service.impl.Validator;

import java.util.HashMap;

import static com.homework.sws.bean.Parameter.CATEGORY_ID;
import static com.homework.sws.bean.Parameter.PRODUCT_ID;

public class ValidationServiceFactory {

    public ValidationService getDefaultValidationService() {
        HashMap<Parameter, Validator> validators = new HashMap<>();
        validators.put(CATEGORY_ID, new CategoryIdValidator());
        validators.put(PRODUCT_ID, new ProductIdValidator());
        return new ValidationServiceImpl(validators);
    }
}
