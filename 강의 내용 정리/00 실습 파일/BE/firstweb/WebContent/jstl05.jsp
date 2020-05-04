<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="http://google.com" var="google" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
${ google }
</body>
</html>