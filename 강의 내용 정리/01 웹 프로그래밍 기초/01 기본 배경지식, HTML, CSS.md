# 1. 웹 개발의 이해
### HTTP
- Hypertext Transfer Protocol의 약자
- 클라이언트-서버 모델 기반
- 클라이언트(대개 웹 브라우저)가 처음 요청을 시작하여 서버와 메시지를 통해 통신
- HTML 문서 등과 같은 자원을 가져옴
- 웹에서 일어나는 어떠한 데이터 교환이든 기반이 되는 프로토콜
- 상태 비저장 stateless 프로토콜\
장점) 최대 연결수보다 더 많은 요청, 응답 수행 가능\
단점) 정보 유지를 위해서는 Cookie 등의 기술 필요

### URL
- Uniform Resource Locator의 약자
- 인터넷 상의 자원의 위치
- 특정 웹서버의 특정 파일에 접근하기 위한 경로

### HTTP method
- **Get** : 정보를 요청하기 위해 사용
- **POST** : 정보를 밀어넣기 위해 사용
- **PUT** : 정보를 업데이트하기 위해 사용
- **DELETE** : 정보를 삭제하기 위해 사용
- **HEAD** : 헤더 정보만 요청해서 자원 혹은 서버의 상태를 체크하기 위해 사용
- **OPTIONS** : 웹서버가 지원하는 메서드의 종류를 요청
- 이외에도 TRACE, CONNECT, PATCH 등이 존재

### Front-end
- 사용자에게 웹을 통해 다양한 컨텐츠를 제공 (ex. 문서, 이미지, 동영상, ...)
- 사용자의 요청에 반응하여 동작
- HTML (구조), CSS (스타일), Javascript (동적 작용)

### Back-end
- 정보를 처리하고 저장하며 요청에 따라 정보를 제공하는 역할
- Java, PHP, Javascript 등을 이용
- 웹의 동작 원리, 프레임워크, 네트워크, DBMS 등의 다양한 지식 기반 필요

### 웹 브라우저의 동작
![](https://cphinf.pstatic.net/mooc/20171231_32/1514692895834EoHUo_PNG/webkitflow.png)

- 사파리 브라우저에서 처리 되는 webkit 렌더링 엔진의 처리 과정
- 출처 https://www.html5rocks.com/en/tutorials/internals/howbrowserswork/

### 웹 서버
- 클라이언트가 요청하는 HTML 문서나 각종 리소스들을 전달하는 기능을 하는 소프트웨어 (컴퓨터)
- 웹 브라우저나 웹 크롤러가 요청하는 리소스는 컴퓨터에 저장된 정적 문서이거나 동적 결과물일 수 있음

### DBMS
- DataBase Management System의 약자
- 다수의 사용자가 데이터베이스 내의 데이터에 접근할 수 있도록 해주는 소프트웨어

### 미들웨어
- 클라이언트 쪽에 비즈니스 로직이 많을 경우 관리로 인해 비용이 많이 발생
- 비즈니스 로직을 클라이언트와 DBMS 사이의 미들웨어 서버에서 동작하도록 하여 클라이언트는 입력과 출력만을 담당

### WAS
- Web Application Server의 약자
- 일종의 미들웨어로, 웹 클라이언트의 요청 중 웹 어플리케이션이 동작하도록 지원하는 데 목적

### 웹 서버 vs WAS
- WAS도 자체적으로 웹 서버 기능을 내장했으며 뚜렷한 성능상의 차이는 없음
- 자원 이용의 효율성 및 장애 극복, 배포 및 유지보수의 편리성을 위해서 규모가 커질수록 웹 서버와 WAS를 분리

# 2. HTML, CSS

### 레이아웃 태그
![](https://cphinf.pstatic.net/mooc/20171231_41/15146999078486r8Pv_JPEG/5086.HTML5PageLayout_2.jpg)
- HTML 태그들을 구성할 때 해당 레이아웃에 맞도록 하여 가독성을 높일 수 있음

### HTML 구조설계
- 영역을 나눠서 상단/본문/네비게이션 등과 같이 큰 부분부터 분리
- 분리된 각 영역 안의 내용 구조를 잡으면서 또 다시 내용의 형태에 따라 분리
- 내부적으로 점점 좁혀나가면서 태그를 사용하여 완성해나감
- CSS 코드를 구현하기 전에 HTML로 먼저 문서의 구조를 잡아나가는 것이 개발 단계에서 유리

### class와 id
#### 1) class
- 한 HTML 문서 안에 중복으로 사용 가능
- 태그에 여러 개의 다른 class 이름을 공백을 기준으로 나열할 수 있음
- 전체적 스타일을 일관성 있게 지정하기 위해 필수적

#### 2) id
- 고유한 속성이기에 한 HTML 문서에 하나만 사용 가능
- 하나하나 특별한 제어와 검색에 용이

### CSS
#### 1) 구성

```css
span {		/* span은 선택자 selector를 나타내고, */
  color: blue; 	/* color는 property, blue는 value를 나타낸다 */
}
```

#### 2) 스타일 적용 우선순위
- inline > internal = external (후자 선언 우선 적용)
- id > class > element
- <참고> https://developer.mozilla.org/en-US/docs/Web/CSS/Specificity