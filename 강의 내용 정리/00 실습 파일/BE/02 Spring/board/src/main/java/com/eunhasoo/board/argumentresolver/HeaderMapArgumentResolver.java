package com.eunhasoo.board.argumentresolver;

import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

// 컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값을 바인딩해주는 인터페이스
// 이 클래스는 HeaderInfo 타입과 일치하는 파라미터를 위한 아규먼트 리졸버 클래스이다
public class HeaderMapArgumentResolver implements HandlerMethodArgumentResolver {

	// 이 리졸버가 지정된 파라미터를 해석할 수 있을지 아닐지를 결정
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(HeaderInfo.class);
	}

	// 파라미터 인자가 어떻게 computed (바인딩) 될지 명세
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HeaderInfo headerInfo = new HeaderInfo();
		
		Iterator<String> headerNames = webRequest.getHeaderNames();
		while (headerNames.hasNext()) {
			String headerName = headerNames.next();
			String headerValue = webRequest.getHeader(headerName);
			System.out.println(headerName + ": " + headerValue);
			headerInfo.put(headerName, headerValue);
		}

		return headerInfo;
	}

}
