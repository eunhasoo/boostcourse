<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 태그를 사용할 때 prefix를 지정해서 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="firstName" scope="request" value="foo"></c:set>
<c:set var="lastName" scope="request" value="bar"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h1>firstName: ${ firstName }</h1>
<h1>lastName: ${ lastName }</h1>

<c:remove var="firstName" scope="request" /> <!-- 단일 태그로 사용: /로 닫아줌 -->
<h1>remove 후 firstName: ${ firstName }</h1>
<p>[참고] null이어도 exception이 발생하지 않는다!</p>
</body>
</html>