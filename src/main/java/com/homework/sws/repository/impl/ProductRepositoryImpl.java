package com.homework.sws.repository.impl;

import com.homework.sws.model.Product;
import com.homework.sws.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> getForCategory(Long categoryId) {
        List<Product> toReturn = new ArrayList<>();
        if (new Long(1).equals(categoryId)) {
            Product product1 = new Product();
            product1.setId(1L);
            product1.setTitle("Title 1");
            product1.setCost(20.00);
            toReturn.add(product1);
            Product product2 = new Product();
            product2.setId(2L);
            product2.setTitle("Title 2");
            product2.setCost(10.00);
            toReturn.add(product2);
            Product product3 = new Product();
            product3.setId(3L);
            product3.setTitle("Title 3");
            product3.setCost(40.00);
            toReturn.add(product3);
        } else if (new Long(2).equals(categoryId)) {
            Product product1 = new Product();
            product1.setId(4L);
            product1.setTitle("Title 4");
            product1.setCost(50.00);
            toReturn.add(product1);
            Product product2 = new Product();
            product2.setId(5L);
            product2.setTitle("Title 5");
            product2.setCost(30.00);
            toReturn.add(product2);
            Product product3 = new Product();
            product3.setId(6L);
            product3.setTitle("Title 6");
            product3.setCost(40.00);
            toReturn.add(product3);
        } else if (new Long(3).equals(categoryId)) {
            Product product1 = new Product();
            product1.setId(7L);
            product1.setTitle("Title 7");
            product1.setCost(50.00);
            toReturn.add(product1);
            Product product2 = new Product();
            product2.setId(8L);
            product2.setTitle("Title 8");
            product2.setCost(80.00);
            toReturn.add(product2);
            Product product3 = new Product();
            product3.setId(9L);
            product3.setTitle("Title 9");
            product3.setCost(60.00);
            toReturn.add(product3);
        }
        return toReturn;
    }
}
