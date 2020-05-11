package com.eunhasoo.diexam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스가 Spring Config 파일임을 알리는 어노테이션
public class ApplicationConfig {

	/* 
	 * @Bean 어노테이션은 어노테이션이 붙은 메소드들을 자동으로 실행해서
	 * 그 결과로 리턴하는 객체들을 기본적으로 싱글톤으로 관리해준다. Bean의 id 속성값이 메소드 이름이 된다. 
	 */
	
	@Bean
	public Car car(Engine engine) {
		Car car = new Car();
		car.setEngine(engine);
		return car;
	}
	
	@Bean
	public Engine engine() {
		return new Engine();
	}
}
