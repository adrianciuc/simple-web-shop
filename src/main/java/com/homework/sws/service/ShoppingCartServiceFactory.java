package com.homework.sws.service;

import com.homework.sws.service.impl.AddToCartFileHandlingException;
import com.homework.sws.service.impl.ShoppingCartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

public class ShoppingCartServiceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServiceFactory.class);
    public static final String ADD_TO_CART_LOG_FILE_PATH = "add.to.cart.log.file.path";

    public ShoppingCartService getDefaultShoppingCartService() {
        try {
            FileWriter addToCartFileWriter = new FileWriter(System.getProperty(ADD_TO_CART_LOG_FILE_PATH), true);
            ProductService productService = new ProductServiceFactory().getDefaultProductService();
            return new ShoppingCartServiceImpl(addToCartFileWriter, productService);
        } catch (IOException e) {
            LOG.error("The add to cart log file could not be opened.", e);
            throw new AddToCartFileHandlingException(e);
        }
    }
}
