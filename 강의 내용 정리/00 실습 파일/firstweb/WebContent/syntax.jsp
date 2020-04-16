<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Syntax</title>
</head>
<body>
<!-- HTML 주석 테스트 -->
<%-- JSP 주석 테스트 --%>
<%
// Java 주석 테스트 1
/*
Java 주석 테스트 2
*/
%>
<% for (int i = 1; i <= 5; i++) { %>
	<H<%= i %>><i>Steven Universe</i></H<%= i %>>
<% } %>
</body>
</html>