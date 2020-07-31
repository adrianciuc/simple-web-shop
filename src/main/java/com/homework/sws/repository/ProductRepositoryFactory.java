package com.homework.sws.repository;

import com.homework.sws.repository.impl.ProductRepositoryImpl;

public class ProductRepositoryFactory {

    public ProductRepository getDefaultProductRepository() {
        return new ProductRepositoryImpl();
    }
}
