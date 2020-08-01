package com.homework.sws.repository;

import com.homework.sws.repository.impl.CSVLineMapper;
import com.homework.sws.repository.impl.CSVLineReader;
import com.homework.sws.repository.impl.CategoryRepositoryImpl;

public class CategoryRepositoryFactory {

    public CategoryRepository getDefaultCategoryRepository() {
        CSVLineReader csvLineReader = new CSVLineReader();
        CSVLineMapper csvLineMapper = new CSVLineMapper();
        return new CategoryRepositoryImpl(csvLineReader, csvLineMapper);
    }
}
