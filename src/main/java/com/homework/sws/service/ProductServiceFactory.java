package com.homework.sws.service;

import com.homework.sws.bean.builder.ProductBeanBuilder;
import com.homework.sws.bean.builder.ProductBeanBuilderFactory;
import com.homework.sws.repository.ProductRepository;
import com.homework.sws.repository.ProductRepositoryFactory;
import com.homework.sws.service.impl.ProductServiceImpl;

public class ProductServiceFactory {

    public ProductService getDefaultProductService() {
        ProductRepository productRepository = new ProductRepositoryFactory().getDefaultProductRepository();
        ProductBeanBuilder productBeanBuilder = new ProductBeanBuilderFactory().getDefaultProductBeanBuilder();
        return new ProductServiceImpl(productRepository, productBeanBuilder);
    }
}
