package com.homework.sws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlerServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        this.processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        this.processError(req, resp);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        if (throwable instanceof ValidationException) {
            LOG.info("Validation error: {}", throwable.getMessage());
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(String.format("{\"error\": \"%s\"}", throwable.getMessage()));
        } else {
            LOG.error("Error: ", throwable);
            req.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req, resp);
        }
    }
}
