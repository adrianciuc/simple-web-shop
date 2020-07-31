package com.homework.sws.bean.builder;

import com.homework.sws.bean.builder.impl.CategoryBeanBuilderImpl;

public class CategoryBeanBuilderFactory {

    public CategoryBeanBuilder getDefaultCategoryBeanBuilder() {
        return new CategoryBeanBuilderImpl();
    }
}
