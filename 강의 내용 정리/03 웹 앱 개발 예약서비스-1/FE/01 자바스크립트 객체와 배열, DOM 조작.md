# 1. 자바스크립트 배열과 객체
### 0) 배열 vs 객체
- **배열**: `순서`(=index)가 있는 리스트
- **객체**: `key` : `value` 구조의 자료구조

### 1) 배열
```javascript
var a = []; // 빈 배열 선언
var b = [ 1, 2, "hello", null, true, [] ]; // 배열 안에는 모든 타입이 다 들어갈 수 있다.
a[1000] = 4;
console.log(a.length); // 1001
console.log(a[300]); // undefined

// 원소 추가는 인덱스 삽입보다는 push 메서드가 주로 사용된다 
var c = [1, 2];
c.push(3);
console.log(c.indexOf(2)); // 1

var origin = [1, 2, 3, 4];
var changed = origin.concat(5, 6); // 새 배열을 반환
console.log(origin); // [1, 2, 3, 4] : 기존 배열은 바뀌지 않는다
console.log(changed); // [1, 2, 3, 4, 5, 6]
var result2 = [ ...origin, 5, 6 ]; // Spread Operator(ES6) : concat과 동일한 동작
console.log(origin.join('')); // "1234" : 원소를 하나의 문자열로 합친다

// 배열 탐색: forEach()
origin.forEach(function(value, index, object) {
  console.log(value); 
});

// 배열 탐색: map()
// 배열의 원소를 돌면서 값을 탐색/작업한 뒤에 새 배열로 만들어서 반환한다
var mapped = origin.map(function (value, index) {
  return value * 2;
});
console.log(mapped); // [2, 4, 6, 8]

// 배열 탐색: filter()
function isEven(value) {
  return value % 2 === 0
}
var filtered = [10, 5, 7, 6, 2].filter(isEven);
console.log(filtered); // [10, 6, 2]

// 배열 요소 제거
var fruits = ["Banana", "Orange", "Apple", "Mango"];
var removedItem = fruits.splice(2, 1, "Lemon", "Kiwi"); // 제거된 원소들을 갖는 배열을 반환
// 배열의 index(2)부터 how-many(1)개 지우고 index 위치에 item...("Lemon", "Kiwi")을 추가 (how-many와 item은 옵션)
console.log(fruits); // ["Banana", "Orange", "Lemon", "Kiwi", "Mango"]
console.log(removedItem); // ["Apple"]


```
- <참고> [MDN Array Reference](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array)

### 2) 객체
```javascript
// 객체 생성
var obj = { name: "crong", age: 20 }
console.log(obj["name"]); // crong : 
// obj[name]은 error

// 객체 안에 객체를 넣을 수도 있다
var info = {
  name: 'today',
  date: [
    {
      year: 2020,
      month: 5,
      day: 4
    }
  ]
}
console.log(info["date"][0].year); // 2020
console.log(info.date[0].month); // 5

var obj = {
  name: 'Eunha',
  country : 'Korea'
};

// 객체의 탐색은 key값을 이용한다
// for-in
for(key in obj) {
  console.log(obj[key]);
}

// Object.keys() : 객체의 key값으로 이루어진 배열을 반환
console.log(Object.keys(obj)); // ["name", "country"]
Object.keys(obj).forEach(function(value) {
  console.log(obj[value]);
});
```
- 자바스크립트 객체의 형식을 본따 만든 것이 서버-클라이언트간 데이터 교환을 위해 사용하는 `JSON` 형식이다

# 2. DOM API 활용
### 1) DOM 조작 API
- 간결하고 편리하게 구현할 수 있는 다양한 라이브러리들이 존재하지만,\
직접 DOM API를 사용해 조작하는 것이 대체로 더 빠르다
- 또한 문제 해결에 있어서도 내부 구조를 알고나면 더 빠르게 디버깅할 수 있게 된다
- <참고> [Document 객체 조작](https://www.w3schools.com/jsref/dom_obj_document.asp)
- <참고> [Element 객체 조작](https://www.w3schools.com/jsref/dom_obj_all.asp)

### 2) DOM 속성과 메소드
#### 유용한 DOM 엘리먼트 속성
- `tagName` : 엘리먼트의 태그명 반환
- `textContent` : 노드의 텍스트 내용을 설정하거나 반환
- `nodeType` : 노드의 유형을 숫자로 반환

#### DOM 탐색 속성
- `childNodes` : 엘리먼트의 자식 노드의 nodeList 반환
- `firstChild` : 엘리먼트의 첫 번째 자식 노드 반환 (개행문자 _textNode_ 인식)
- `firstElementChild` : 첫 번째 자식 '엘리먼트'를 반환
- `parentElement` : 엘리먼트의 부모 엘리먼트 반환
- `nextSibling` : 동일한 노트 tree-level에서 다음 노드를 반환
- `nextElementSibling` : 동일한 노트 tree-level에서 다음 엘리먼트를 반환

#### DOM 조작 메소드
- `removeChild()` : 엘리먼트의 자식 노드 제거
- `appendChild()` : 엘리먼트의 마지막 자식 노드에 노드 추가
- `insertBefore()` : 기존 자식 노드 앞에 새 자식 노드를 추가
- `cloneNode()` : 노드를 복제
- `replaceChild()` : 엘리먼트의 자식 노드를 바꿈
- `closest()` : 상위에서 가장 가까운 엘리먼트를 반환

#### HTML을 문자열로 처리해주는 DOM 속성/메소드
- `innerText` : 지정된 노드와 모든 자손의 텍스트 내용을 설정하거나 반환
- `innerHTML` : 지정된 엘리먼트의 내부 html을 설정하거나 반환
- `insertAdjacentHTML()` : HTML으로 텍스트를 지정된 위치에 삽입
