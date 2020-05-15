package com.eunhasoo.board.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.board.dto.Guestbook;
import com.eunhasoo.board.service.GuestbookService;

@RestController // JSON을 반환하는 Controller
@RequestMapping("/guestbooks") // URL /guestbooks로 들어오는 요청을 전부 받음
public class GuestbookApiController {

	@Autowired
	GuestbookService guestbookService;

	@GetMapping // GET 메소드인 URL /guestbooks 요청을 받음
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<Guestbook> list = guestbookService.getGuestbooks(start);

		int count = guestbookService.getCount();
		int pageCount = count / GuestbookService.LIMIT;
		if (count % GuestbookService.LIMIT > 0)
			pageCount++;

		List<Integer> pageStartList = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}

		// 앞의 코드들은 기존 Controller와 같고 이하가 이전과 다른 코드 부분이다.
		// model에 담아서 View 이름을 전달했던 이전과 달리 map에 담아서 리턴한다.
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageStartList);

		return map;
	}
	
	@PostMapping // POST 메소드인 URL /guestbooks 요청을 받음
	public Guestbook write(@RequestBody Guestbook guestbook, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		// id가 입력된 guestbook이 반환된다.
		Guestbook resultGuestbook = guestbookService.addGuestbook(guestbook, clientIp);
		return resultGuestbook;
	}

	@DeleteMapping("/{id}")
	public Map<String, String> delete(@PathVariable(name="id") Long id, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		int deleteCount = guestbookService.deleteGuestbook(id, clientIp);
		return Collections.singletonMap("success", deleteCount > 0 ? "true" : "false");
	}
}
