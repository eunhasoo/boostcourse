let id;
let type;
let card;
let btn;
let httpRequest;

function updateTodo(e) {
  btn = e.target;
  card = e.target.parentNode.parentNode;
  id = e.target.parentNode.parentNode.id;
  type = e.target.parentNode.parentNode.parentNode.firstElementChild.innerText;
  var url = 'http://localhost:8090/todo/todotype?id=' + id + '&type=' + type;
  httpRequest = new XMLHttpRequest();
  httpRequest.onreadystatechange = checkStatus;
  httpRequest.open('GET', url);
  httpRequest.send();
}

function checkStatus() {
  if (httpRequest.readyState === 4) {
	  var nextSec = card.parentElement.nextElementSibling;
	  if (type === 'DOING') {
		  btn.remove();
	  }
	  nextSec.appendChild(card);
	  console.log('success');
  }
}

// 동적으로 데이터를 받아오는 경우 eventlistener를 이렇게 적용해줘야 한다
document.addEventListener('click', function(e){
    if (e.target && e.target.className== 'next'){
    	updateTodo(e);
    	return;
     }
 });
