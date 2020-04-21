# 1. Scope

### Scope란?
- Java의 블록과 메소드 내에서 볼 수 있는 변수의 생명주기와 스코프(범위)와 비슷한 개념으로,\
자바 웹 어플리케이션 내의 파라미터와 속성들도 생명주기와 스코프를 가진다

### 스코프 종류
- Application: `웹 어플리케이션`이 시작되고 종료될 때까지 변수가 유지되는 경우
- Session: `웹 브라우저별`로 변수가 관리되는 경우
- Request: `HTTP 요청`을 WAS가 받아서 웹 브라우저에게 `응답`할 때까지 변수가 유지되는 경우
- Page: `페이지 내`에서 지역변수처럼 사용되는 경우

#### 1) Page Scope
- `PageContext` 추상 클래스를 사용
- JSP의 pageContext 내장변수로 사용가능
- forward될 경우 Page scope에 지정된 변수는 사용할 수 없음
- **지역변수**처럼 해당 JSP나 서블릿이 실행되는 동안에만 정보를 유지
- JSP에서 pageScope에 값을 저장한 후 해당 값을 _EL표기법_ 등으로 사용한다

#### 2) Request Scope
- `HTTPServletRequest` 객체를 사용한다
- JSP의 request 내장변수로 사용가능
- request 객체의 setAttribute(), getAttribute() 메소드로 값을 저장하고 꺼내온다
- **forward**_시에 값을 유지_ 하고자 할 때 사용된다

#### 3) Session Scope
- `HttpSession` 인터페이스를 구현한 객체를 사용
- JSP의 session 내장변수로 사용가능
- session 객체의 setAttribute(), getAttribute() 메소드로 값을 저장하고 꺼내온다
- _클라이언트(=웹 브라우저)별로 다른 **상태정보**를 유지, 관리_ 하기 위해 사용된다
- 브라우저의 탭 간에는 세션 정보가 공유되기 때문에 탭끼리 같은 세션 정보를 사용할 수 있음
- 프로그래밍시에 지정한 시간동안 정보가 계속해서 남아있음

#### 4) Application Scope
- `ServletContext` 인터페이스를 구현한 객체를 사용
- JSP의 application 내장변수로 사용가능
- application 객체의 setAttribute(), getAttribute() 메소드로 값을 저장하고 꺼내온다
- 하나의 `서버`에는 여러 개의 웹 어플리케이션 존재 가능
- 하나의 `웹 어플리케이션` 내에는 하나의 어플리케이션 객체가 존재
- 웹 어플리케이션이 **시작되고 종료될 때까지** 변수를 사용할 수 있다
- _**모든 클라이언트가 공통**으로 사용해야할 값이 있을 때_ 사용된다 (브라우저 공통)

# 2. EL
### 표현 언어
- Expression Language의 약자
- 값을 표현하는데 사용되는 스크립트 언어로써 JSP 문법을 보완하는 역할
- 프론트와 백의 경계가 모호한 JSP의 문제를 해결
- 결과만을 출력하기 위한 JSP의 기능적인 측면을 더욱 부각시킬 수 있음

### 제공하는 기능
- JSP의 스코프에 맞는 속성 사용
- 집합 객체에 대한 접근법 제공
- 수치, 관계, 논리 연산자 제공
- 자바 클래스 메소드 호출기능 제공
- 표현언어만의 기본 객체 제공

### 표현 방법
- `${ expr }`
- expr: 표현언어가 정의한 문법에 따라 값을 표현하는 식
- JSP의 스크립트 요소를 제외한 나머지 모든 부분에서 사용가능

### 기본 객체를 사용한 예
- JSP 문법) `<% pageContext.getRequest.getRequestURI() %>`\
EL) `${ pageContext.request.requestURI }`

- JSP 문법) `<% request.getAttribute("name") %>`\
EL) `${ requestScope.name }`

### 데이터 타입
- `boolean` `정수` `실수` `문자열` `null`
- 작은 따옴표는 `\'`와 같이 \ 기호와 함께 사용해야 함

# 3. JSTL
- JSP Standard Tag Library의 약자
- 조건문 처리, 반복문 처리 등을 html 태그 형태로 작성하도록 도와줌

### JSTL Core

![](https://cphinf.pstatic.net/mooc/20180130_226/1517290578353rKRbE_PNG/2_6_2_jstl_.PNG)

- 선언 `<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`\
사용 전에는 반드시 선언 태그안에 코어를 선언해줘야 사용할 수 있다.

#### 변수 지원
```java
<c:set target="${some}" property="propertyName" value="myValue" />
```
- `c:set`는 변수의 이름과 값, 스코프를 지정한다
- some 객체가 **Java Bean**일 경우: `some.setPropertyName(myValue)`와 동일\
some 객체가 **Map**일 경우: `some.put(propertyName, myValue)`와 동일

```java
<c:remove var="foo" scope="request" />
```
- `c:remove`는 지정했던 변수를 제거한다

#### 조건문: if
```java
<c:if test="${조건}">
	// test의 조건이 true이면 바디를 처리
</c:if>
```

#### 조건문: choose
```java
<c:choose>
	<c:when test="${조건1}">
		// 조건1이 true일 때 실행
	</c:when>
	<c:when test="${조건2}">
		// 조건2가 true일 때 실행
	</c:when>
	<c:otherwise>
		// 앞의 조건들이 모두 만족하지 않을 때 실행
	</c:otherwise>
</c:choose>
```

#### 반복문: forEach
```java
<%
	List<String> goodWords = new ArrayList<>();
	goodWords.add("Well done!");
	goodWords.add("Awesome!");
	goodWords.add("Cool!");
	goodWords.add("Fantasic!");
	
	request.setAttribute("goodWords", goodWords);
%>
	
...
	
// 배열 및 Collection 객체에 저장된 요소를 차례대로 처리한다.
// [begin="시작번호"] [end="끝번호"] 를 추가적으로 지정할 수 있다.
<c:forEach items="${ goodWords }" var="word">
	<h3>${ word }</h3>
</c:forEach>
```

#### 리다이렉트: redirect
```java
// 지정한 URL으로 리다이렉트한다
<c:redirect url="redirectTo.jsp">
	// 파라미터 값도 지정할 수 있다
	<c:param name="pName" value="pValue" />
</c:redirect>
```

#### 데이터 출력: out
```java
<c:out value="${ hello }" escapeXml="{true | false}" default="defaultValue" />
// escapeXml의 속성이 true일 경우(=기본값) 문자 변환을 통해 
// <, >, & 등이 태그가 아닌 문자 자체로 출력된다
```