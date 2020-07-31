package com.homework.sws.repository.impl;

import com.homework.sws.model.Category;
import com.homework.sws.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<Category>();
        Category cat1 = new Category();
        Category cat2 = new Category();
        Category cat3 = new Category();
        cat1.setTitle("Cat 1");
        cat1.setId(1L);
        cat2.setTitle("Cat 2");
        cat2.setId(2L);
        cat3.setTitle("Cat 3");
        cat3.setId(3L);
        categoryList.add(cat1);
        categoryList.add(cat2);
        categoryList.add(cat3);
        return categoryList;
    }
}
