package com.eunhasoo.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.eunhasoo.reservation.interceptor.RequestInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.eunhasoo.reservation.controller" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/resources/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/script/**").addResourceLocations("/WEB-INF/resources/script/").setCachePeriod(31556926);
    }
 
    // default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    	System.out.println("addViewControllers가 호출됩니다.");
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/detail").setViewName("detail");
        registry.addViewController("/review").setViewName("review");
        registry.addViewController("/reserve").setViewName("reserve");
        registry.addViewController("/login").setViewName("bookinglogin");
        registry.addViewController("/myreservation").setViewName("myreservation");
        registry.addViewController("/reviewWrite").setViewName("reviewWrite");
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
	@Bean
	public MultipartResolver multipartResolver() {
		// Commons Multipart Resolver 생성
	    org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 (10MB)
	    return multipartResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
		registry.addInterceptor(new RequestInterceptor());
	}
	
}
