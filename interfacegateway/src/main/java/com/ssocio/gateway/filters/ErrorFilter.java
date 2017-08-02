package com.ssocio.gateway.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ErrorFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public String filterType() {
		return "error";
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
//		RequestContext ctx = RequestContext.getCurrentContext();
//		HttpServletRequest request = ctx.getRequest();
//		logger.info("RouteFilter : " + String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		logger.info("ErrorFilter: " + String.format("response status is %d", response.getStatus()));
		return null;
	}


}
