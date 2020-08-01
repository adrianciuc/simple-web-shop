package com.homework.sws.service.impl;

import com.homework.sws.bean.ProductBean;
import com.homework.sws.bean.builder.ProductBeanBuilder;
import com.homework.sws.repository.ProductRepository;
import com.homework.sws.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductBeanBuilder productBeanBuilder;

    public ProductServiceImpl(ProductRepository productRepository, ProductBeanBuilder productBeanBuilder) {
        this.productRepository = productRepository;
        this.productBeanBuilder = productBeanBuilder;
    }

    @Override
    public List<ProductBean> getForCategory(Long categoryId) {
        return this.productRepository.getForCategory(categoryId)
                .stream()
                .map(this.productBeanBuilder::buildFrom)
                .collect(Collectors.toList());
    }

    @Override
    public boolean productWithIdExists(Long productId) {
        return productRepository.existsProductWithId(productId);
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public ProductBeanBuilder getProductBeanBuilder() {
        return productBeanBuilder;
    }
}
