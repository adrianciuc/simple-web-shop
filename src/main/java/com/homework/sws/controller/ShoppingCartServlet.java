package com.homework.sws.controller;

import com.homework.sws.bean.ShoppingCartBean;
import com.homework.sws.service.ShoppingCartService;
import com.homework.sws.service.ShoppingCartServiceFactory;
import com.homework.sws.service.impl.AddToCartFileHandlingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShoppingCartServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServlet.class);
    public static final String SHOPPING_CART_SESSION_ATTRIBUTE = "shoppingCart";

    private ShoppingCartService shoppingCartService;

    @Override
    public void init() {
        this.shoppingCartService = new ShoppingCartServiceFactory().getDefaultShoppingCartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        makeSureShoppingCartIsOnSession(req);
        req.getRequestDispatcher("/WEB-INF/views/content/shoppingCart.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productIdParameter = req.getParameter("productId");
        LOG.debug("Received request to add product with id {} to shopping cart", productIdParameter);
        ShoppingCartBean shoppingCartBean = makeSureShoppingCartIsOnSession(req);
        String sessionId = req.getSession().getId();
        this.shoppingCartService.addToShoppingCart(productIdParameter, sessionId, shoppingCartBean);
    }

    private ShoppingCartBean makeSureShoppingCartIsOnSession(HttpServletRequest req) {
        Object cartAttribute = req.getSession().getAttribute(SHOPPING_CART_SESSION_ATTRIBUTE);
        if (cartAttribute == null) {
            cartAttribute = new ShoppingCartBean();
        }
        req.getSession().setAttribute(SHOPPING_CART_SESSION_ATTRIBUTE, cartAttribute);
        return (ShoppingCartBean)cartAttribute;
    }
}
