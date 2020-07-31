package com.homework.sws.bean.builder;

import com.homework.sws.bean.builder.impl.ProductBeanBuilderImpl;

public class ProductBeanBuilderFactory {

    public ProductBeanBuilder getDefaultProductBeanBuilder() {
        return new ProductBeanBuilderImpl();
    }
}