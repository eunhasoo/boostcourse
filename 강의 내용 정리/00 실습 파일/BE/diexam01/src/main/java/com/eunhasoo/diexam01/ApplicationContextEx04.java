package com.eunhasoo.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextEx04 {

	public static void main(String[] args) {
		// ComponentScan 어노테이션을 이용해서 Bean 객체 가져오기
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig2.class);
		Car car = (Car) applicationContext.getBean(Car.class);
		car.run();
	}

}
