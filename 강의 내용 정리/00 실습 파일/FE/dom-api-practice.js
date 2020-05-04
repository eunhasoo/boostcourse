/* 
 * 실습 1~4 html : https://jsbin.com/mebuha/1/edit?html,js,output
 * 실습 5 html : https://jsbin.com/redetul/1/edit?html,css,js,output
 * 실습 6 html : https://jsbin.com/ricopa/1/edit?html,css,js,output
 */ 

// 1. strawberry 아래에 새로운 과일을 하나 더 추가하시오. 추가 된 이후에는 다시 삭제하시오.
const ul = document.querySelector('ul');
const li = document.createElement('li');
const pineapple = document.createTextNode('pineapple');
li.appendChild(pineapple);
ul.appendChild(li); // 추가
ul.removeChild(li); // 제거


// 2. insertBefore 메서드를 사용해서, orange와  banana 사이에 새로운 과일을 추가하시오.
const banana = document.querySelector('li:nth-child(3)');
const ul = document.querySelector('ul');
const li = document.createElement('li');
const pineapple = document.createTextNode('pineapple');
li.appendChild(pineapple);
ul.insertBefore(li, banana);


// 3. insertAdjacentHTML메서드를 사용해서 orange와 banana 사이에 새로운 과일을 추가하시오.
const banana = document.querySelector('li:nth-child(3)');
banana.insertAdjacentHTML('beforebegin', '<li>pineapple</li>')


// 4. apple을 grape 와 strawberry 사이로 옮기시오.
const ul = document.querySelector('ul');
const apple = document.querySelector('li:nth-child(1)');
const strawberry = document.querySelector('li:nth-child(5)');
apple.remove();
ul.insertBefore(apple, strawberry);


// 5. class 가 'red'인 노드만 삭제하시오.
const red = document.querySelectorAll('.red');
const ul = document.querySelector('ul');
red.forEach(function(el) {
  ul.removeChild(el);
  // el.remove(); remove 메소드는 IE 호환성이 좋지않다
});


// 6. section 태그의 자손 중에 blue라는 클래스를 가지고 있는 노드가 있다면, 그 하위에 있는 h2 노드를 삭제하시오.
var blues = document.querySelectorAll('section .blue');
// Array.from 메소드* 이용해서 구현하기
var bluesArr = Array.from(blues); // *nodeList to array
bluesArr.forEach(function(v) {
  var section = v.closest('section');
  var h2 = section.querySelector('h2');
  section.removeChild(h2);
})
/* Array.from 메소드 사용하지 않고 구현하기
if (blues != null) {
  for (var i = 0; i < blues.length; i++) {
    var section = blues[i].closest('section');
    var h2 = section.querySelector('h2');
    section.removeChild(h2);
  }
}
*/