package com.eunhasoo.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextEx01 {

	public static void main(String[] args) {
		// BeanFactory의 경로를 지정해서 컨테이너를 생성
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("---------초기화 완료");
		
		// bean id로 클래스(bean)를 얻어옴
		UserBean userBean = (UserBean) applicationContext.getBean("userBean"); // Object 타입으로 가져오기 때문에 형변환 필요
		userBean.setName("eunha");
		System.out.println(userBean.getName());
		
		UserBean userBean2 = (UserBean) applicationContext.getBean("userBean");
		System.out.println("같은 인스턴스인가요? " + (userBean == userBean2)); // true
	}

}
