package com.sample.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.sample.gateway.filters.ErrorFilter;
import com.sample.gateway.filters.PostFilter;
import com.sample.gateway.filters.PreFilter;
import com.sample.gateway.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudZuulGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulGateWayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
