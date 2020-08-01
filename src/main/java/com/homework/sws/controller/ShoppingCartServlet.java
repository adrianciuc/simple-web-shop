package com.homework.sws.controller;

import com.homework.sws.bean.ShoppingCartBean;
import com.homework.sws.service.ProductService;
import com.homework.sws.service.ProductServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

public class ShoppingCartServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServlet.class);

    public static final String SHOPPING_CART = "shoppingCart";
    public static final String PRODUCT_WITH_ID_DOES_NOT_EXISTS = "Product with id %s does not exists";
    public static final String ADD_TO_CART_LOG_FILE_PATH = "add.to.cart.log.file.path";

    private ProductService productService;
    private FileWriter addToCartFileWriter;

    @Override
    public void init() {
        this.productService = new ProductServiceFactory().getDefaultProductService();
        try {
            this.addToCartFileWriter = new FileWriter(System.getProperty(ADD_TO_CART_LOG_FILE_PATH));
        } catch (IOException e) {
            LOG.error("The add to cart log file could not be opened.", e);
            throw new AddToCartFileHandlingException(e);
        }
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
        if (productIdParameter == null || productIdParameter.trim().isEmpty()) {
            LOG.debug("productId parameter was not sent in the request");
            resp.sendError(400, "productId parameter was not sent");
        } else {
            Long productId = Long.parseLong(productIdParameter);
            if (productService.productWithIdExists(productId)) {
                shoppingCartBean.getProductIds().add(productId);
                synchronized (this) {
                    this.addToCartFileWriter.append(
                            String.format("Product with id %s was added to cart in session %s\n",
                                    productId,
                                    req.getSession().getId()));
                    this.addToCartFileWriter.flush();
                }
                LOG.debug("Product with id {} was added to cart", productId);
            } else {
                LOG.debug("There is no product with product id {}", productId);
                resp.sendError(400, String.format(PRODUCT_WITH_ID_DOES_NOT_EXISTS, productId));
            }
        }
    }

    @Override
    public void destroy() {
        try {
            this.addToCartFileWriter.close();
        } catch (IOException e) {
            LOG.error("The add to cart log file could not be closed.", e);
            throw new AddToCartFileHandlingException(e);
        }
    }

    private ShoppingCartBean makeSureShoppingCartIsOnSession(HttpServletRequest req) {
        Object cartAttribute = req.getSession().getAttribute(SHOPPING_CART);
        if (cartAttribute == null) {
            cartAttribute = new ShoppingCartBean();
        }
        req.getSession().setAttribute(SHOPPING_CART, cartAttribute);
        return (ShoppingCartBean)cartAttribute;
    }
}
