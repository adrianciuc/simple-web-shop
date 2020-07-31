package com.homework.sws.bean.builder;

import com.homework.sws.bean.ProductBean;
import com.homework.sws.model.Product;

public interface ProductBeanBuilder {

    ProductBean buildFrom(Product product);
}
