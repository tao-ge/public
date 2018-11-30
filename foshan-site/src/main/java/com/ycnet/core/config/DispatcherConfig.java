package com.ycnet.core.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ycnet.core.URLInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.ycnet",useDefaultFilters=false,includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION,value={Controller.class})})
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Import(WebSocketConfig.class)
public class DispatcherConfig extends WebMvcConfigurationSupport {

	/**
	 * 视图解析处理器
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver= new InternalResourceViewResolver();
		viewResolver.setPrefix("/jsp/one/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * 消息资源处理器
	 * @return
	 */
	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("config.message.messages");
		
		return messageSource;
	}
	
	/**
	 * controller适配器
	 * @return
	 */
	@Bean
	public HandlerAdapter servletHandlerAdapter()
	{
		return new SimpleServletHandlerAdapter();
	}
	
	/**
	 * 本地化拦截器
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor(){
		
		return new LocaleChangeInterceptor();
	}
	
	/**
	 * cookie的本地化资源处理器
	 * @return
	 */
	@Bean(name="localeResolver")
	public CookieLocaleResolver cookieLocaleResolver(){
		
		return new CookieLocaleResolver();
	}
	
	@Bean
	public URLInterceptor urlInterceptor(){
		return new URLInterceptor();
	}
	/**
	 * 自定义拦截器
	 * 
	 * public MyInterceptor initMyInterceptor()
	 * {
	 * 		return new MyInterceptor();
	 * }
	 * 
	 */
	
	/**
	 * 需要显示声明RequestMappingHandlerMapping ，否则不能注册自定义的拦截器
	 */
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping(){
		
		return super.requestMappingHandlerMapping();
	}
	
	/**
	 * 添加拦截器
	 */
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		
		//registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(urlInterceptor());
		super.addInterceptors(registry);
	}
	
	/**
	 * 显示声明HandlerMapping,否则不能注册资源访问处理器
	 */
	@Bean
	public HandlerMapping resourceHandlerMapping()
	{
		return super.resourceHandlerMapping();
	}
	/**
	 * 可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的 内容
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/**/*.html").addResourceLocations("/");
		registry.addResourceHandler("/**/*.html").addResourceLocations("/");
		registry.addResourceHandler("/uploadImage/**").addResourceLocations("/uploadImage/");
		registry.addResourceHandler("/uploadImage/hardwareUpgradFile/**").addResourceLocations("/uploadImage/hardwareUpgradFile/");//硬件升级版本文件"07"
		registry.addResourceHandler("/uploadImage/cableSectionImage/**").addResourceLocations("/uploadImage/cableSectionImage/");//物理光缆上传图片"09"
		registry.addResourceHandler("/uploadImage/wellImg/**").addResourceLocations("/uploadImage/wellImg/");//井上传图片"06"
		registry.addResourceHandler("/uploadImage/monitoring/**").addResourceLocations("/uploadImage/monitoring/");//摄像头上传图片"05"
		registry.addResourceHandler("/uploadImage/facility/**").addResourceLocations("/uploadImage/facility/");//添加设施图片"01"
		registry.addResourceHandler("/uploadImage/terminal/**").addResourceLocations("/uploadImage/terminal/");//端子上传图片"02"
		registry.addResourceHandler("/uploadImage/deviceInspect/**").addResourceLocations("/uploadImage/deviceInspect/");//巡检上传图片"03"
		registry.addResourceHandler("/uploadImage/cableSection/**").addResourceLocations("/uploadImage/cableSection/");//光缆段上传图片"04"
		registry.addResourceHandler("/css/**").addResourceLocations("/static/one/css/").setCachePeriod(31536000);
		registry.addResourceHandler("/js/**").addResourceLocations("/static/one/js/").setCachePeriod(31536000);
		registry.addResourceHandler("/images/**").addResourceLocations("/static/one/images/").setCachePeriod(31536000);
		registry.addResourceHandler("/export/**").addResourceLocations("/export/");
		registry.addResourceHandler("/*.apk").addResourceLocations("/apk/");
		registry.addResourceHandler("/*.txt").addResourceLocations("/apk/");
		registry.addResourceHandler("/data/**/*.db").addResourceLocations("/apk/data/");
	}
	
	/*
	 * 文件上传处理器
	 */
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver()	
	{
		
		return new CommonsMultipartResolver();
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(500000000);
	    return multipartResolver;
	}
	
	@Bean
	@Autowired
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter(final MappingJackson2HttpMessageConverter message){
		RequestMappingHandlerAdapter r = new RequestMappingHandlerAdapter();
		
		ConfigurableWebBindingInitializer config=new ConfigurableWebBindingInitializer();
		config.setConversionService(defaultFormattingConversionService());
		r.setWebBindingInitializer(config);
		
		r.setMessageConverters(new ArrayList<HttpMessageConverter<?>>(){
			private static final long serialVersionUID = 1L;
		         {add(message);}});
		return r;
	}
	
	@Bean(name="defaultFormattingConversionService")
	public DefaultFormattingConversionService defaultFormattingConversionService()
	{
		return new DefaultFormattingConversionService();
		
	}
	
	@Bean
	@Autowired
	public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver(final MappingJackson2HttpMessageConverter message)
	{
		ExceptionHandlerExceptionResolver  e = new ExceptionHandlerExceptionResolver();
		e.setMessageConverters(new ArrayList<HttpMessageConverter<?>>(){
			private static final long serialVersionUID = 1L;
		{add(message) ;}});
		return e;
	}
}
