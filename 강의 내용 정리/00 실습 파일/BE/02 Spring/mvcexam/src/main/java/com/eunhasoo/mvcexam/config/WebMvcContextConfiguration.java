package com.eunhasoo.mvcexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * DispatcherServlet이 실행될 때 읽어들이는 설정 클래스
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.eunhasoo.mvcexam.controller" }) // basePackages를 지정해주지 않으면 스캔을 잘 수행하지 못할 수 있음
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	// 리소스 파일들을 다른 디렉토리로 통하도록 지정한다
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    // DefaultServletHandler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    // 특정 URL에 대한 처리를 Controller에 작성하지 않고 매핑할 수 있도록 함
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    	System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("main"); // "/" URL을 main View로 연결
    }
    
    // ViewResolver에게 prefix와 suffix를 지정하도록 알림
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); // 디렉토리에 존재하는
        resolver.setSuffix(".jsp"); // 확장자 파일을 찾음
        return resolver;
    }
}
