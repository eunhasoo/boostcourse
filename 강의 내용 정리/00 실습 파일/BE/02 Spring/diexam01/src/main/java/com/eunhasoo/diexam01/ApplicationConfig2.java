package com.eunhasoo.diexam01;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.eunhasoo.diexam01") 
public class ApplicationConfig2 {
	/* 
	 * ComponentScan 어노테이션은 스캔할 특정 패키지를 지정해서 어노테이션을 검색해 Bean으로 등록한다.
	 * 예) 파라미터로 들어온 com.eunhasoo.diexam01 패키지 이하에서 
	 * @Controller, @Service, @Repository, @Component 어노테이션이 붙어 있는 클래스를 찾아 메모리에 몽땅 올린다.
	 */
}
