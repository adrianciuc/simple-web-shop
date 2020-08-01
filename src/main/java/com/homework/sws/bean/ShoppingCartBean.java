package com.homework.sws.bean;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartBean {

    List<Long> productIds;

    public ShoppingCartBean() {
        this.productIds = new ArrayList<>();
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
