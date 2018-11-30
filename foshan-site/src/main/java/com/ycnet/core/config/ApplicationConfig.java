package com.ycnet.core.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;




@Configuration
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages={"com.ycnet.*"},excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION,value={Controller.class})})
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Import({DataSourceConfig.class})
public class ApplicationConfig {
	
	
	/**
	 * 配置线程池
	 * @return
	 */
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor()
	{
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(5); //线程池维护线程的最少数量
		threadPoolTaskExecutor.setKeepAliveSeconds(30000); //线程池维护线程所允许的空闲时间
		threadPoolTaskExecutor.setMaxPoolSize(1000); //线程池维护线程的最大数量
		threadPoolTaskExecutor.setQueueCapacity(200); //线程池所使用的缓冲队列
		return threadPoolTaskExecutor;
	}
	
	
	@Bean(name="messageConvert")
	public MappingJackson2HttpMessageConverter messageConverter()
	{
		MappingJackson2HttpMessageConverter  convert = new MappingJackson2HttpMessageConverter();
		convert.setSupportedMediaTypes(new ArrayList<MediaType>(){
			private static final long serialVersionUID = 1L;
		{add(new MediaType("text","plain"));add(new MediaType("application","json"));/*add("text/html;charset=UTF-8");add("text/json;charset=UTF-8");add("application/json;charset=UTF-8");*/}} );
		
		return  new MappingJackson2HttpMessageConverter();
	}
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	
}
