package com.homework.sws.controller;

import com.homework.sws.bean.ProductBean;
import com.homework.sws.service.ProductService;
import com.homework.sws.service.ProductServiceFactory;
import com.homework.sws.service.ValidationService;
import com.homework.sws.service.ValidationServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.homework.sws.bean.Parameter.CATEGORY_ID;

public class ProductServlet extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private ValidationService validationService;
    private ProductService productService;

    @Override
    public void init() {
        this.productService = new ProductServiceFactory().getDefaultProductService();
        this.validationService = new ValidationServiceFactory().getDefaultValidationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        LOG.info(">>>>> Received request to get products of category with id {}", categoryId);
        this.validationService.validate(CATEGORY_ID, categoryId);
        List<ProductBean> products = productService.getForCategory(Long.parseLong(categoryId));
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/content/products.jsp").include(req, resp);
        LOG.info("<<<<< All products of category with id {} returned", categoryId);
    }
}
