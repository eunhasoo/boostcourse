# Ajax 통신의 이해

### Ajax란?
- Asynchronous JavaScript and XML의 약자
- 비동기 웹 애플리케이션 제작을 위해 사용하는 웹 개발 기법
- 기존 데이터 교환 방식에는 중복되는 코드를 다시 전송받으면서 많은 대역폭 낭비, 상호작용 서비스 한계 등 단점 존재
- Ajax는 페이지 전체 리프레쉬없이 필요한 데이터만을 서버에 요청하여 클라이언트에서 받은 데이터를 처리
- 서버와 클라이언트간의 데이터 교환을 위해 주로 JSON 포맷 형식 이용
- <참고> [Ajax 위키백과](https://ko.wikipedia.org/wiki/Ajax)

### 실행 예제

```javascript
function reqListener () {
  console.log(this.responseText);
}

var oReq = new XMLHttpRequest();
oReq.addEventListener("load", reqListener);
oReq.open("GET", "http://www.example.org/example.txt");
oReq.send();
```

- HTTP 요청을 보내기 위한 `XMLHttpRequest` 객체 생성
- 응답을 받았을 때 처리할 함수를 정의한 후 요청 전송 (open)
- open() 메소드의 파라미터\
1.`request method` : HTTP 표준에 따라 대문자 표기 ex. GET, POST\
2.`URL` : 정확한 도메인 네임이 아니면 permission denied 발생 가능\
3.`Asynchronous` : 생략시 기본값 true. false로 지정시 send() 메소드에서 서버로부터 응답이 올 때까지 대기 (동기적)

- <참고> [Ajax 시작하기](https://developer.mozilla.org/ko/docs/Web/Guide/AJAX/Getting_Started), [교차 출처 리소스 공유(CORS)](https://developer.mozilla.org/ko/docs/Web/HTTP/CORS)