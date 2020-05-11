# 1. Spring Framework 

### Spring Framework란?
- `Framework`는 반제품처럼 이미 어느정도 만들어져 있는, 프로그램의 기본 토대 및 골격을 의미
- 엔터프라이즈급의 큰 애플리케이션을 구축할 때 사용할 수 있는 가벼운 솔루션
- 모든 과정을 한꺼번에 해결할 수 있다 (One-Stop-Shop)
- 원하는 부분만 가져다 사용할 수 있도록 모듈화가 잘 되어있음

### 프레임워크 모듈

![](https://cphinf.pstatic.net/mooc/20180201_180/1517452205302mNfIy_PNG/2_10_1___.png)
- 약 20개의 모듈로 구성
- 필요한 모듈만을 가져다 사용할 수 있음
- 모든 모듈을 한꺼번에 알 필요는 없고 필요할 때마다 배워서 사용
- <참고> [Spring Framework Modules](https://docs.spring.io/spring/docs/4.3.14.RELEASE/spring-framework-reference/htmlsingle/#overview-modules)

### IoC, DI, 컨테이너

#### 컨테이너란?

- **인스턴스의 `생명주기`를 관리**
- 생성된 인스턴스들에게 추가적인 기능 제공
- 개발자가 정의한 서블릿을 톰캣(WAS)이 자동으로 메모리에 올리고 실행하는 역할과 동일한 개념

#### IoC란?
- Inversion of Control의 약자
- 개발자가 아닌 다른 프로그램이 프로그램의 흐름을 제어하는 것
- TV 리모컨의 기능과 외관이 TV 제작 회사별로 크게 다르지 않도록 제작하는 것처럼\
프로그램 내부 코드도 각기 다른 객체들을 하나의 인터페이스를 이용해서 관리하고\
인스턴스의 생성, 소멸 등의 제어 역할을 `보관함`에 모아서 맡김
- 개발자가 만든 서블릿의 메소드들을 제때 알맞게 호출하는 WAS의 역할과 동일한 개념

#### DI란?
- Dependency Injection의 약자
- 의존성을 주입한다는 뜻으로 필요한 객체를 `보관함`으로부터 가져오는 것을 의미
- 클래스 사이의 의존 관계를 빈 설정 정보를 바탕으로 `컨테이너`가 자동으로 연결해줌

```java
// 1. DI가 적용되지 않은 예
class 엔진 { }

class 자동차 {
    // 개발자가 직접 인스턴스를 생성
    엔진 v5 = new 엔진();
}

// 2. DI가 적용된 예
@Component
class 엔진 { }

@Component
class 자동차 {
    // 컨테이너가 v5 변수에 인스턴스를 할당
    @Autowired 엔진 v5;
}
```

### Spring에서 제공하는 Ioc/DI 컨테이너
- 위에서 설명한 `보관함`의 개념
- `BeanFactory` : IoC/DI에 대한 기본 기능을 가짐 (구식)
- `ApplicationContext` : BeanFactory의 모든 기능을 포함하며 일반적으로 사용이 권장됨\
트랜잭션, AOP, 국제화, 어플리케이션 이벤트 등의 처리가 가능하고\
BeanPosterProcessor, BeanFactoryPostProcessor 등을 자동으로 등록함
- `BeanPostProcessor` : 컨테이너의 기본 로직을 오버라이딩하여 인스턴스화 및 의존성 처리 로직 등을 개발자가 구현할 수 있음
- `BeanFactoryPostProcessor` : 설정된 메타 데이터를 커스터마이징할 수 있음
- <참고> [Spring IoC&DI](https://isstory83.tistory.com/91), [DI 정의, 유형, 종류](http://www.nextree.co.kr/p11247/)

### Bean Class
- 예전엔 Visual한 컴포넌트를 일컬었지만 최근에는 일반적인 Java Class를 말한다

#### 3가지 특징
- 기본 생성자를 가진다
- 필드를 `private`하게 선언한다
- getter, setter 메소드를 가지며 이들을 `프로퍼티` _property_ 라고 부른다.

### Bean 객체 이용하기

#### xml 파일을 이용한 설정 예시

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="userBean" class="kr.or.connect.diexam01.UserBean"></bean>

</beans>
```

#### Java Config(어노테이션)를 이용한 설정
- `@Configuration` : 스프링 설정 클래스를 선언하는 어노테이션
- `@Bean` : bean을 정의하는 어노테이션
- `@ComponentScan` : @Controller, @Service, @Repository, @Component 어노테이션이 붙은 클래스를 찾아 컨테이너에 등록
- `@Component` : 컴포넌트 스캔의 대상이 되는 어노테이션중 하나로써 주로 유틸, 기타 지원 클래스에 붙여짐
- `@Autowired` : 주입 대상이 되는 bean을 컨테이너에서 찾아 주입하는 어노테이션

#### 예시

```java
// ApplicationConfig.java
@Configuration // 스프링 설정 클래스라는 의미를 가짐
public class ApplicationConfig {
	@Bean
	public Car car(Engine e) {
		Car c = new Car();
		c.setEngine(e);
		return c;
	}
	
	@Bean
	public Engine engine() {
		return new Engine();
	}
}

// ApplicationConfig2.java
@Configuration
@ComponentScan("kr.or.connect.diexam01")
public class ApplicationConfig2 {
}

// ApplicationContextExam03.java
public class ApplicationContextExam03 {

	public static void main(String[] args) {
		// Java Config 클래스를 읽어들여 IoC와 DI를 적용한다
		// @Bean이 붙은 메소드들을 자동으로 실행하여 그 결과로 리턴하는 객체들을 기본적으로 싱글턴으로 관리
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		Car car = (Car)ac.getBean("car");
		car.run();
	}
}
```

- 실습파일 diexam01 참고
- <참고> [Field 의존 주입은 유해한 것으로 간주된다](https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/), [생성자 주입을 사용해야 하는 이유](https://yaboong.github.io/spring/2019/08/29/why-field-injection-is-bad/)

# 2. Spring JDBC
### Spring JDBC
- 이전의 JDBC 프로그래밍에는 반복되는 개발 요소가 많이 있었음
- 코드의 중복은 개발자를 지루하게 만들고 유지보수를 어렵게 만듬
- 모든 저수준 세부사항은 스프링 프레임워크가 담당해서 처리하여 개발자는 필요한 부분만 개발할 수 있게 해줌
- 연결 파라미터 정의, SQL문, 파라미터 선언과 값 제공, 이터레이션 작업을 빼면 스프링이 알아서 처리함
- 이마저도 불편해서 등장한 기술로는 Spring JPA와 Mybatis가 있음

#### Spring JDBC 패키지
- `core` : JdbcTemplate 및 관련 Helper 객체 제공
- `datasource` : DataSource를 쉽게 접근하기 위한 유틸 클래스, 트랙잭션 매니저 및 다양한 DataSource 구현 제공
- `object` : RDBMS 조회, 갱신, 저장 등을 안전하고 재사용 가능한 객체 제공
- `support` : core 및 object를 사용하는 JDBC 프레임워크를 지원

#### JDBC Template
- 가장 중요한 클래스로 리소스 생성, 해지 처리
- Statement 생성과 실행 및 SQL 조회, 업데이트
- 저장 프로시저 호출, ResultSet 반복호출 등을 실행
- SQl 조회, 업데이트, 저장 등을 수행

#### 간단한 예제
- 열의 수 구하기

```java
int rowCount = this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM t_actor");`
```

- 변수 바인딩 사용

```java
int countOfActorsNamedJoe = this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM t_actor WHERE first_name = ?", "Joe");
```

- String 값으로 결과 받기

```java
String lastName = this.jdbcTemplate.queryForObject("SELECT last_name FROM t_actor WHERE id = ?", new Object[] {1212L }, String.class);
```

#### JdbcTemplate 외의 접근 방법
- NamedParameterJdbcTemplate : `?` 인자 대신 파라미터명을 사용해 작성하도록 지원
- SimpleJdbcTemplate : JdbcTemplate + NamedParameterJdbcTemplate (더 이상 사용X)
- SimpleJdbcInsert : 테이블에 쉽게 insert 기능 제공

### 주요 용어 정의

#### DTO
- Data Transfer Object의 약자
- `계층` 간 데이터 교환을 위한 Java Beans (계층이란 컨트롤러, 비즈니스, 퍼시스턴스 계층을 의미)
- 로직을 가지고 있지 않은 순수한 데이터 객체

#### DAO
- Data Access Object의 약자
- 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 객체
- 보통 데이터베이스를 조작하는 기능을 전담하는 목적으로 만들어짐

#### ConnectionPool
- 비용이 많이 드는 DB 연결을 줄이기 위해 미리 커넥션풀에 커넥션을 여러개 맺어두고 사용
- 커넥션이 필요하면 커넥션 풀에게 빌려서 사용한 후 반납
![](https://cphinf.pstatic.net/mooc/20180208_14/15180684447693OANG_JPEG/3_8_2_ConnectionPool.jpg)

#### DataSource
- 커넥션 풀을 관리하는 목적으로 사용되는 객체
- 이 객체를 이용해 커넥션을 얻어오고 반납하는 등의 작업을 수행

- 실습파일 daoexam 참고