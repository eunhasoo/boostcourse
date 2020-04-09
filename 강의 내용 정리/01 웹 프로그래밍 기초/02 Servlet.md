# 2. Servlet

### Java Web Application
- WAS에 설치되어 동작하는 어플리케이션
- HTML, CSS, 이미지, 자바 클래스 파일, 각종 설정 파일 등이 포함
- 디렉토리 구조

### Servlet이란?
- 자바 웹 어플리케이션의 구성요소 중 동적인 처리를 담당하는 프로그램
- WAS에서 동작하는 JAVA 클래스
- HttpServlet 클래스를 상속받아야 함

### Servlet 등록 방법
#### 1) Servlet 3.0 미만
- web.xml 파일에 직접 입력하여 등록
- url-pattern을 살펴봐서 일치하는 URL을 가진 servlet-name이 있다면 그 name을 가진 Servlet을 찾음
- Servlet Mapping에 실패하면 404 에러가 발생
- web.xml 파일 내용이 바뀔 경우 서버 재시작 필요

#### 2) Servlet 3.0 이상
- web.xml 파일에 등록하지 않고 어노테이션을 이용한다
- @WebServlet 어노테이션으로 URL을 지정하면 자동으로 해당 URL에 부합하는 서블릿을 찾아냄
- 서블릿 내용이 바뀌면 서버가 자동으로 갱신

### Servlet의 생명주기
![](https://cphinf.pstatic.net/mooc/20180124_22/1516782982944xjogH_PNG/1_5_3_ServletLifcycle.PNG)
- WAS는 **서블릿 요청을 받으면** 1. 우선 메모리에 해당 서블릿이 있는지부터 확인
- 2-A. 메모리에 있다면 서블릿의 service() 메소드를 실행
- 2-B. 메모리에 없다면 서블릿 생성후 init() 메소드를 실행한 뒤, service() 메소드를 실행
- WAS가 종료되거나 어플리케이션 내용이 갱신될 경우 3. destory() 메소드 실행
- 요청을 받을 때마다 1~3단계를 반복한다

#### service(request, response) 메소드
- HttpServlet 클래스의 메소드로, 템플릿 메소드 패턴을 사용
- 클라이언트 요청이 GET인 경우 자신이 가진 doGet 메소드 호출
- 클라이언트 요청이 POST인 경우 자신이 가진 doPost 메소드 호출

### Request와 Response
![](https://cphinf.pstatic.net/mooc/20180124_79/15167843899250uB2H_PNG/1_5_4_request_response.PNG)
- WAS는 웹 브라우저로부터 Servlet 요청을 받으면
- 요청시 가지고 있는 정보를 HttpServletRequest 객체를 생성하여 저장
- 응답을 보낼 때 사용할 HttpServletResponse 객체를 생성
- 생성된 두 객체를 Servlet에 전달

#### HttpServletRequest
- HTTP Request 정보를 서블릿에게 전달하기 위해 사용
- 헤더 정보, 파라미터, 쿠키, URI, URL 등의 정보를 읽어 들이는 메소드,\
Body의 Stream을 읽어 들이는 메소드를 소유

#### HttpServletResponse
- 요청을 보낸 클라이언트에게 응답을 보내기 위해 서블릿에 전달되는 객체
- Content Type, 응답 코드, 응답 메시지 등을 전송