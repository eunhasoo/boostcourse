<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/todoform-style.css">
    <title>TODO ADD</title>
</head>
<body>

<div class="container">
    <h1 style="text-align: center;">할일 등록</h1>

    <form action="todoadd" method="POST">
        <p>어떤 일인가요?</p>
        <input type="text" name="title" placeholder="24자까지 입력이 가능합니다." required maxlength="24">

        <p>누가 할일인가요?</p>
        <input type="text" name="name" placeholder="홍길동" required style="width: 30%;">

        <p>우선순위를 선택하세요.</p>
        <input type="radio" name="sequence" id="1" value="1" checked>
        <label for="1">1순위</label>
        <input type="radio" name="sequence" id="2" value="2">
        <label for="2">2순위</label>
        <input type="radio" name="sequence" id="3" value="3">
        <label for="3">3순위</label>

        <section>
            <a href="/todo"><button id="back" type="button">< 이전</button></a>
            <button type="submit" style="margin:6px;">제출</button>
            <button type="reset">지우기</button>
        </section>
    </form>
</div>
</body>
</html>