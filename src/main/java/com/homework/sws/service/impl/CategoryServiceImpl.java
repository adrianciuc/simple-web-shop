package com.homework.sws.service.impl;

import com.homework.sws.bean.CategoryBean;
import com.homework.sws.bean.builder.CategoryBeanBuilder;
import com.homework.sws.repository.CategoryRepository;
import com.homework.sws.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryBeanBuilder categoryBeanBuilder;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryBeanBuilder categoryBeanBuilder) {
        this.categoryRepository = categoryRepository;
        this.categoryBeanBuilder = categoryBeanBuilder;
    }

    public List<CategoryBean> getAllCategories() {
        return this.categoryRepository.getAll()
                .stream()
                .map(this.categoryBeanBuilder::buildFrom)
                .collect(Collectors.toList());
    }
}
