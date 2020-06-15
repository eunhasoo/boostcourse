<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
	.container {
	width:500px;
	text-align: center;
	margin: 30px auto;
	}
</style>
</head>
<body>
<div class="container">
	<h1>관리자 로그인</h1>
	<p style="color: red">${errorMessage}</p>

	<form method="post" action="login">
		<span>Password</span>
		<input type="password" name="passwd">
		<input type="submit" value="submit">
	</form>
</div>
</body>
</html>