package com.eunhasoo.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextEx02 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		// Car 객체에 Engine 객체 주입 (DI)
		// 앞으로 Engine의 종류나 Car의 종류가 바뀌더라도 이 코드는 바뀌지 않고, xml 파일만 수정해주면 된다.
		Car car = (Car) applicationContext.getBean("car");
		car.run();
	}

}
