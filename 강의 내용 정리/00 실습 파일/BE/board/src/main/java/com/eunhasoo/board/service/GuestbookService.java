package com.eunhasoo.board.service;

import java.util.List;

import com.eunhasoo.board.dto.Guestbook;

public interface GuestbookService {
	/*
	 * 방명록 요구사항: 페이징된 방명록 정보 읽어오기, 전체 건수 구하기, 저장하기, ID로 삭제하기
	 */
	public static final Integer LIMIT = 5;
	public List<Guestbook> getGuestbooks(Integer start);
	public int deleteGuestbook(Long id, String ip);
	public int getCount();
	public Guestbook addGuestbook(Guestbook guestbook, String ip);
}
