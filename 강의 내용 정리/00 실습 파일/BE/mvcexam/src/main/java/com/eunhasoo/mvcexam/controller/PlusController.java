package com.eunhasoo.mvcexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlusController {

	// GET 방식으로 들어오는 URL 요청에 plusform.jsp를 응답
	@GetMapping(path="/plusform")
	public String plusForm() {
		// String으로 뷰 이름을 리턴
		return "plusForm"; // [prefix + view 이름 + suffix]의 뷰파일을 찾음
	}
	
	// 
	@PostMapping(path="/plus")
	public String plus(@RequestParam(name = "value1", required = true) int value1,
			@RequestParam(name = "value2", required = true) int value2, ModelMap modelMap) {
		//  @RequestParam을 이용해 하나하나 Request 파라미터로 받아온 두 값을 더함
		int result = value1 + value2;
		// HttpRequest 객체를 사용하지 않고 Spring이 제공하는 ModelAndView 객체를 이용해 
		// request scope에 값을 넣음
		modelMap.addAttribute("value1", value1);
		modelMap.addAttribute("value2", value2);
		modelMap.addAttribute("result", result);
		// view를 전달함
		return "plusResult";
	}
	
}
