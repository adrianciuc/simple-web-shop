package com.homework.sws.bean.builder.impl;

import com.homework.sws.bean.CategoryBean;
import com.homework.sws.bean.builder.CategoryBeanBuilder;
import com.homework.sws.model.Category;

public class CategoryBeanBuilderImpl implements CategoryBeanBuilder {

    @Override
    public CategoryBean buildFrom(Category category) {
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setId(category.getId());
        categoryBean.setTitle(category.getTitle());
        return categoryBean;
    }
}
