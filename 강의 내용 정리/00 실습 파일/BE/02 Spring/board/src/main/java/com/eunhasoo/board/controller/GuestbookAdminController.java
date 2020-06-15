package com.eunhasoo.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 로그인 폼을 요청하면 폼 요청을 처리하고
// 로그인을 요청하면 로그인 요청을 처리하는 컨트롤러

@Controller
public class GuestbookAdminController {

	@GetMapping("/loginform")
	public String loginForm() {
		return "loginform";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(required=true) String passwd, HttpSession session,
			RedirectAttributes redirectAttr) {
		if ("abc123".equals(passwd)) { // 암호가 일치하면 세션에 로그인 정보 저장
			session.setAttribute("isAdmin", true);
		} else { // 암호가 일치하지 않으면 로그인 폼으로 리다이렉트
			// RedirectAttributes는 dispatchServlet이 관리하는 FlashMap에 값을 저장하여
			// 리다이렉트할 때 딱 한번만 값을 유지할 목적으로 이용
			redirectAttr.addFlashAttribute("errorMessage", "암호가 틀렸습니다.");
			return "redirect:/loginform";
		}
		
		// 암호가 맞으면 list로 리다이렉트
		return "redirect:/list";
	}
	
	@GetMapping("/logout")
	public String logout(@SessionAttribute("isAdmin") String isAdmin, HttpSession session) {
		if (isAdmin != null && isAdmin.equals("true")) {
			session.removeAttribute("isAdmin");
		}
		return "redirect:/list";
	}
}
