# 1. window
### window 객체
- 모든 브라우저에서 지원되는 브라우저 창을 나타내는 전역 객체
- 많은 메소드들이 존재하며 메소드 호출시 객체명 생략이 가능
- 모든 전역 JavaScript 객체, 함수 및 변수는 자동으로 윈도우 객체의 멤버가 됨

### 비동기 실행 과정 이해하기
```javascript
var run = function() {
    setTimeout(function() {
        console.log('time out');
    }, 2000);
}

console.log('hello');
run();
console.log('bye');
```
- window 객체의 setTimeout은 지정한 시간만큼 기다렸다가 실행되는 비동기 처리 메소드
- 비동기 처리란 특정 로직의 실행이 끝날 때까지 기다려주지 않고 나머지 코드를 먼저 실행하는 것을 의미
- [hello-time out-bye] 순으로 출력되지 않고 [hello-bye-time out] 순으로 출력되는 것을 확인할 수 있음

# 2. DOM

![](https://cphinf.pstatic.net/mooc/20180126_280/1516956194218XFPk5_PNG/2-2-2_Dom_tree.png)
- document object model의 약자
- 브라우저에서 HTML 코드를 저장할 때 DOM이라는 객체 형태의 모델에 저장
- 그렇게 저장된 정보를 DOM Tree라고 지칭
- DOM Tree는 복잡하기 때문에 DOM API를 통해 쉽게 접근하고 조작할 수 있도록 여러 메소드 제공
- 주요 메소드: `getElementById()`, `querySelector()`, `querySelectorAll()`

# 3. Event

```javascript
var el = document.getElementById(".outside");
el.addEventListener("click", function(e) {
	// outside 클래스로 지정된 엘리먼트를 클릭했을 때 동작할 행위 정의
	console.log(e.target); // 이벤트가 발생한 element를 가리킨다
	console.log(e.target.nodeName);
}, false);
```

- 브라우저에서 마우스 클릭, 스크롤 이동 등 다양한 동작으로 이벤트 발생
- addEventListener 메소드를 이용해 Event Handler(Listener)를 정의하여 이벤트가 발생했을 때 어떤 동작을 하도록 함수로 등록