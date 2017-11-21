package com.tunguyen.shop.config;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(simpleMappingExceptionResolver());
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver;
		exceptionResolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("AuthenticationException", "login");
		Properties statusCodes = new Properties();
		mappings.setProperty("login", String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
		exceptionResolver.setExceptionMappings(mappings);
		exceptionResolver.setStatusCodes(statusCodes);
		return exceptionResolver;
	}
}
