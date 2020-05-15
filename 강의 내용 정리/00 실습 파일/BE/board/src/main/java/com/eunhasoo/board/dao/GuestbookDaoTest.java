package com.eunhasoo.board.dao;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eunhasoo.board.config.ApplicationConfig;
import com.eunhasoo.board.dto.Guestbook;
import com.eunhasoo.board.dto.Log;

public class GuestbookDaoTest {
	/*
	 * 실제 개발할 때는 JUnit과 같은 단위 테스트 도구를 이용해
	 * 따로 테스트 코드를 작성해서 사용한다
	 */
	
	public static void main(String[] args) {
//		<ApplicationContext 불러오기>
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//		<GuestDao 빈 가져오기>
		GuestbookDao Guestbookdao = applicationContext.getBean(GuestbookDao.class);
//		<Guestbook DTO 생성해서 insert하기>
		Guestbook guestbook = new Guestbook();
		guestbook.setName("이처음");
		guestbook.setContent("Hello world! 반가워요.");
		guestbook.setRegDate(new Date());
		Long guestInsertId = Guestbookdao.insert(guestbook);
		System.out.println("---------inserted id: " + guestInsertId);

//		<LogDao 빈 가져오기>
		LogDao logDao = applicationContext.getBean(LogDao.class);
//		<Log DTO 생성해서 insert하기>
		Log log = new Log();
		log.setIp("127.0.0.1");
		log.setMethod("insert");
		log.setRegDate(new Date());
		Long logInsertId = logDao.insert(log);
		System.out.println("--------inserted id: " + logInsertId);
	}

}
