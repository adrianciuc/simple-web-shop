package com.homework.sws.service.impl;

public class CategoryIdValidator extends LongTypeIdValidator {

    @Override
    String getParameterName() {
        return "categoryId";
    }
}
