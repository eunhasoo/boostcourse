<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	request.setAttribute("n", 10);
 --%>
<c:set var="n" scope="request" value="15" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
n : ${ n }
<c:if test="${ n == 0 }">
	<h1>n은 zero 입니다.</h1>
</c:if>
<c:if test="${ n != 0 }">
	<h1>n은 ${ n }입니다.</h1>
</c:if>
</body>
</html>