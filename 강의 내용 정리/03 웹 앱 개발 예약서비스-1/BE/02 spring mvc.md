# Spring MVC

### MVC
- Model-View-Controller의 약자
- `Model` : 뷰가 렌더링하는 데 필요한 데이터
- `View` : 웹 어플리케이션에서 실제로 보이는 부분으로, 모델을 사용해 렌더링
- `Controller` : 사용자의 액션에 응답하는 컴포넌트로, 모델을 업데이트하고 다른 액션을 수행

### MVC Model 1 Architecture

![](https://cphinf.pstatic.net/mooc/20180219_180/1519003368125BcfqV_PNG/1.png)

- 브라우저의 요청을 `JSP`가 전달받음
- Java Bean과 연결되어 비지니스 로직과 데이터베이스 소통
- JSP에 HTML, Javascript 등 여러 코드가 섞여있기 때문에 유지보수에 어려움을 겪음

### MVC Model 2 Architecture

![](https://cphinf.pstatic.net/mooc/20180219_65/1519003382079lUcI5_PNG/2.png)

- 브라우저의 요청을 `Servlet`이 받아서 JSP(View)를 통해 화면에 출력
- 서블릿이 컨트롤러의 역할, JSP는 뷰의 역할만 수행
- **로직과 뷰를 분리**할 수 있게 됨

### MVC Model 2의 발전형태

![](https://cphinf.pstatic.net/mooc/20180219_149/15190034013354diDI_PNG/3.png)

- 클라이언트가 보내는 모든 요청을 `프론트 컨트롤러`(=서블릿)가 받음
- 서블릿은 받은 요청들을 컨트롤러(핸들러 클래스)에 위임함으로써 한 클래스에서 모든 URL 처리 가능
- 뷰에 Model을 전달함으로써 알맞은 데이터를 출력
- <참고> [Model 1, 2 MVC 아키텍쳐](https://www.javatpoint.com/model-1-and-model-2-mvc-architecture), [Spring Web MVC framework 레퍼런스](https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html)
- <참고> [Spring MVC 아키텍쳐 개요](https://terasolunaorg.github.io/guideline/1.0.1.RELEASE/en/Overview/SpringMVCOverview.html)
- Spring Web Module은 Model 2 MVC 패턴을 지원
![](https://cphinf.pstatic.net/mooc/20180219_73/1519003417760TqmnB_PNG/4.png)

### Spring MVC 기본 동작 흐름

![](https://cphinf.pstatic.net/mooc/20180219_116/1519003779294ejdEx_PNG/1.png)

### DispatcherServlet
- 프론트 컨트롤러 _Front Controller_
- 클라이언트의 모든 요청을 받아서 이를 처리할 핸들러에게 넘기고,\
핸들러가 처리한 결과를 받아 사용자에게 응답 결과를 전달
- 여러 컴토넌트들을 이용해 작업을 처리
- <참고> [DispatcherServlet 동작원리](https://jess-m.tistory.com/15)

### 내부 동작 흐름 요약
![](https://cphinf.pstatic.net/mooc/20180219_281/1519003870301bOehw_PNG/2.png)

#### 1) 요청 선처리 작업

![](https://cphinf.pstatic.net/mooc/20180219_91/1519003885824QT31y_PNG/3.png)

- Spring MVC가 지원하는 _지역화_ 란 같은 사이트에 사용자별로 다른 언어를 지원하는 것을 의미하는데,\
즉 개별 클라이언트의 언어 셋팅을 따라서 `LocaleResolver`가 지역정보를 결정하고, 그 값에 따른 언어를 처리
- `RequestContextHolder`는 Spring 객체 안에서 요청과 응답 객체를 관리할 수 있게 해줌
- `FlashMapManager`는 Spring 3에서 추가된 기능으로, 리다이렉트로 값을 간편하게 전달할 수 있도록 지원
- 사용자가 파일업로드를 할 경우 파일 정보를 읽어들일 특수한 Request 객체가 필요한데,\
이 때 멀티 파트라는 요청이 들어오면 `MultipartResolver`가 지정해서 요청 처리 핸들러를 결정, 처리

#### 2) 요청 전달
![](https://cphinf.pstatic.net/mooc/20180219_20/1519003954110F9wyd_PNG/4.png)
- `HandlerMapping`으로 `HandlerExecutionChain`이 결정되는데, \
그것이 발견되지 않으면 에러코드를 전달하고 요청을 끝내고, 발견되면 `HandlerAdapter`를 결정
- 이후 HandlerAdapter가 발견되지 않으면 ServletException을 발생시킨뒤 요청을 끝내고, 발견되었으면 요청을 처리

#### 3) 요청 처리
![](https://cphinf.pstatic.net/mooc/20180219_167/1519004040926yL8eC_PNG/5.png)
- `ModelAndView`는 컨트롤러의 처리 결과를 보여줄 view와 view에서 사용할 값을 전달
- `RequestToViewNameTranslator`는 적절한 뷰가 없을 때 자동으로 뷰 이름을 생성해주는 전략 오브젝트

#### 4) 예외처리
![](https://cphinf.pstatic.net/mooc/20180219_26/1519004078279fGdRP_PNG/6.png)
- `HandlerExceptionResolver`는 기본적으로 DispatcherServlet에 등록된 컴포넌트로,\
예외가 throw 됐을 때 어떤 핸들러를 실행할 것인지에 대한 정보를 제공

#### 5) 뷰 렌더링 과정
![](https://cphinf.pstatic.net/mooc/20180219_66/1519004113425TanBR_PNG/7.png)
- 뷰가 String이라면 `ViewResolver`로 view를 찾아보고, \
존재하지 않으면 예외를 발생하고 존재한다면 View 구현체로 렌더링한뒤 요청 처리를 재개한다

#### 6) 요청 처리 종료
![](https://cphinf.pstatic.net/mooc/20180219_296/1519004150778ofOPV_PNG/8.png)
- HandlerExecutionChain이 존재하면 afterCompletion 메소드를 실행한뒤 RequestHandlerEvent를 발생시키고 요청을 처리\
존재하지 않으면 바로 RequestHandlerEvent를 발생시킨후 요청이 처리됨


### DispatcherServlet을 Front Controller로 설정하기

#### 1) web.xml 파일으로 설정
- xml Spring 설정을 읽어들이도록 설정 방법과\
Java Config 설정을 읽어들이도록 설정 방법이 존재
- url-pattern이 `/`으로 지정됨에 따라 모든 요청을 받아들이게 됨
- CSS나 이미지 파일들도 한꺼번에 들어오기 때문에 이런 파일들은 다른 디렉토리로 통하도록 필터링 필요

#### 2) WebApplicationInitializer 인터페이스를 구현해서 설정
- Spring이 제공하는 방법으로써 SpringServletContainerInitializer가 WebApplicationInitializer 구현체를 찾아서\
인스턴스를 만들고, 해당 인스턴스의 onStartup 메소드를 호출해 동기화

### Spring MVC 설정 어노테이션
#### @Configuration
- 자바 Config 파일임을 명시하는 역할

#### @EnableWebMvc
- 웹에 필요한 `Bean`들을 자동으로 설정
- xml 설정의 `<mvc:annotaion-driven/>`과 동일
- 기본 설정 이외에 따로 설정이 필요할 경우에는\
WebMvcConfigurerAdapter를 상속받도록 클래스를 작성해서 필요한 메소드를 오버라이딩

#### @ComponentScan
- `@Controller`, @Service, @Repository, @Component가 붙은 클래스를 찾기 위해서 사용

### Controller 클래스 작성
- `@Controller` 어노테이션을 클래스 선언 위에 붙임
- 맵핑을 위해 `@RequestMapping` 어노테이션을 클래스나 메소드 위에 사용

#### @RequestMapping
- HTTP `요청`과 이를 다루기 위한 Controller의 `메소드`를 연결하는 어노테이션
```java
@RequestMapping("/users", method=RequestMethod.POST)
// Spring 4.3 버전 이상부터는 메소드 방식에 따른 어노테이션 사용가능
@PostMapping("/users")
```
- HTTP 특정 헤더와 연결 `headers`, Parameter와 연결 `params`,\
Content-Type 헤더와 연결 `consumes`, Accept 헤더와 연결 `produces` 등 속성 지원

### Spring MVC가 지원하는 메소드 인수 어노테이션
- `@RequestParam` : 매핑된 메소드의 Argument에 붙임. http parameter의 name값과 매핑.
- `@RequestHeader` : 요청 정보의 헤더 정보를 읽어들임
- `@RequestBody` : HTTP request 바디정보를 객체로 매핑
- `@RequestPart` : "multipart / form-data" 요청의 일부를 메소드 인수와 연관시키는 데 사용
- `@ModelAttribute` : 메소드 파라미터나 리턴값을 지정된 모델으로 바인딩
- `@PathVariable` : {id}와 같이 URI 템플릿 변수에 메소드 인수를 바인딩
- `@CookieValue` : 쿠키 정보를 읽어들임
- <참고> [Spring MVC 어노테이션](https://www.javadevjournal.com/spring-mvc/spring-mvc-annotations/)

### 메소드 리턴값
- `ModelAndView`, `String`, `Model`, `View`, `ModelMap` 등 다양한 타입 리턴가능