package com.ycnet.core.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySources(value={@PropertySource("classpath:com/ycnet/web/config/jdbc.properties")})
@EnableTransactionManagement
public class DataSourceConfig {

	
	@Value("${jdbc.driver}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	
	
	@Bean(destroyMethod="close")
	public DataSource dataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		dataSource.setInitialSize(6); // initial connections
		dataSource.setMaxActive(1000);  // MAX connections
		dataSource.setMaxIdle(300);     //MAX idle connections
		dataSource.setMinIdle(50);     //MIN idle connections
		
		//处理mysql 8小时自动断开连接的问题
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setValidationQuery("select 1");
		dataSource.setTimeBetweenEvictionRunsMillis(20000);
		dataSource.setNumTestsPerEvictionRun(100);
		
		
		return dataSource;
	}
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception  
	{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		//sessionFactory.setConfigLocation(new ClassPathResource("com/ycnet/web/config/SqlMapConfig.xml"));
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/ycnet/mybatis/**/*.xml"));
		sessionFactory.setDataSource(dataSource() );
		return sessionFactory.getObject();
	}
	
	@Bean(name="sqlSession")
	public SqlSessionTemplate sqlSessionTemplate() throws Exception 
	{
		return  new SqlSessionTemplate(sqlSessionFactory());
	}
	
	/**
	 * 事务管理
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager()
	{
		return new DataSourceTransactionManager(dataSource());
	}
}
