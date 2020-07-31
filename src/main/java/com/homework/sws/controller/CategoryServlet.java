package com.homework.sws.controller;

import com.homework.sws.bean.CategoryBean;
import com.homework.sws.service.CategoryService;
import com.homework.sws.service.CategoryServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private CategoryService categoryService;

    @Override
    public void init() {
        this.categoryService = new CategoryServiceFactory().getDefaultCategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Received request: {}", req);
        List<CategoryBean> categoryBeans = this.categoryService.getAllCategories();
        req.setAttribute("categories", categoryBeans);
        req.getRequestDispatcher("/WEB-INF/views/content/categories.jsp").include(req, resp);
    }
}
