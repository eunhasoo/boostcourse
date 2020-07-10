package com.eunhasoo.reservation.interceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 웹 브라우저에서 요청이 올 때마다, 요청 URL, 시간, 클라이언트 ip 주소에 대한 로그를 남긴다
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("요청 URL: {}", request.getRequestURI());
		logger.debug("요청 시간: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		logger.debug("클라이언트 IP: {}", request.getRemoteAddr());
		
		return super.preHandle(request, response, handler);
	}

}
