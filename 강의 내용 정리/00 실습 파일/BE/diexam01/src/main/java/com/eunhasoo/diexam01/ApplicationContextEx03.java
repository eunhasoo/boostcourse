package com.eunhasoo.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextEx03 {

	public static void main(String[] args) {
		// Configuration 어노테이션을 이용한 Config 객체로부터  Bean 객체 가져오기
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//		Car car = (Car) applicationContext.getBean("car");
		Car car = (Car) applicationContext.getBean(Car.class); // Bean id값이 헷갈리면 그냥 클래스를 적어줘도 정상적으로 동작
		car.run();
	}

}
