<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	// JSP 내장변수를 이용해 스코프 변수들을 저장
	pageContext.setAttribute("p1", "page scope value");
	request.setAttribute("r1", "request scope value");
	session.setAttribute("s1", "session scope value");
	application.setAttribute("a1", "application scope value");
	
	String printP1 = "${ pageScope.p1 }";
	String printP1_ = "${ p1 }";
%>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h2>JSP 문법을 이용해서 값 가져오기</h2>
pageContext.getAttribute("p1"): <%= pageContext.getAttribute("p1") %> 
<br/>
<h2>EL을 이용해서 값 가져오기</h2>
<%= printP1 %>: ${ pageScope.p1 } <br/>
r1 = ${ requestScope.r1 } <br/>
s1 = ${ sessionScope.s1 } <br/>
a1 = ${ applicationScope.a1 }<br/>

<h2>※ <%= printP1_ %>: ${ p1 }</h2>
<p>변수가 겹치지 않는 경우 그냥 <%= printP1_ %> 와 같이 출력해도 EL이 인식해서 변수를 찾아낸다.</p>
</body>
</html>