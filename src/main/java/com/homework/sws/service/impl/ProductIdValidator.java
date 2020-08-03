package com.homework.sws.service.impl;

public class ProductIdValidator extends LongTypeIdValidator {

    @Override
    String getParameterName() {
        return "productId";
    }
}
