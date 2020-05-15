package com.eunhasoo.mvcexam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class GoodsController {
	
	@GetMapping("/goods/{id}")
	public String getGoodsById(@PathVariable(name = "id") int id,
			@RequestHeader(value = "User-Agent", defaultValue = "myBrowser") String userAgent,
			HttpServletRequest request, ModelMap model) {
		// URL을 /goods/15로 요청
		String path = request.getServletPath();

		System.out.println("id: " + id); // id: 15
		System.out.println("user_agent: " + userAgent); 
		// user_agent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36
		System.out.println("path: " + path); // path: /goods/15

		model.addAttribute("id", id);
		model.addAttribute("userAgent", userAgent);
		model.addAttribute("path", path);
		return "goodsById";
	}

}
