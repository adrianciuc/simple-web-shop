package com.homework.sws.controller;

import com.homework.sws.bean.Parameter;
import com.homework.sws.bean.ShoppingCartBean;
import com.homework.sws.service.ShoppingCartService;
import com.homework.sws.service.ShoppingCartServiceFactory;
import com.homework.sws.service.ValidationService;
import com.homework.sws.service.ValidationServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.homework.sws.bean.Parameter.PRODUCT_ID;

public class ShoppingCartServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServlet.class);
    public static final String SHOPPING_CART_SESSION_ATTRIBUTE = "shoppingCart";

    private ShoppingCartService shoppingCartService;
    private ValidationService validationService;

    @Override
    public void init() {
        this.shoppingCartService = new ShoppingCartServiceFactory().getDefaultShoppingCartService();
        this.validationService = new ValidationServiceFactory().getDefaultValidationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        makeSureShoppingCartIsOnSession(req);
        req.getRequestDispatcher("/WEB-INF/views/content/shoppingCart.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productIdParameter = req.getParameter("productId");
        LOG.info(">>>>> Received request to add product with id {} in shopping cart", productIdParameter);
        validationService.validate(PRODUCT_ID, productIdParameter);
        ShoppingCartBean shoppingCartBean = makeSureShoppingCartIsOnSession(req);
        String sessionId = req.getSession().getId();
        Long productId = Long.parseLong(productIdParameter);
        this.shoppingCartService.addToShoppingCart(productId, sessionId, shoppingCartBean);
        LOG.info("<<<<< Product with id {} was added in shopping cart", productIdParameter);
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
