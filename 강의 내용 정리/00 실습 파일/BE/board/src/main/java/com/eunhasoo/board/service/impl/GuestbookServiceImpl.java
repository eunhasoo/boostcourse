package com.eunhasoo.board.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunhasoo.board.dao.GuestbookDao;
import com.eunhasoo.board.dao.LogDao;
import com.eunhasoo.board.dto.Guestbook;
import com.eunhasoo.board.dto.Log;
import com.eunhasoo.board.service.GuestbookService;

@Service // Service Layer에 해당함을 명시
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired // Autowired 어노테이션으로 인해 Bean이 알아서 주입된다
	GuestbookDao guestbookDao;
	
	@Autowired
	LogDao logDao;

	// start page부터 LIMIT 양만큼 페이징된 Guestbook 목록을 가져옴
	@Override
	@Transactional(readOnly = true) // 읽기만 하는 메소드에 내부적으로 readOnly 형태로 커넥션 사용
	public List<Guestbook> getGuestbooks(Integer start) {
		return guestbookDao.selectAll(start, LIMIT);
	}

	@Override
	@Transactional(readOnly = false) // readOnly 방지
	public int deleteGuestbook(Long id, String ip) {
		// 성공하면 1을 리턴
		int result = guestbookDao.deleteById(id); 
		// 삭제 정보 log를 남김
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegDate(new Date());
		logDao.insert(log);
		return result;
	}

	// 페이징 처리를 위한 전체 guestbook 건수를 가져오는 메소드
	@Override
	@Transactional(readOnly = true)
	public int getCount() {
		return guestbookDao.selectCount();
	}

	@Override
	@Transactional(readOnly = false) // readOnly 방지
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		// 컨트롤러 단에서 받아온 guestbook 객체에 필요한 정보 추가후 insert
		guestbook.setRegDate(new Date());
		Long id = guestbookDao.insert(guestbook); // AUTO-INCREMENT 옵션으로 인해 id가 자동으로 생성되고 그값을 가져옴
		guestbook.setId(id); // log에 해당 부분을 남기기 위해 id를 따로 설정하는 작업을 수행
		// log 정보 남기기
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegDate(new Date());
		logDao.insert(log);
		return guestbook;
	}
	
}
