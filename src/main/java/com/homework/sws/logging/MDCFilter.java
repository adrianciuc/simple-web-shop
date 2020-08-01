package com.homework.sws.logging;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MDCFilter implements Filter {

    public static final String SESSION_ID = "sessionId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String sessionId = ((HttpServletRequest)request).getSession().getId();
            MDC.put(SESSION_ID, sessionId);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(SESSION_ID);
        }
    }

    @Override
    public void destroy() {
    }
}
