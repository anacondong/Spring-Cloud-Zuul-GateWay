package com.sample.gateway.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {
	private Logger logger = Logger.getLogger(getClass());
	
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
		logger.info("Inside Response Filter");
		String responseBody = "";
		RequestContext ctx = RequestContext.getCurrentContext();
		
		try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
			   final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
			   responseBody = new String(responseData);
			   
			   logger.info("Response Body is: "+responseBody);
			   
			   ctx.setResponseBody(responseData);
			} catch (IOException e) {
			   logger.warn("Error reading body",e);
			}
		return null;
	}
	
	

}