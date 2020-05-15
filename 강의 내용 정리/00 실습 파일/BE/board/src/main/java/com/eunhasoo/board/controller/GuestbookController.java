package com.eunhasoo.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
					   ModelMap modelMap) {
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
