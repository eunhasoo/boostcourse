# 1. 생성자 패턴

### 일반적인 객체 생성

```javascript
var oName = {
  name : 'jisu',
  getName : function(){
    return this.name;
  }
}

var oName2 = {
  name : 'siwa',
  getName : function(){
    return this.name;
  }
}
```

- 같은 구조를 갖는 객체를 매번 새로운 객체 리터럴로 구현
- 코드 중복이 많고 번거롭다

### 생성자 패턴

```javascript
// 자바스크립트는 클래스, 생성자, 메소드를 모두 함수로 구현 가능
function Speaker(name, buyDate) {
  this.name = name;
  this.buyDate = buyDate;
  this.inform = function() { // 객체 내부 함수의 중복 발생
    console.log(this.name + '은 ' + this.buyDate + '에 구매하였습니다.');
  }
}

var s1 = new Speaker('JBL Flip 4', '2019-11-12');
var s2 = new Speaker('Boss Quietcomfort 35', '2017-06-14');
s1.inform(); // JBL Flip 4은 2019-11-12에 구매하였습니다.
s2.inform(); // Boss Quietcomfort 35은 2017-06-14에 구매하였습니다.
```

- 생성자를 이용한 객체 생성 방법을 사용하면 객체를 동적으로 쉽게 만들 수 있음
- 생성자 함수는 대문자로 시작
- `new` 키워드를 사용하면 객체 내부에서 마지막에 `return this`가 생략된 구조로 동작한다고 보면 됨
- <참고> [자바스크립트의 다양한 객체 생성 방법](https://poiemaweb.com/js-object-oriented-programming#22-%ED%94%84%EB%A1%9C%ED%86%A0%ED%83%80%EC%9E%85-%EA%B8%B0%EB%B0%98-%EC%96%B8%EC%96%B4), [자바스크립트 객체지향 프로그래밍](https://wayhome25.github.io/javascript/2017/02/19/js-oop-2/)

### Prototype

- 위 코드의 문제점은 같은 함수(inform)를 여러 객체가 중복으로 가진다는 것
- prototype을 사용하면 하나의 함수를 같은 메모리 공간에 둠으로써\
하나로 정의해 메모리를 효율적으로 사용할 수 있음
- 즉 하나의 공통된 생성자 함수로 만들어진 각 인스턴스가\
prototype이라는 같은 참조 객체를 바라보고 있는 것
- 개발자 도구 안에 `__proto__` 속성으로 정보를 들여다볼 수 있음
- 다른 객체 지향 언어의 상속과 비슷한 개념

```javascript
function Speaker(name, buyDate) {
  this.name = name;
  this.buyDate = buyDate;
}
Speaker.prototype.inform = function() {
    console.log(this.name + '은 ' + this.buyDate + '에 구매하였습니다.');
}

var s1 = new Speaker('JBL Flip 4', '2019-11-12');
var s2 = new Speaker('Boss Quietcomfort 35', '2017-06-14');
s1.inform(); // JBL Flip 4은 2019-11-12에 구매하였습니다.
s2.inform(); // Boss Quietcomfort 35은 2017-06-14에 구매하였습니다.
```

# 2. 생성자 패턴으로 Tab UI 만들기

```javascript
// index-proto.js 파일 (index.js 리팩토링)

function Tab(tabMenu) {
    this.tabMenu = tabMenu;
    this.registerEvent();
}

Tab.prototype = {
    registerEvent() {
        this.tabMenu.addEventListener('click', function(e) {
            this.sendAjax('./json.text', e.target.innerText)
        }.bind(this));
    },
    sendAjax(url, mName) {
        var oReq = new XMLHttpRequest();
        oReq.addEventListener("load", () => {
            data = JSON.parse(oReq.responseText);
            this.makeTemplate(data, mName);
        });
        oReq.open("GET", url);
        oReq.send();
    },
    makeTemplate(data, mName) {
        var tempHtml = document.querySelector('#template').innerHTML;
        var resultHtml = "";
        for (var i = 0; i < data.length; i++) {
            if (data[i].name === mName) {
                resultHtml += tempHtml.replace('{id}', data[i].id)
                                      .replace('{name}', data[i].name)
                                      .replace('{say}', data[i].say);
                break;
            }
        }
        document.querySelector('.content').innerHTML = resultHtml;
    }
}

const tabMenu = document.querySelector('.tab-menu');
var oTab = new Tab(tabMenu);
```

- 전체 코드는 실습 파일 폴더의 tabui 폴더 참고