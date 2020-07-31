package com.homework.sws.service;

import com.homework.sws.bean.builder.CategoryBeanBuilder;
import com.homework.sws.bean.builder.CategoryBeanBuilderFactory;
import com.homework.sws.repository.CategoryRepository;
import com.homework.sws.repository.CategoryRepositoryFactory;
import com.homework.sws.service.impl.CategoryServiceImpl;

public class CategoryServiceFactory {

    public CategoryService getDefaultCategoryService() {
        CategoryRepository categoryRepository = new CategoryRepositoryFactory().getDefaultCategoryRepository();
        CategoryBeanBuilder categoryBeanBuilder = new CategoryBeanBuilderFactory().getDefaultCategoryBeanBuilder();
        return new CategoryServiceImpl(categoryRepository, categoryBeanBuilder);
    }
}
