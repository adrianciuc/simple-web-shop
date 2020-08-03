package com.homework.sws.service.impl;

import com.homework.sws.bean.ShoppingCartBean;
import com.homework.sws.controller.ValidationException;
import com.homework.sws.service.ProductService;
import com.homework.sws.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
    public static final String PRODUCT_WITH_ID_DOES_NOT_EXISTS = "Product with id %s does not exists";

    private final FileWriter addToCartFileWriter;
    private final ProductService productService;

    public ShoppingCartServiceImpl(FileWriter addToCartFileWriter, ProductService productService) {
        this.addToCartFileWriter = addToCartFileWriter;
        this.productService = productService;
    }

    @Override
    public void addToShoppingCart(String productIdParameter, String sessionId, ShoppingCartBean shoppingCartBean) throws IOException {
        if (productIdParameter == null || productIdParameter.trim().isEmpty()) {
            LOG.debug("productId parameter was not sent in the request");
            throw new ValidationException("productId parameter was not sent");
        } else {
            Long productId = Long.parseLong(productIdParameter);
            if (productService.productWithIdExists(productId)) {
                shoppingCartBean.getProductIds().add(productId);
                synchronized (this) {
                    this.addToCartFileWriter.append(
                            String.format("Product with id %s was added to cart in session %s\n",
                                    productId,
                                    sessionId));
                    this.addToCartFileWriter.flush();
                }
                LOG.debug("Product with id {} was added to cart", productId);
            } else {
                LOG.debug("There is no product with product id {}", productId);
                throw new ValidationException(String.format(PRODUCT_WITH_ID_DOES_NOT_EXISTS, productId));
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.addToCartFileWriter.close();
        } catch (IOException e) {
            LOG.error("The add to cart log file could not be closed.", e);
            throw new AddToCartFileHandlingException(e);
        }
    }
}
