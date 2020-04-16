# 1. JSP
- JavaServer Page의 약자
- ASP에 비해 상대적으로 불편했던 서블릿 개발 방식을 개선하기 위해 등장한 기술
- JSP는 직접 실행되는 것이 아니라, 톰캣으로부터 **Servlet으로 변환**되어 실행

### Life Cycle
- 브라우저가 서버에 JSP에 대한 요청을 전송하면
- **최초로 요청받은 경우**에만 `JSP` 소스 코드가 `Servlet`으로 변환되고 (.java)\
또 그 서블릿 코드를 실행 가능하도록 (.class) 컴파일된다
- 서블릿 클래스를 로딩하고 인스턴스를 생성한다
- 서블릿이 실행되어 요청을 처리하고 응답 정보를 생성한다
- 일반적인 Servlet의 Life Cycle과 동일하게 생각하면 됨 
- 최초 요청시 Init(), 외에는 Service()가 담당, 서버 종료 혹은 서블릿 내용 갱신 후 요청받으면 Destroy()

### 문법

#### 선언문
- 사용법: `<%! 선언문 %>`
- 메소드나 멤버 변수를 선언하기 위해 사용
- 서블릿으로 변환되면서 코드가 클래스 바디 위에 선언되기 때문에 JSP 파일 내 위치는 중요하지 않음

#### 스크립릿
- 사용법: `<% 스크립릿 %>`
- 주로 프로그래밍의 로직을 기술할 때 사용
- 스크립릿 내부에 변수 선언시 Service 메소드 안에 선언됨

#### 표현식
- 사용법: `<%= 표현식 %>`
- 웹 브라우저에 출력할 (응답에 포함하고 싶은) 부분을 나타낼 때 사용

#### 주석
- HTML 형식 `<!-- -->`
- Java 형식 `//` `/* */`
- JSP 형식 `<%-- --%>`

### JSP 내장 객체
- 서블릿으로 변환되면서 jspService 메소드 내부에 미리 선언되어 있는 객체들이 존재
- 이러한 객체들을 jsp에서도 바로 사용할 수 있다

# 2. redirect & forward

### 리다이렉트 redirect
- HTTP 프로토콜로 정해진 규칙
- 전달받은 클라이언트의 요청에 대해 서버가 클라이언트에게 특정 URL로 이동을 요청하는 것

#### 실행 과정

```java
<% response.sendRedirect("redirect02.jsp"); %>
```
- Servlet과 JSP는 리다이렉트를 위해 `HttpServletResponse` 클래스의 sendRedirect() 메소드를 사용한다
- 서버가 클라이언트에게 리다이렉트 코드인 `302`와 함께 헤더 내 Location 값으로 이동할 URL을 보내면,\
클라이언트는 응답 코드 `302`를 확인하고 헤더의 URL로 재요청을 보낸다
- 재요청을 보낼 때 브라우저의 주소창은 **리다이렉트 요청받은 URL**로 바뀐다
- 클라이언트는 (1. 첫 요청), (2. 리다이렉트 요청) = 총 **2번**의 요청을 한다\
즉, 이 요청 객체들은 각각 다른 객체이다. 같은 객체가 아님을 주의!
- <참고> [HTTP 상태 코드](https://developer.mozilla.org/ko/docs/Web/HTTP/Status), [HTTP의 리다이렉션](https://developer.mozilla.org/ko/docs/Web/HTTP/Redirections)

### 포워드 foward
- 한 서블릿에서 작업을 하다가, 작업하던 user 정보(request와 response)를 JSP나 다른 서블릿으로 전달하는 것
- `request.setAttribute("attributeName", value)` 로 값을 집어넣고\
`request.getAttribute("attributeName")` 으로 값을 꺼낸다
- `RequestDispatcher` 객체를 이용해서 forward할 리소스 경로를 지정하고 수행한다
- 리다이렉트 개념과 헷갈릴 수 있지만, 포워드는 요청 받은 객체 **하나**를 가지고 여러 서블릿이 공유하고\
URL 또한 서블릿이 forward 되더라도 **처음 요청한 URL 그대로** 남아있다
- 프로그래밍 로직은 서블릿에서 담당하고, 출력은 jsp에서 담당하도록 서블릿과 jsp를 연동하는 데에 유용하게 쓰이는 기술
![](https://cphinf.pstatic.net/mooc/20180129_201/1517203743283AcQbB_PNG/2_4_3_servlet_jsp.PNG)