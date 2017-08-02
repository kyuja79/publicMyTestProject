package com.ssocio.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RoutingFilter extends ZuulFilter{
	
	private static Logger logger = LoggerFactory.getLogger(RoutingFilter.class);

	@Override
	public String filterType() {
		return "route";
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
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info("RouteFilter : " + String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		return null;
	}



}
