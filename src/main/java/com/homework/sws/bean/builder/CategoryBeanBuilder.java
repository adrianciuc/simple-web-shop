package com.homework.sws.bean.builder;

import com.homework.sws.bean.CategoryBean;
import com.homework.sws.model.Category;

public interface CategoryBeanBuilder {

    CategoryBean buildFrom(Category category);
}
