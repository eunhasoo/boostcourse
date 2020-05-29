<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GuestBook</title>
	<style>
	h1, p {
	  text-align: center;
	  margin: 2px;
	}
	.container {
	  width: 600px;
	  border: 1px solid #f7f7f7;
	  border-radius: 30px;
 	  box-shadow:  0 1px 2px rgba(0,0,0,0.07), 
                0 2px 4px rgba(0,0,0,0.07), 
                0 4px 8px rgba(0,0,0,0.07), 
                0 8px 16px rgba(0,0,0,0.07),
                0 16px 32px rgba(0,0,0,0.07), 
                0 32px 64px rgba(0,0,0,0.07);
	  margin: 20px auto;
	  padding: 5px;
	}
	.content {
	  background-color: #f7f7f7;
	  font-size: 15px;
	  padding: 5px;
	  border: 1px solid brown;
	  border-radius: 15px 50px 30px 5px;
	  margin: 5px;
	}
	.page {
	  text-align: center;
	}
	.write_box {
	  width: 80%;
	  margin: 13px auto;
	  text-align: center;
	}
	.write_box textarea {
	  font-size: 15px;
	  margin: 9px;
	  width: 70%;
	  border: none;
 	  border-bottom: 2px solid gray;
 	  outline: none;
 	  resize: none;
	}
	.write_box input[type="text"] {
	  margin-top:5px;
	  font-size: 15px;
	  width: 70%;
  	  border: none;
 	  border-bottom: 2px solid gray;
 	  outline: none;
	}
	
	.write_box input[type="submit"] {
	  margin-left: 60%;
	  border: 1px solid white;
	  border-radius: 30px;
	  color: white;
	  background-color: black;
	  padding: 6px;
	  cursor: pointer;
	}
	</style>
</head>
<body>
<div class="container">
	<h1><i>Guestbook</i></h1>
	<p style="text-align: right;">count: ${ count }</p>
	<hr>
		
	<%-- forEach문을 이용해서 방명록 출력 --%>
	<c:forEach items="${ guestbooks }" var="guestbook">
		<div class="content">
			<b>ID: ${ guestbook.id }. Writer: ${ guestbook.name }</b> ${ guestbook.regDate } 
			<br/>
			${ guestbook.content } <br/>
		</div>
	</c:forEach>

	<div class="page">
		<%-- 페이지 링크를 출력--%>
		<c:forEach items="${ pageStartList }" var="pageIndex" varStatus="status">
			<a href="list?start=${ pageIndex }">${ status.index + 1 }</a> &nbsp; &nbsp;
		</c:forEach>
	</div>
	<div class="write_box">
		<%-- 새로운 방명록 입력받는 폼 --%>
		<form method="POST"	action="write">
			<input type="text" placeholder="Your Name..." name="name" required> <br>
			<textarea name="content" rows="4" placeholder="What's up?" required></textarea> <br>
			<input type="submit" value="submit">
		</form>
	</div>
</div>
</body>
</html>