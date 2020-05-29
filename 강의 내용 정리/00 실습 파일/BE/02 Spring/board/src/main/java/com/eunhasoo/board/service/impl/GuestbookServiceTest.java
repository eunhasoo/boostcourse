package com.eunhasoo.board.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eunhasoo.board.config.ApplicationConfig;
import com.eunhasoo.board.dto.Guestbook;
import com.eunhasoo.board.service.GuestbookService;

public class GuestbookServiceTest {

	public static void main(String[] args) {
		// ApplicationContext를 불러오고 Service Bean을 가져온다
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		// ServiceImpl 클래스를 사용하는 것이 아님을 다시 상기하자!!!
		GuestbookService guestbookService = applicationContext.getBean(GuestbookService.class);
		
		Guestbook guestbook = new Guestbook();
		guestbook.setName("10빠");
		guestbook.setContent("방명록 10번대 진출 축하드립니다");
		Guestbook addResult = guestbookService.addGuestbook(guestbook, "127.0.0.1");
		System.out.println(addResult);
	}

}
