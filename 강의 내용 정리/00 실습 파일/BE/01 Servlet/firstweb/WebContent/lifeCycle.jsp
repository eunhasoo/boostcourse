<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LifeCycle</title>
</head>
<body>

<!-- JSP 내부에서 선언문 사용하기 -->
<%!
	public void jspInit() {
		System.out.println("---- jspInit()@ ----");
	}
%>

<%!
	public void jspDestroy() {
		System.out.println("---- jspDestroy() ----");
	}
%>

<%
	System.out.println("---- jspService() ----");
%>

</body>
</html>