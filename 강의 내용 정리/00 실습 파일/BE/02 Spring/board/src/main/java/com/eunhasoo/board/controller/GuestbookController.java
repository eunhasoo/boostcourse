package com.eunhasoo.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunhasoo.board.dto.Guestbook;
import com.eunhasoo.board.service.GuestbookService;

@Controller
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping("/list")
	public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
					   ModelMap modelMap, @CookieValue(value="count", defaultValue="0", required=false) String value,
					   /* HttpServletRequest request ,*/ HttpServletResponse response) {
		// A. HttpServletRequest 객체를 이용한 쿠키 사용
//		String value = null;
//		boolean find = false;
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if ("count".equals(cookie.getName())) { // 해당하는 쿠키 이름 찾기
//					find = true;
//					value = cookie.getValue(); // 쿠키 정보 가져오기
//					break;
//				}
//			}
//		}
//		
//		if (!find) {
//			value = "1";
//		} else {
//			try {
//				value = Integer.toString(Integer.parseInt(value) + 1);
//			} catch (Exception e) {
//				value = "1";
//			}
//		}
		
		// B. Spring MVC 어노테이션을 이용한 쿠키 사용
		try {
			int i = Integer.parseInt(value);
			value = Integer.toString(++i);
		} catch(Exception e) { // 잘못된 값을 전달받았을 경우를 위해 try catch 블록 사용
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
	
	@GetMapping("/guess")
	public String guess(@RequestParam(name="number", required=false) Integer number, HttpSession session, ModelMap modelMap) {
		String msg = null;
		
		// 파라미터가 없는 경우 (최초 요청) 카운트를 0으로 설정하고
		// 1부터 100 사이의 랜덤한 값을 설정해서 모두 session에 담는다
		if (number == null) {
			session.setAttribute("count", 0);
			session.setAttribute("guessNumber", (int) (Math.random() * 100) + 1);
			msg = "내가 생각한 숫자를 맞춰보세요.";
		} else {
			// number 파라미터가 있을 경우 세션에서 값을 읽어들인 후, number와 세션에 저장된 값을 비교
			// 값을 비교해서 작거나 크다면 카운트를 1 증가 시켜주고, 값이 같다면 세션 정보를 삭제
			int count = (Integer) session.getAttribute("count");
			int guessNumber = (Integer) session.getAttribute("guessNumber");
			
			if (guessNumber == 0) {
				msg = "1부터 100 사이의 숫자만 입력해주세요.";
				session.setAttribute("count", ++count);
			}
			if (guessNumber < number) {
				msg = "내가 생각한 숫자보다 큽니다.";
				session.setAttribute("count", ++count);
			} else if (guessNumber > number) {
				msg = "내가 생각한 숫자보다 작습니다.";
				session.setAttribute("count", ++count);
			} else {
				msg = "정답입니다! " + count + "번째 시도에 맞췄습니다. 숫자는 " + number + "였습니다.";
				session.invalidate();
//				session.removeAttribute("count");
//				session.removeAttribute("guessNumber");
			}
		}
		
		modelMap.addAttribute("msg", msg);
		return "guess";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @SessionAttribute("isAdmin") String isAdmin,
			HttpServletRequest request, RedirectAttributes redirectAttr) {
		if (isAdmin == null || !"true".equals(isAdmin)) { // 로그인 상태가 아닌 경우
			// RedirectAttributes는 dispatchServlet이 관리하는 FlashMap에 값을 저장하여
			// 리다이렉트할 때 딱 한번만 값을 유지할 목적으로 이용
			redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다.");
			return "redirect:/loginform";
		}
		String clientIp = request.getRemoteAddr();
		guestbookService.deleteGuestbook(id, clientIp);
		return "redirect:/list";
	}
}
