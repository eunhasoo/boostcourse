<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/main-style.css">
    <title>TODO LIST</title>
</head>
<body>
	<p id="worktodo">나의 해야 할 일들</p>
	<div class="container">
	    <a href="todoform" class="todoadd">새로운 TODO 등록</a>
	    <div style="clear:both;"></div>
	    <div class="part">
	        <section>
	            <div class="type"><p>TODO</p></div>
				<c:forEach items="${ todos }" var="todo">
					<c:if test="${ todo.getType().equals(\"TODO\") }">
				   		<div class="card" id="${ todo.getId() }">
						<h3>${ todo.getTitle() }</h3>
						<p>
						등록날짜:${ todo.getRegDate() }, ${ todo.getName() }, 우선순위 ${ todo.getSequence() } <a class="next">→</a>
						</p>
						</div>
					</c:if>
				</c:forEach>
	        </section>
	
	        <section>
	            <div class="type"><p>DOING</p></div>
				<c:forEach items="${ todos }" var="todo">
					<c:if test="${ todo.getType().equals(\"DOING\") }">
				   		<div class="card" id="${ todo.getId() }">
						<h3>${ todo.getTitle() }</h3>
						<p>
						등록날짜:${ todo.getRegDate() }, ${ todo.getName() }, 우선순위 ${ todo.getSequence() } <a class="next">→</a>
						</p>
						</div>
					</c:if>
				</c:forEach>            
	            
	        </section>
	
	        <section>
	            <div class="type"><p>DONE</p></div>
				<c:forEach items="${ todos }" var="todo">
					<c:if test="${ todo.getType().equals(\"DONE\") }">
				   		<div class="card">
						<h3>${ todo.getTitle() }</h3>
						<p>등록날짜:${ todo.getRegDate() }, ${ todo.getName() }, 우선순위 ${ todo.getSequence() }</p>
						</div>
					</c:if>
				</c:forEach>        
			</section>
    	</div> <!-- part 끝 -->
    </div> <!-- container 끝 -->
</body>
<script src="js/main.js"></script>
</html>
