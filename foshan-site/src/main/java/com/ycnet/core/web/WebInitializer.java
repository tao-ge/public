package com.ycnet.core.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.FilterRegistration.Dynamic;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.Log4jConfigListener;

@SuppressWarnings("deprecation")
@Order(1)
public class WebInitializer implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		
		servletContext.setInitParameter("spring.profiles.defualt", "dev");
		servletContext.setInitParameter("spring.profiles.active", "dev");
		servletContext.setInitParameter("spring.liveBeansView.mbeanDomain", "dev");
		servletContext.setInitParameter("socket.port", "3456");
		
		servletContext.setInitParameter("log4jConfigLocation", "classpath:/com/ycnet/web/config/log4j.properties");
		servletContext.addListener( new Log4jConfigListener());   
		
		
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("utf-8");
		Dynamic dynamic =servletContext.addFilter("encodingFilter", cef);
		dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE),true, "/*");
		
	}
}
