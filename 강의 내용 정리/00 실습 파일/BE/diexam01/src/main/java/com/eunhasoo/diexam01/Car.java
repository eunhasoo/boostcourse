package com.eunhasoo.diexam01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

	@Autowired // Engine 타입의 객체가 생성되었다면 알아서 주입해달라는 어노테이션
	private Engine v8;
	
	public Car() {
		System.out.println("Car 생성자");
	}
	
//	setter는 Autowired로 인해 필요성이 사라짐
	public void setEngine(Engine engine) {
		this.v8 = engine;
	}
	
	public void run() {
		System.out.println("엔진을 이용해 달립니다.");
		v8.exec();
	}
	
//	이러한 과정을 스프링 컨테이너에 위임한다
//	public static void main(String[] args) {
//		Engine engine = new Engine();
//		Car car = new Car();
//		car.setEngine(engine);
//	}
	
}
