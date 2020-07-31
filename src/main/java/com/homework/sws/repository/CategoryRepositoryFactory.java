package com.homework.sws.repository;

import com.homework.sws.repository.impl.CategoryRepositoryImpl;

public class CategoryRepositoryFactory {

    public CategoryRepository getDefaultCategoryRepository() {
        return new CategoryRepositoryImpl();
    }
}
