package com.eunhasoo.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	
	// Logger 객체 필드 선언
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Controller 메소드가 실행되기 전에 호출
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("----------- preHandle() ----------");
//	    System.out.println(request.getRequestURL());
//		System.out.println("----------------------------------");
		
		logger.debug("{} 를 호출했습니다.", handler.toString());
	    return true;
	}

	// Controller 메소드가 실행된 후에 호출
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("----------- postHandle() ----------");
//		System.out.println(handler.toString() + "가 종료되었습니다.\n" +
//				modelAndView.getViewName() + "을 View로 사용합니다.");
//		System.out.println("----------------------------------");
		
		logger.debug("{}가 종료되었습니다.", handler.toString());
		if (modelAndView != null && modelAndView.hasView()) {
			logger.debug("{}를 view로 사용합니다.", handler.toString(), modelAndView.getViewName());
		}
	}

}
