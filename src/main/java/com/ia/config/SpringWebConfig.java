package com.ia.config;
 
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mysql.jdbc.Connection;
 
@EnableWebMvc
@Configuration
/*@ComponentScan({ "com.ia.web" })*/
@ComponentScan(basePackages  = {"com.ia.web"})

public class SpringWebConfig extends WebMvcConfigurerAdapter {
 
	/*ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"Spring-AutoScan.xml"});*/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	 @Bean
    public static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try { 
        	System.out.println("Connectio is lost" + CommonUtility.REQUEST_URL);
        	/*if(CommonUtility.REQUEST_URL.contains("localhost")) {*/
        		/*Local configuration*/
       		   dataSource.setDriverClassName("com.mysql.jdbc.Driver");
               dataSource.setUrl("jdbc:mysql://localhost:3306/project_tracker");
               dataSource.setUsername("root");
               dataSource.setPassword("root");
               
                
        	/*}else{*/ 
        		/*Server configuration*/
        		System.out.println("Server configuration");
                /*dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://74.207.234.174:3306/athena_web?autoReconnect=true");
                dataSource.setUsername("root");
                dataSource.setPassword("Admin123*+@");*/
        		
        		/*dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://pluggtool.c3nvqp9bnujz.us-east-1.rds.amazonaws.com:3306/athena?autoReconnect=true");
                dataSource.setUsername("plugg_tool");
                dataSource.setPassword("PlU!G_2019");*/
        		
//        	}
        
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace(); 
		}
        
        return dataSource;
    }
	 
	 @Bean
	 public static Connection connection() {
		 try {
			 System.out.println("Connection call");
			 	return (Connection) dataSource().getConnection(); 
		 }catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Error in connection ::"+e);
			 return null;
		}		  
	 }
	 
	 @Bean(name = "multipartResolver")
	 public CommonsMultipartResolver multipartResolver() {
	     CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	     //multipartResolver.setMaxUploadSize(490851389);
	     return multipartResolver;
	 }

	 @Override
     public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
     }
	 
/*	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/api/**")
	            .allowedOrigins("http://domain2.com")
	            .allowedMethods("PUT", "DELETE")
	            .allowedHeaders("header1", "header2", "header3")
	            .exposedHeaders("header1", "header2")
	            .allowCredentials(false).maxAge(3600);
	    }*/	
	
	 
	 
}