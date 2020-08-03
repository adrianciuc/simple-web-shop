package com.homework.sws.service;

import com.homework.sws.bean.ShoppingCartBean;

import java.io.IOException;

public interface ShoppingCartService {

    void addToShoppingCart(Long productIdParameter, String sessionId, ShoppingCartBean shoppingCartBean) throws IOException;
}
