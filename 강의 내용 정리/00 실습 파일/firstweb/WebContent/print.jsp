<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logic to print</title>
</head>
<body>
<%--

<<EL 표기법 스포일러>>

${ v1 } + ${ v2 } = ${ result } 결과는 이하와 동일하다!!

 --%>
 
<%
	int v1 = (int) request.getAttribute("v1"); // 형변환 잊지 않기!
	int v2 = (int) request.getAttribute("v2");
	int result = (int) request.getAttribute("result");
%>

<h1><%= v1 %> + <%= v2 %> = <%= result %></h1>
<h5>새로고침 해보세요</h5>

</body>
</html>