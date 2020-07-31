package com.homework.sws.controller;

import com.homework.sws.bean.ProductBean;
import com.homework.sws.service.ProductService;
import com.homework.sws.service.ProductServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private ProductService productService;

    @Override
    public void init() {
        this.productService = new ProductServiceFactory().getDefaultProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Received request: {}", req);
        Long categoryId = Long.parseLong(req.getParameter("categoryId"));
        LOG.debug("Category ID is: {}", categoryId);
        List<ProductBean> products = productService.getForCategory(categoryId);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/content/products.jsp").include(req, resp);
    }
}
