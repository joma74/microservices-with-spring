package com.eazybytes.microserviceswithspring.loans.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestContextFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(RequestContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) servletRequest;
        var correlationId = httpServletRequest.getHeader(RequestContextHolder.getContext().getCorrelationId());
        LOG.debug("Lonas Service incoming Correlation id : {}", correlationId);
        RequestContextHolder.getContext().setCorrelationId(correlationId);
        //
        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
