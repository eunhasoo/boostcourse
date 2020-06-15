package com.eunhasoo.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eunhasoo.board.dto.Guestbook;
import com.eunhasoo.board.service.GuestbookService;

@Controller
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping("/list")
	public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
					   ModelMap modelMap, @CookieValue(value="count", required=false) String value,
					   HttpServletResponse response) {
		// 쿠키 1만큼 증가시키기
		try {
			int i = Integer.parseInt(value);
			value = Integer.toString(++i);
		} catch(Exception e) {
			value = "1";
		}
		
		Cookie cookie = new Cookie("count", value); // 쿠키 생성
		cookie.setMaxAge(60 * 60 * 24 * 365); // 1년 동안 쿠키 유지
		cookie.setPath("/"); // 경로 이하 모두 쿠키 지정
		response.addCookie(cookie); // 응답에 쿠키 담기
		
		// start부터 LIMIT만큼 방명록 구하기
		List<Guestbook> guestbooks = guestbookService.getGuestbooks(start); 
		
		// 전체 페이지 수 구하기
		int count = guestbookService.getCount();
		int pageCount = count / GuestbookService.LIMIT; // 한 페이지당 방명록 수로 전체 카운트를 나눔
		if (count % GuestbookService.LIMIT > 0)
			pageCount++; // 만약 10/3을 했으면 페이지는 3이 아니라 4까지 있어야 함 (남은 1개는 4페이지로)
		
		// 페이지 수만큼 start 값을 List로 저장
		// 예를들어 페이지 수가 3이면 0, 5, 10이 되어야 함 (현재 지정된 LIMIT값이 5이므로)
		// 쿼리스트링에는 ?start=0과 같이 들어가게 될 것
		List<Integer> pageStartList = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}
		
		// model에 값 전달하기
		modelMap.addAttribute("guestbooks", guestbooks);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("pageStartList", pageStartList);
		modelMap.addAttribute("cookieCount", value);
		
		return "list";
	}
	
	@PostMapping("/write")
	public String write(@ModelAttribute Guestbook guestbook, HttpServletRequest request) {
		// 클라이언트의 IP 정보를 조회하기 위해 HttpServletRequest 객체를 이용한다.
		System.out.println("Client IP: " + request.getRemoteAddr());
		guestbookService.addGuestbook(guestbook, request.getRemoteAddr());
		return "redirect:list"; // 작업이 성공하면 redirect 필요
	}
}
