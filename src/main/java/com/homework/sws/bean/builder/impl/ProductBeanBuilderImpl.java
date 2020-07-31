package com.homework.sws.bean.builder.impl;

import com.homework.sws.bean.ProductBean;
import com.homework.sws.bean.builder.ProductBeanBuilder;
import com.homework.sws.model.Product;

public class ProductBeanBuilderImpl implements ProductBeanBuilder {

    @Override
    public ProductBean buildFrom(Product product) {
        ProductBean productBean = new ProductBean();
        productBean.setId(product.getId());
        productBean.setTitle(product.getTitle());
        productBean.setCost(product.getCost());
        return productBean;
    }
}
