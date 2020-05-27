# 1. Layered Architecture
### Controller에서 중복 처리하기
- 별도의 객체로 분리
- 별도의 메소드로 분리
- ex) 게시판에서도 회원 정보를 불러와야 하고 상품 목록에서도 회원 정보가 필요하다면?

### 컨트롤러와 서비스 Service

![](https://cphinf.pstatic.net/mooc/20180219_85/1519008848012uvMNx_PNG/1.png)

- 서비스 객체는 업무와 관련된 메소드(=비지니스 메소드)를 가짐
- 비지니스 메소드를 별도의 `Service` 객체에서 구현하도록 하고\
컨트롤러는 Service 객체를 사용함

### 서비스 객체
- 비지니스 로직 _Business Logic_ 을 수행하는 메소드를 가진 객체
- 보통 **하나의 비지니스 로직**은 **하나의 트랜잭션**으로 동작

### 트랜잭션 Transaction
- 하나의 논리적인 작업을 의미
- `원자성`, `일관성`, `독립성`, `지속성`의 4가지 특징 존재 **_ACID_**

#### 1) 원자성 Atomicity
- **전체가 성공**하거나 **전체가 실패**하는 것
- 출금하는 도중에 오류가 난다면, 과정 이전의 상태로 돌아가야 함 (rollback)
- 출금 과정을 모두 성공적으로 수행한다면 정보를 반영해야 함 (commit)

#### 2) 일관성 Consistency
- 트랜잭션의 **작업 처리 결과가 항상 일관성**있어야 한다는 것
- 트랜잭션을 진행하는 동안 데이터가 변경됐다 하더라도,\
처음 참조한 데이터로 진행함으로써 각 사용자가 일관성 있는 데이터를 참조하게 됨

#### 3) 독립성 Isolation
- 둘 이상의 트랜잭션이 병행 실행 중인 경우\
어느 한 트랜잭션이라도 다른 트랜잭션의 결과를 참조할 수 없는 것
- 실행중인 트랜잭션이 **완료되기 전까지는** 다른 트랜잭션이 해당 **연산에 끼어들 수 없음**

#### 4) 지속성 Durability
- 트랜잭션이 성공적으로 완료됐을 경우 결과가 **영구적으로 반영**되어야 하는 것

### JDBC 프로그래밍의 트랜잭션 처리
- Connection 객체의 setAutoCommit 메소드에 false를 파라미터로 지정
- SQL문을 완료하고 성공했을 경우 Connection이 가지는 commit() 메소드 호출

### Spring의 트랜잭션 처리
#### @EnableTransactionManagement
- Spring Java Config 파일에서 트랜잭션을 활성화할 때 사용하는 어노테이션
- PlatformTransactionManager 구현체를 모두 찾아서 그중 하나를 맵핑해 사용
- TransactionManagementConfigurer를 구현해서 원하는 트랜잭션 매니저를 리턴하도록\
Java Config 파일에서 구성하면 특정 트랜잭션 매니저를 사용할 수 있음
- 아니면 @Primary 어노테이션을 지정해 사용가능

### 서비스 객체내의 중복 코드 처리
- 데이터 액세스 메소드를 별도의 Repository(DAO) 객체에서 구현하도록 하고,\
서비스에서 Repository 객체를 사용

### Layered Architecture

![](https://cphinf.pstatic.net/mooc/20180219_283/1519009121486u3LkD_PNG/2.png)

- `Presentation Layer`에서는 컨트롤러의 역할
- `Service Layer`에서는 비즈니스 로직을 담당하는 서비스의 역할
- `Repository Layer`에서는 데이터베이스에 접근하는 역할
- 각 계층별로 Config 파일도 따로 관리하는 것이 권장됨
- <참고> [Spring Web Architecture의 이해](https://www.petrikainulainen.net/software-development/design/understanding-spring-web-application-architecture-the-classic-way)

### 설정의 분리
- Spring 설정 파일을 Presentation layer와 나머지를 분리할 수 있음
- web.xml 파일에서 컨트롤러에 대한 스프링 설정은 `DispatcherServlet`이 읽도록 하고,\
그외 설정은 `ContextLoaderListener`를 통해 읽도록 지정
- ContextLoaderListener와 DispatcherServlet은 각각 ApplicationContext를 생성
- ContextLoaderListener가 생성하는 ApplicationContext가 root 컨텍스트가 되고,\
DispatcherServlet이 생성한 인스턴스는 root 컨텍스트를 부모로 하는 자식 컨텍스트가 됨
- 자식 컨텍스트들은 root컨텍스트의 설정 빈 사용이 가능

### Spring MVC 참고
- [Spring Web MVC 레퍼런스](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [Spring MVC Tutorial](https://www.javatpoint.com/spring-mvc-tutorial)

# 2. Controller

### 1) Spring에서의 Web API
#### @RestController
- Spring 3까지는 주로 `@Controller`와 `@ResponseBody`를 이용해 API를 작성
- **Spring 4부터** @RestController를 사용 (이전 버전의 어노테이션도 포함)
- <참고> [RESTful하게 Web Service 만들기](https://spring.io/guides/tutorials/bookmarks/), [Spring으로 REST 서비스 만들기](https://spring.io/guides/gs/rest-service/)

#### MessageConvertor
- 자바 객체와 HTTP 요청/응답 바디를 변환
- @ResponseBody, @RequestBody, @EnableWebMvc로 인한 기본 설정
- 외부에서 전달받은 JSON 메소드를 내부에서 사용할 수 있는 객체로 변환하거나,\
컨트롤러가 리턴한 객체가 JSON으로 변환돼서 클라이언트에게 전달할 수 있도록 역할을 수행
- 다양한 종류의 컨버터가 기본적으로 제공되나\
`jackson` 라이브러리를 추가하지 않으면 JSON으로 변환할 수 없어 500 오류 발생
- jackson 라이브러리를 추가할 경우 객체를 JSON으로 변환하는 메시지 컨버터가 사용되도록\
`@EnableWebMvc`에 기본으로 설정되어 있음
- 사용자 임의의 컨버터를 사용하려면 `WebMvcConfigurerAdapter`의 ConfigureMessageConverters 메소드 오버라이딩 필요
