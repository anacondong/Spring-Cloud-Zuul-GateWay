package com.sample.gateway.filters;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

  private Logger logger = Logger.getLogger(getClass());
	
  
  @Override
  public String filterType() {
    return "pre";
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
		logger.info("Inside PreFilter Filter");
		try {
			RequestContext ctx = RequestContext.getCurrentContext();
			HttpServletRequest request = ctx.getRequest();
			String header = request.getHeader("Authorization");
			String body = IOUtils.toString(request.getReader());
			ctx.addZuulRequestHeader("Authorization", header);
			ctx.addZuulRequestHeader("Content-Type", request.getHeader("Content-Type"));
			logger.info("Token is header'" + header + "'");
			logger.info(String.format("method: %s request endpoint to: %s \n with body: %s", 
					request.getMethod(), request.getRequestURL().toString(),body));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
  
  

}