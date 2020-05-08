# 1. WEB UI

### 1) 서비스 개발을 위한 디렉토리 구성

#### (1) CSS, JavaScript
- 간단한 내용이라면 한 페이지에 모두 표현
- 그렇지 않으면 **의미에 맞게 파일을 구별** (권장)

#### (2) HTML 내부 위치 구성

```html
<html>
  <head>
    <!-- <style>h1 { color: red; }</style> -->
    <link rel="stylesheet" href="./css/style.css">
  </head>
<body>
  <h1>Hello World</h1>

  <!-- contents here... -->

  <!--
  <script>
    const h1 = document.querySelector('h1');
    console.log(h1);
  </script>
  -->
  <script src="./js/main.js"></script>
</body>
</html>
```

- **CSS**는 DOM을 렌더링하기 위해 미리 로딩되어야 하기 때문에\
상단 `<head>` 태그 안에 위치 시킴
- **JavaScript**는 DOM을 참조하는 경우가 많고, CSS와 HTML이 렌더링될 때 방해하지 않기 위해\
하단의 `<body>` 태그가 닫히기 전 소스 파일간 의존성을 지켜서 순서대로 배치

### 2) 웹 페이지 로드 관련 이벤트
- 웹 사이트를 접속하면 요청에 대한 응답으로 콘텐츠들이 서버로부터 로드됨
- HTML을 파싱하면서 CSS, Javascript, Image 등을 받아오고나면 로드를 끝냄
- 이러한 작업 중간에 자바스크립트가 개입할 경우 문제 발생

#### DOMContentLoaded와 load 이벤트

```javascript
document.addEventListener('DOMContentLoaded', function() {
  startSomething();
  initA();
  initB();
});

window.addEventListener('load', function() {
  console.log('load is done.');
});
```

- Document 객체의 `DOMContentLoaded` 이벤트는 DOM Tree 분석을 모두 끝내고나면 발생
- Window 객체의 `load` 이벤트는 웹 페이지의 모든 CSS, 자바스크립트, 이미지 등이 모두 불려온,\
즉 화면에 필요한 리소스들의 렌더링이 모두 완료된 상태에 발생
- 시작시 특정 서비스 동작 수행을 원할 경우 DOMContentLoaded 이벤트 핸들러 내부에 구현하는 것이 좋다
- <참고> [DOMContentLoaded와 load 이벤트간의 차이](https://stackoverflow.com/questions/2414750/difference-between-domcontentloaded-and-load-events)

### 3) Event Delegation
#### 이벤트 버블링
- 부모-자식 관계의 엘리먼트가 있을 때 하위 엘리먼트에 클릭 이벤트가 발생하면\
그것을 감싸고 있는 상위 엘리먼트까지 올라가면서 이벤트 리스너가 있는지 찾는 과정

```html
<ul>
  <li>
    <img src="https://images-na.,,,,,/513hgbYgL._AC_SY400_.jpg" class="product-image">
  <li>
    <img src="https://images-n,,,,,/41HoczB2L._AC_SY400_.jpg" class="product-image">
  <li>
    <img src="https://images-na.,,,,51AEisFiL._AC_SY400_.jpg" class="product-image">
  <li>
    <img src="https://images-na,,,,/51JVpV3ZL._AC_SY400_.jpg" class="product-image">
  </li>
</ul>
```

- 위와 같은 코드가 있을 때, `ul > li`에 각각 클릭 이벤트 리스너를 달지 않아도\
`ul`에만 이벤트 리스너를 달면 하위 엘리먼트도 그 리스너의 영향을 받음
- 클릭한 지점에 존재하는 엘리먼트를 선택하기 위해서는 리스너 내부의 event 객체의 `target` 속성 이용\
(`currentTarget`은 이벤트 리스너가 연결된 엘리먼트만을 선택함)
- 이와 반대의 개념으로 이벤트 캡처링 _Event Capturing_ 이 존재. 그러나 **이벤트 버블링이 디폴트로 적용**됨
- <참고> [이벤트 버블링, 이벤트 캡처 그리고 이벤트 위임까지](https://joshua1988.github.io/web-development/javascript/event-propagation-delegation/)
![](https://cphinf.pstatic.net/mooc/20180207_43/1517986448399nM5Jy_PNG/3-5-3_Event_Bubbling.png)

#### 이벤트 위임

```javascript
var ul = document.querySelector("ul");
ul.addEventListener("click",function(evt) {
  debugger;
    if(evt.target.tagName === "IMG") {
      log.innerHTML = "clicked" + evt.target.src;
    } else if (evt.target.tagName === "LI") {
      log.innerHTML = "clicked" + evt.target.firstChild.src;
    }
});
```

- <img> 태그에 발생해야 할 이벤트를 상위 부모 엘리먼트에 위임한다는 뜻으로\
이러한 과정을 이벤트 위임 _Event Delegation_ 이라고 부름
- 각 자식 노드들 마다 이벤트 리스너를 달지 않아도 되기 때문에 간편하고 유지보수 용이

### 4) HTML Templating

- HTML과 데이터를 섞어서 웹 화면에 변경을 주는 것
- 구조는 비슷하지만 각각이 표현하는 데이터가 다름
- 하나의 리스트를 웹 화면에 추가하는 과정
- 즉, 반복적인 HTML 부분을 template으로 만들어두고,\
서버에서 온 데이터(주로 JSON)를 결합해서 화면에 추가하는 작업
- script 태그의 type을 text/template 등으로 설정해두고 HTML 템플릿을 숨기는 방법이나,\
서버에 file로 보관하고 AJAX로 요청해서 받아오는 방법 등이 있음
- 문자열 변환 메소드 중 `replace()`를 사용해서 템플릿을 사용한다
- <참고> [라이브러리 도움없이 templating 작업하기](https://jonsuh.com/blog/javascript-templating-without-a-library/)

# 2. Tab UI

- 컴포넌트 UI라고도 부르는 요소들 중 하나
- Tab 메뉴를 누르면 Ajax를 통해서 데이터를 가져온 후, 눌린 Tab에 맞는 데이터를 화면에 노출
- <참고> [Tab UI 구현해보기](https://gist.github.com/crongro/291c5555b63a0afa41960e09d0173e06)