document.addEventListener('DOMContentLoaded', onLoad);

function onLoad() {
  goToTop();
  reservationListener();
}

function goToTop() {
  document.querySelector('footer .gototop').addEventListener('click', () => {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
    });
}

function reservationListener() {
  var viewReservation = document.querySelector('span.viewReservation');
  if (viewReservation && viewReservation.innerText !== '예약확인') {
    if (viewReservation.parentElement.tagName === 'A') {
      viewReservation.parentElement.addEventListener('click', () => {
        window.location.href = '/reservation/myreservation?resrv_email=' + viewReservation.innerText;
      });
    }
  } else if (viewReservation) {
    viewReservation.parentElement.addEventListener('click', () => {
      window.location.href = 'login';
    });
  }
}  
