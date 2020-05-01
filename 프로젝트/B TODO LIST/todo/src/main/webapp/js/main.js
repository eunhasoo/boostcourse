const next = document.querySelector('.next');

function updateTodo(e) {
  const id = this.parentNode.parentNode.id;
  const type = next.parentNode.parentNode.previousElementSibling.innerText;
  var httpRequest = new XMLHttpRequest();
  httpRequest.onreadystatechange = checkStatus;
  httpRequest.open('GET', 'http://localhost:8090/todo/todotype?id='+id+'&type='+type);
  httpRequest.send();
}

function checkStatus() {
  if (httpRequest.status === 200) {
	  var httpRequest = new XMLHttpRequest();
	  console.log('success');
  }
}

next.addEventListener('click', updateTodo);