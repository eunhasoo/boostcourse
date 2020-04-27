# 1. Maven

### 1) CoC란?
- Convention over Configuration의 약자
- 프로그램의 여러 파일들의 **위치**들을 미리 정해놓는 일종의 관습
- Maven 이전에 선행되는 개념

### 2) Maven이란?
- 빌드, 패키징, 문서화, 테스트 및 리포팅, git, 의존성 관리, 형상관리 서버와 연동, 배포 등의 작업을 손쉽게 하도록 도와주는 도구
- 이전에는 특정 폴더에 라이브러리 파일을 일일이 복사해서 사용했음 (ex. WEB-INF/lib)\
이러한 방식은 사용되는 라이브러리가 많아질 수록 불편함이 늘어남
- Maven을 이용하면 직접 다운로드받지 않아도 라이브러리를 사용할 수 있고,\
여러 개발자가 일관된 방식으로 빌드를 수행할 수 있게 될뿐만 아니라 다양한 플러그인 제공으로 많은 일들을 자동화할 수 있음
- <참고> [Maven 주요 기능](http://maven.apache.org/maven-features.html)

### 3) 살펴보기

```java
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>kr.or.connect</groupId>
    <artifactId>examples</artifactId>
    <packaging>jar</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>mysample</name>
    <url>http://maven.apache.org</url>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

#### 기본 태그 의미
- `project` : 최상위 루트 엘리먼트
- `modelVersion` : POM model의 버전
- `artifactId` : 프로젝트를 생성하는 조직의 고유 아이디. 일반적으로 도메인 이름을 거꾸로 적음
- `packaging` : 해당 프로젝트를 패키징할 방식 (jar, war, ear 등)
- `version` : 프로젝트의 현재 버전.
- `name` : 프로젝트의 이름
- `url` : 프로젝트 사이트 존재시 사이트 URL 등록

### 4) Maven 프로젝트 생성 관련
- 프로젝트 템플릿과 같은 개념인 Archetype으로 `maven-archetype-webapp` 선택
- `Group Id`는 보통 프로젝트를 진행하는 회사나 팀의 도메인 이름을 거꾸로 적어준다
- `Artifact Id`는 해당 프로젝트의 이름을 적어준다
- `Version`은 보통 기본값(0.0.1-SNAPSHOT)으로 설정한다
- `Package`명은 Group Id + Artifact Id가 조합된 이름으로 자동 설정된다
- 프로젝트 생성후 Java 빌드 버전을 1.8로 맞춰준다
- EL 표기법 사용을 위해 다이나믹 웹 모듈을 3.1으로 설정한다
- <참고> [Eclipse의 Maven을 사용한 다이나믹 웹 프로젝트 생성](https://crunchify.com/how-to-create-dynamic-web-project-using-maven-in-eclipse/), [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

# 2. JDBC

### 1) JDBC란?
- Java Database Connectivity의 약자
- 자바를 이용한 데이터베이스 접속, SQL문 실행, 실행결과 데이터 핸들링 등을 제공하는 방법과 절차에 관한 규약
- 자바 프로그램 내에서 SQL문을 실행하기 위한 표준 인터페이스 API (=JDBC API) 포함
- SQL + 프로그래밍 언어의 통합 접근 중 한 형태
- DataBase Vendor에 의해 제공받은 라이브러리를 추가해서 사용
- <참고> [JDBC Basics](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)


### 2) JDBC 프로그래밍
1. **드라이버 로드**: DB 벤더가 제공하는 인터페이스 사용을 위해 라이브러리 로드
2. **Connection 객체 생성** : 데이터베이스 연결/접속
3. **Statement 객체 생성 및 질의 수행** : SQL문을 만들고 수행
4. (결과 데이터 존재시) **ResultSet 객체 생성** : 쿼리문에 따라 결과 존재 유무 다름
5. **모든 객체 연결 끊기** : 더 이상 사용하지 않으면 _열었던 순서의 반대로_ 닫아줌\
<참고> [try-with-resources 문법](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)을 이용하면 close 메소드 호출없이도 자동으로 자원 반납이 가능하다
- 수정/삽입/삭제가 반복되면 중복되는 소스코드가 늘어나고 유지보수가 어려워지는 문제 발생 → spring으로 발전

# 3. API
### 1) API란?
- 응용 프로그래밍 인터페이스 Application Programming Interface의 약자
- 주로 파일/창/문자 제어, 화상 처리 등을 위해 인터페이스가 제공된다
- 호출하려는 메소드의 소스코드를 모르더라도 인터페이스를 알면 사용할 수 있다
- <참고> [Java 8 API](https://docs.oracle.com/javase/8/docs/api/)

### 2) REST API란?
- REpresentational State Transfer의 약자
- REST 형식의 API로, 핵심 컨텐츠 및 기능을 외부 사이트에서 활용할 수 있도록 제공되는 인터페이스
- Web browser 뿐만 아니라 App 등 다양한 클라이언트들이 등장함에 따라 그들에 대응하기 위해 널리 사용되기 시작
- 서비스 업체들이 다양한 REST API를 제공함으로써, 클라이언트가 이들을 조합해 어플리케이션을 제작할 수 있게 됨\
<참고> [네이버 API](https://developers.naver.com/products/intro/plan/), [페이스북 API](https://developers.facebook.com/docs), [공공 데이터 포털](https://www.data.go.kr/)

### 3) 쉽지 않은 REST 구현
#### 3-1. REST가 지켜야 할 스타일
- client-server
- stateless
- cache
- layered system
- code-on-demand (optional)
- uniform interface

스타일은 제약조건의 집합을 의미한다.\
위에서 언급한 스타일들을 모두 지켜야만 REST API라고 말할 수 있다.\
HTTP를 사용하면 _uniform interface를 제외한_ 나머지는 쉽게 구현이 가능하다.

#### 3-2. uniform interface의 스타일
- 리소스가 URI로 식별되어야 한다
- 리소스 생성, 수정, 추가를 위해서는 HTTP 메시지에 표현을 전송해야 한다
- **메시지는 스스로 설명될 수 있어야 한다 (Self-Descriptive Message)**
- **어플리케이션의 상태는 Hyperlink를 이용해 전이되어야 한다 (HATEOAS)**

1, 2번째 항목과 달리 3, 4번째 항목은 API 특성상 지키기 쉽지 않다.\
일반적으로 응답 결과로 JSON을 사용하게 되는데 이 메시지가 스스로 설명되기란 쉽지 않고,\
웹 페이지 자체에 관련된 링크인 HATEOAS를 API에서 제공하는 것 또한 쉬운 일이 아니다.

### 4) Web API (HTTP API)
- 위처럼 REST의 uniform interface을 지원하기 쉽지 않은 이유 때문에\
많은 서비스가 REST 스타일을 모두 지키지 않고 API를 개발하게 된다
- REST API의 모든 것을 제공하지 않으면서 REST API라고 말하는 경우도 있지만,\
Web API 혹은 HTTP API라고 지칭하는 것이 올바른 용어 표현이라고 할 수 있다
- <참고> [REST API 제대로 알고 사용하기](https://meetup.toast.com/posts/92), [당신의 API가 Restful하지 않은 5가지 증거](https://beyondj2ee.wordpress.com/2013/03/21/%EB%8B%B9%EC%8B%A0%EC%9D%98-api%EA%B0%80-restful-%ED%95%98%EC%A7%80-%EC%95%8A%EC%9D%80-5%EA%B0%80%EC%A7%80-%EC%A6%9D%EA%B1%B0/)

### 4-1. 디자인 가이드

#### <1> URL는 정보의 자원을 표현해야 한다
![](https://cphinf.pstatic.net/mooc/20180206_109/1517904573515UkVsl_PNG/2_11_2_webapi.PNG)

#### <2> 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현한다
- 메소드에 따라서 API에 요청하는 바가 달라지게 된다
- 예시)\
(1) GET /members : O\
멤버의 모든 정보를 달라는 요청이다.\
(2) GET /members/**delete**/1 : X\
GET은 정보를 요청할 때 사용한다. **동사로 삭제를 표현해선 안된다.**\
(3) DELETE /memebers/1: O\
멤버 1을 삭제해달라는 요청이다. 이와 같이 DELETE 방식으로 삭제를 표현하는 것이 올바르다.\
(4) POST /members : O\
멤버를 추가하는 요청이다.\
(5) PUT /members/1 : O\
멤버 1에 해당하는 데이터를 갱신해달라는 요청이다.

#### <3> `/`는 계층을 나타낼 때 사용한다
- URI 마지막 문자로 /를 포함하지 않는다
- `-`는 가독성을 높일 때 사용하고 `_`는 사용하지 않는다
- 소문자만 사용한다
- 파일 확장자는 URI에 포함시키지 않고 Accept Header를 사용한다
- 예시)\
http://domain/houses/apartments \
http://domain/departments/1/employees

### 5) 상태 코드
#### 성공

![](https://cphinf.pstatic.net/mooc/20180206_273/1517904784161eGNFk_PNG/2_11_1_1.PNG)

#### 클라이언트로 인한 오류

![](https://cphinf.pstatic.net/mooc/20180206_113/1517904803278oyxuH_PNG/2_11_1_2.PNG)

#### 서버로 인한 오류

![](https://cphinf.pstatic.net/mooc/20180206_194/1517904834300fxqr9_PNG/2_11_1_3.PNG)