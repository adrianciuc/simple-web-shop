package com.homework.sws.service;

import com.homework.sws.bean.ProductBean;

import java.util.List;

public interface ProductService {

    List<ProductBean> getForCategory(Long categoryId);
}
