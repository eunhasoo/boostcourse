package com.eunhasoo.mvcexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunhasoo.mvcexam.dto.User;

@Controller
public class UserController {
	
	// @GetMapping(path="/userform")
	@RequestMapping(path="/userform", method=RequestMethod.GET)
	public String userform() {
		return "userform";
	}
	
	@RequestMapping(path="/regist", method=RequestMethod.POST)
	public String regist(@ModelAttribute User user) {
		// DTO를 이용해 Request 파라미터를 하나로 담을 수 있음
		// 메소드 파라미터에 DTO 객체를 선언만 해주면 Spring MVC가 알아서 form에서 넘긴 파라미터 값들을 꺼내서
		// User 객체를 생성해서 이 객체안에 그 값들을 넣어주는 일을 수행한다.
		System.out.println("-------사용자가 입력한 User 정보");
		System.out.println(user);
		return "regist";
	}
	
}
