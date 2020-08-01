package com.homework.sws.repository;

import com.homework.sws.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getForCategory(Long categoryId);
    boolean existsProductWithId(Long productId);
}
