# 1. AJAX

### 1) AJAX와 비동기 처리
- AJAX는 이전에 설명한 setTimeout 함수와 같이 비동기적이고,\
UX(User Experience)와 관련이 많은 기술
- 새로고침 없이 서버측에 데이터를 요청하여 화면에 렌더링함
- 브라우저가 화면을 그릴 때 Ajax와 같은(콜백, 이벤트 등) 작업이 있을 경우
한쪽(이벤트 큐)에 보내놓고 나머지 작업을 먼저 처리한 뒤에 해당 요청들을 작업하는 과정을 거침
- <참고> 동기/비동기에 대한 자료 [영상](https://www.youtube.com/watch?v=8aGhZQkoFbQ), [그림](http://www.phpmind.com/blog/2017/05/synchronous-and-asynchronous/)
- <참고> jQuery 라이브러리를 사용한 AJAX 통신의 코드 단위 처리 과정
![](https://cphinf.pstatic.net/mooc/20180202_278/15175639688702H54K_PNG/3-3-1_Ajax%28Jquery__%29.png)


### 2) AJAX 응답처리
- 서버로부터 응답 처리되는 AJAX 코드는 JSON 형태의 **문자열**로 전달되고,\
이 JSON 형태의 문자열을 토큰화하기 위해 JSON 객체를 이용하여 **객체**로 파싱한다
- 즉 응답을 처리하는 콜백 함수 내부의 `this.responseText`의 타입은 _string_ \
`JSON.parse(this.responseText);`와 같이 파싱하면 _object_ 타입으로 변환됨

### 3) Cross Domain 문제
- 보안상 도메인 A에서 도메인 B로 Ajax 데이터 통신이 불가능
- 이를 해결하기 위한 방법으로 `CORS`와 `JSONP`가 존재
- CORS보다는 JSONP가 주로 사용되는 편이다

#### CORS
- 백엔드(응답을 주는 서버)에서 헤더값에 허용 가능한 도메인을 등록하는 방법
- <참고> [Cross-origin resource sharing](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing)

#### JSONP
- HTML의 script 요소로부터 요청되는 호출에는 보안상 정책이 적용되지 않는다는 점을 이용한 우회 방법으로\
`XMLHttpRequest` 객체 대신 `<script>` 태그를 이용해 파일이 아닌 스크립트를 요청해 실행
- <참고> [JSONP 알고 쓰자](https://kingbbode.tistory.com/26)

### 4) AJAX 디버깅
- 네트워크 통신은 브라우저의 네트워크 모듈로 실행되기 때문에\
이런 부분을 소스 코드상으로는 디버깅을 할 수 없음
- 서버, 클라이언트, 네트워크 가운데 어디에서 문제가 일어났는지 알아내기 위해\
요청이 잘 보내졌는지, 응답이 잘 도달했는지 등을 디버깅해야 함
- **크롬 개발자 도구의 network 패널**을 통해 이들에 대한 정보를 알아낼 수 있다
![](https://cphinf.pstatic.net/mooc/20180205_131/1517812553101sxaO7_PNG/3-3-2____.png)

# 2. WEB Animation
### 1) 애니메이션이란?
- 반복적인 움직임의 처리
- 엘리먼트가 생성되거나 사라지는 등 여러 동작을 끊김없고 부드럽게 그리기 위함
- 사용자에게 매끄러운 UX를 제공하기 위한 기술

#### FPS
- Frame Per Second의 약자\
1초에 화면에 표현할 수 있는 정지화면 _frame_ 수
- 매끄러운 애니메이션의 경우 보통 `60fps`\
즉 16.666(= 1000ms/60fps)초 간격으로 frame 생성이 필요한 셈이다

#### 구현 방법
- 단순하고 규칙적인 경우 `CSS`의 transition과 transform 속성을 이용
- 세밀한 조작이 필요한 경우 `Javascript`를 이용해 변경

### 2) Javascript 함수를 이용한 애니메이션 구현
#### (1) setInterval와 setTimeout

```javascript
// 1) setInterval : 지정된 시간마다 정기적으로 실행
const interval = window.setInterval(() => {
  console.log('현재 시각은 ', new Date());
}, 1000 / 60);

// 2) setTimeout : 지정된 시간 이후에 한번만 실행
setTimeout(() => {
  console.log('현재 시각은 ', new Date());
}, 1000 / 60);

// 재귀적으로 호출하여 여러번 실행되도록 설정
let count = 0;
function animate() {
  setTimeout(() => {
    if (count >= 20) return; // 20이 되면 종료
    console.log('현재 시각은 ', new Date());
    count++;
    animate(); // 재귀 호출
  }, 500);
}
animate();
```

- `setInterval`의 경우 정해진 시간에 함수가 실행된다고 보장할 수 없기 때문에\
최근에는 일반적으로 애니메이션을 구현할 때 사용하지 않음
- `setTimeout` 또한 애니메이션을 위한 최적화된 함수가 아니기 때문에 최근에는 잘 사용되지 않음
- 다른 동작들을 수행하느라 바쁜 브라우저는 정확한 시간에 맞춘 cycle을 지키지 못하는 경우가 종종 발생
- <참고> [setTimeout과 setInterval](https://javascript.info/settimeout-setinterval)

#### (2) requestAnimationFrame

```javascript
let request;

const performAnimation = () => {
  request = requestAnimationFrame(performAnimation)
  //animate something
}
requestAnimationFrame(performAnimation)

cancelAnimationFrame(request) //stop the animation
```
- setInterval과 setTimeout의 대안으로 등장한, 상대적으로 최신 버전의 함수
- 애니메이션을 수행하기 위한 스탠다드 함수이기 때문에 복잡한 애니메이션을 다룰 때 유용
- 지정된 조건을 만족할 때까지 함수를 연속적으로 호출
- requestAnimationFrame()의 인자로 원하는 함수를 넣어주면\
브라우저가 그 비동기 함수가 실행될 가장 적절한 타이밍에 실행시켜줌
- 개발자의 입장에서는 브라우저를 믿고 함수를 전달하여 사용하는 방법이다
- <참고> [requestAnimationFrame 가이드](https://flaviocopes.com/requestanimationframe/), [MDN 레퍼런스](https://developer.mozilla.org/en-US/docs/Web/API/window/requestAnimationFrame)

### 2) CSS 기법으로 애니메이션 구현

```css
selector { /* 단축 표기법 */
  transition: property duration [timing-function] [delay];
}

/* 예시 */
div {
  transform-origin: left top;
  transition: transform 1s;
}

div:hover {
  transform: rotate(720deg);
}
```

- 자바스크립트 구현 방법보다 빠르고, 특히 모바일 웹에서 많이 구현됨
- transition과 transform을 결합하여 사용할 수 있지만,\
transition 그 자체로 (ex. 버튼 hover시 색상 변경) 한 스타일에서 다른 스타일로 변경할 경우도 사용 가능
- <참고> [초보자를 위한 Transition과 Transform의 이해](https://thoughtbot.com/blog/transitions-and-transforms), [하드웨어 가속에 대한 이해와 적용](https://d2.naver.com/helloworld/2061385)

#### transition 속성
- 얼마나 부드럽고 점진적으로 전환 _transition_ 할 것인지 지정하는 속성
- transition-property와 transition-duration 두 속성은 지정 필수
- `transition-property` : 트랜지션이 적용될 CSS 프로퍼티 (ex. background-color, transform, all, ...)
- `transition-duration` : 초/밀리초 단위로 지정되는 트랜지션 지속 시간 (ex. 3s, 1000ms, ...)
- `timing-function` : 지속중인 트랜지션의 속도 (ex. linear, ease, ...)
- `delay` : 트랜지션 시작에 걸릴 시간 지정 (ex. 0.5s, ...)
- 개별적으로 속성값을 정의할 수 있지만 간단하고 빠른 구현을 위해 단축 표기법을 주로 사용

#### transform 속성
- 엘리먼트 변화를 어떤 상태에서 어떤 상태로 변형 _transform_ 할 것인가에 대한 속성
- `-X(n)` : n이 양수인 경우 수평 오른쪽으로, 음수인 경우 수평 왼쪽으로 변형
- `-Y(n)` : n이 양수인 경우 수직 아래쪽으로, 음수인 경우 수직 위쪽으로 변형
- `deg` : n이 양수인 경우 시계방향으로, 음수인 경우 시계 반대방향으로 변형
- `rotate` : 엘리먼트를 회전하는 속성
- `skew` : 주어지는 방향값 만큼 엘리먼트를 기울이는 속성
- `scale` : 엘리먼트의 크기를 증가시키거나 감소시키는 속성
- `translate` : 엘리먼트를 상하좌우로 이동시키는 속성