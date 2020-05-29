<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	int n = (int) (Math.random() * 100) + 1;
	request.setAttribute("random", n);
%>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h1>random: ${ random }</h1>
<c:choose>
	<c:when test="${ random <= 40 }">
		random은 40 이하입니다.
	</c:when>
	<c:when test="${ random <= 80 }">
		random은 80 이하입니다.
	</c:when>
	<c:otherwise>
		random은 100 이하입니다.
	</c:otherwise>
</c:choose>
</body>
</html>