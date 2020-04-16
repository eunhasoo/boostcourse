<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward</title>
</head>
<body>

<%
	Integer loop = (Integer) request.getAttribute("dice");

	for (int i = 0; i < loop; i++) {
%>
	<h3><i>Hello World</i></h3>
<% } %>
</body>
</html>