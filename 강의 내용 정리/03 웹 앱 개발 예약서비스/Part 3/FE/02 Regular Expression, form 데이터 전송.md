# 1. 정규 표현식

### 정규 표현식 _Regular Expression_

- 문자열의 **특정 패턴**을 찾는 문법
- 패턴을 찾아 추출, 삭제, 치환 등의 문자열 조작이 가능
- 따로 문자열 메소드를 이용해 작업을 하는 것보다 획기적으로 코드양을 줄일 수 있고\
더 빠른 속도로 문자열을 조작할 수 있는 방법을 제공

### 실무에서의 사용

- 이메일, 주소, 전화번호의 규칙 검증
- textarea에 입력된 값중 불필요한 입력값의 추출\
(허용되지 않는 글자 입력 등)
- 트랜스 파일링\
(기존 소스코드를 읽어서 현재 지원하는 문법으로 변환)
- 개발 도구에서의 문자열 치환
- 신용카드 번호 패턴
- 특정 지역의 주소/우편 번호

### 간단한 사용법 알아보기

```javascript
// 숫자 하나 찾기
"1".match(/1/); // ["1"]
"512".match(/11/); // null
"a12b".match(/52/)[0]; // "52"

// 문자열 안에 있는 모든 두 자리 숫자 찾기
"a1v35b0".match(/\d\d/); // ["35"]
```

### 공부 방법

- Javascript Regex Cheatsheet
- <참고> [정규 표현식에 쓰이는 특수문자](http://www.devholic.net/1000238)
- 패턴들을 테스트해보면서 구현해보기

### 예제 살펴보기

#### 우편번호

```javascript
const mailNum1 = "160-20";
mailNum.match(/\d\d\d-\d\d/)[0]; // "160-20"
mailNum.match(/\d{3}-\d{2}/)[0]; // "160-20"

const mailNum2 = "16020";
mailNum2.match(/\d{5}/)[0]; // "16020"

// 괄호로 묶은 하위 표현식 Sub Expression
const oldAndNew = /(\d{3,4}-\d{2,3}|\d{5})/;
"160-23".match(oldAndNew)[0]; // "160-23"
"16582".match(oldAndNew)[0]; // "16582"

// '9로 시작하는' 다섯 자리수 방지
const notStartWith9 = /(\d{3,4}-\d{2,3}|[0-8]\d{5})/;
"96582".match(notStartWith9); // null
```

#### 핸드폰 번호 규칙

```javascript
const myPhone1 = "010-1234-5678";
const myPhone2 = "011-123-5678";
const myPhone3 = "013-1234-5678";

myPhone1.match(/\d{3}-\d{4}-\d{4}/)[0]; // "010-1234-5678"
myPhone2.match(/01[01789]-\d{4}-\d{4}/)[0]; // null
myPhone2.match(/01[01789]-\d{3,4}-\d{4}/)[0]; // null
```

#### 개발 도구에서의 함수 선택하기

```javascript

// 괄호로 시작 (즉시 실행 함수): escape 문자 사용
\(

// 괄호가 하나 있거나 없음
\(?

// whitespace (공백)
\(?function\s

// 공백 하나 이상
\(?function\s+
\(?function\s{1,}

// a-z, _ 를 포함한 문자 ($는 미포함)
\(?function\s+\w

// $까지 포함한 문자 하나 이상, 이후 괄호 한 개 (최종)
\(?function\s+[a-zA-z_$]+(
```

#### 개발 도구에서의 변수 타입 바꾸기

```javascript
// var to const
[find]    : (\s?)(var)(\s+[a-zA-Z$_]+)
[replace] : $1const$3
```

#### greedy/lazy 수량자

- 탐욕적(greedy) 수량자: `*`, `+`, `{n,}`
- 게으른(lazy) 수량자: `*?`, `+?`, `{n,}?`

### 전/후방 탐색 패턴

#### 전방탐색 _lookaround_ : 앞에서 찾기

- 일치 영역을 발견해도 그 값을 바로 반환하지 않고,\
소비를 하지 않는 패턴 _not consume_ (↔ consume)
- 실제로는 하위 표현식이고 같은 형식으로 작성
- `?=`로 시작하여 일치할 텍스트가 바로 다음에 오는 하위 표현식

```javascript
var regex = /.+(?=:)/;

// :를 찾되 :앞에 있는 문자열을 찾고, :는 소비되지 않음을 주의
"http://www.forta.com".match(regex)[0]; // http
"https://mail.forta.com".match(regex)[0]; // https
"ftp://ftp.forta.com".match(regex)[0]; // ftp

// ?= 없이 .+(:)로 찾을 경우에는 :와 일치하는 문자열을 소비
"http://www.forta.com".match(/.+(:)/)[0]; // http:
```

#### 후방탐색 _lookbehind_ : 뒤에서 찾기

- 텍스트를 반환하기 전에 뒤 쪽을 탐색
- 후방탐색 또한 소비를 하지 않는 패턴 _not consume_
- `?<=`을 사용해 전방 탐색과 같은 방식으로 사용
- 하위 표현식 내부에서 사용하고, 일치할 텍스트 앞에 위치

```javascript
var regex = /(?<=\$/)[0-9.]+/;

// $를 찾되 $뒤에 있는 문자열을 찾고, $는 소비되지 않음
"XTC99: $69.96".match(regex)[0]; // "69.96"
```

#### 전방, 후방탐색 함께 사용하기

```javascript
// 제목만 반환하기
var str = "<head>\
<title>Everybody coding</title>\
</head>";

// (후방탐색)제목(전방탐색)
var regex = /(?<=\<[tT][iI][tT][lL][eE]\>).*(?=\<\/[tT][iI][tT][lL][eE]\>)/;

str.match(regex)[0]; // "Everybody coding"
```

- 일반적인 전방, 후방 탐색은 **긍정형 탐색**으로,\
찾고자하는 부분의 앞 뒤를 특별히 지정해 일치하는 텍스트를 찾기 위한 방법
- 반면 **부정형 탐색**은 지정한 패턴과 일치하지 않는 텍스트를 찾기 위한 방법
- 연산 그대로 = 대신 !를 사용하여 부정형으로 탐색할 수 있음\
긍정 후방 `?<=` → 부정 후방 `?<!`\
긍정 전방 `?=` → 부정 전방 `?!`
- <참고>[정규 표현식 전후방탐색](https://blog.hexabrain.net/205)


# 2. Form 데이터 전송

### form 태그를 사용한 html

- 사용자 입력을 받기 위해 사용되는 태그
- 여러 input type 태그 등을 이용하여 다양한 입력을 받을 수 있음
- div 태그 등을 이용해 감싸서 각 그룹 영역을 표시
- 정해진 형식없이 개인이 여러가지 형태로 form을 구성할 수 있음

```html
<form action="/join" method="post">
    <div class="inputWrap">
        <div class="email">
            <span> Email </span> <input type="text" name="email"><br/>
        </div>
        <div class="password">
            <span> Password </span> <input type="password" name="password"><br/>
        </div>
    </div>
    <input class="sendbtn" type="submit">
</form>
```

- type이 `submit`인 엘리먼트를 클릭하거나 엔터를 입력할 경우\
action 속성에서 지정한 URL으로 이동
- method 속성으로 GET, POST 등 메소드 방식을 지정할 수 있음
- <참고> [다양한 HTML 폼 관련 태그](https://www.w3schools.com/html/html_forms.asp)

### 메소드 방식

- form으로 전송하는 데이터의 경우 `POST` 방식으로 전송하는 것이 일반적으로 권장
- `GET`은 서버로 무엇인가를 요청할 경우 default로 전송되는 방식
- action 속성에 정의된 URL으로 데이터를 보내고,\
서버에서는 이 요청을 받아서 라우팅 _routing_ 작업을 처리해야 함
- <참고> [HTTP method GET vs POST](https://www.w3schools.com/tags/ref_httpmethods.asp)
