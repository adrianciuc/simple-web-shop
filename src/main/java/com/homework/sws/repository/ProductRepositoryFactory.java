package com.homework.sws.repository;

import com.homework.sws.repository.impl.CSVLineMapper;
import com.homework.sws.repository.impl.CSVLineReader;
import com.homework.sws.repository.impl.ProductRepositoryImpl;

public class ProductRepositoryFactory {

    public ProductRepository getDefaultProductRepository() {
        CSVLineReader csvLineReader = new CSVLineReader();
        CSVLineMapper csvLineMapper = new CSVLineMapper();
        return new ProductRepositoryImpl(csvLineReader, csvLineMapper);
    }
}
