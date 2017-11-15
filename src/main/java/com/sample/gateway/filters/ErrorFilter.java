package com.sample.gateway.filters;

import org.apache.log4j.Logger;

import com.netflix.zuul.ZuulFilter;

public class ErrorFilter extends ZuulFilter {
	
	private Logger logger = Logger.getLogger(getClass());
	
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
	logger.info("Inside Route Filter");
    return null;
  }

}