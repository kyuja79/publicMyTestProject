package com.ssocio.gateway.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PostFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(PostFilter.class);
	
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        logger.info("PostFilter : "+String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//	    HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
//	    logger.info("PostFilter: " + String.format("response's content type is %s", response.getStatus()));
    	HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
    	HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
    	logger.info("REQUEST :: < " + request.getScheme() + " " + request.getLocalAddr() + ":" + request.getLocalPort());
    	logger.info("REQUEST :: < " + request.getMethod() + " " + request.getRequestURI() + " " + request.getProtocol()); logger.info("RESPONSE:: > HTTP:" + response.getStatus());
    	return null;    	
    }

}
