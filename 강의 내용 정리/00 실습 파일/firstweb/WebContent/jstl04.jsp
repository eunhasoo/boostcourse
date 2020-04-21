<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<String> goodWords = new ArrayList<>();
	goodWords.add("Well done!");
	goodWords.add("Awesome!");
	goodWords.add("Cool!");
	goodWords.add("Fantasic!");
	
	request.setAttribute("goodWords", goodWords);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<c:forEach items="${ goodWords }" var="word">
	<h3>${ word }</h3>
</c:forEach>
</body>
</html>