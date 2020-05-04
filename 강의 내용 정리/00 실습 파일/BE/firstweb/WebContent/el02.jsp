<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("a", 10);
	pageContext.setAttribute("b", true);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h1>EL 태그 계산식</h1>
a = ${ pageScope.a } <br/>
a + 5 = ${ a + 5 } <br/>
a - ${ a } = ${ a - a } <br/>
a * 10 = ${ a * 10 } <br/>
a / 5 = ${ a div 5 } 
<h1>EL 태그 논리연산식</h1>
a = ${ a } <br/>
b = ${ pageScope.b } <br/>
!b = ${ !b } <br/>
a > 5 = ${ a > 5 } <br/>
a < 5 = ${ a < 5 } <br/>
a == 10 = ${ a == 10 } <br/>
a == 25 = ${ a == 25 } <br/>
</body>
</html>