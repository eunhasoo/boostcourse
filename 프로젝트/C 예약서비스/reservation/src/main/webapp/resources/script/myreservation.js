var myReservationApp = {
    data: '',
    day: {
      0: '(일)',
      1: '(월)',
      2: '(화)',
      3: '(수)',
      4: '(목)',
      5: '(금)',
      6: '(토)'
    },
    init: function() {
      var req = new XMLHttpRequest();
      var qs = new URLSearchParams(window.location.search);
      req.open('GET', 'api/reservations?resrv_email=' + qs.get('resrv_email'));
      req.onload = function() {
        var _data = JSON.parse(req.response);
        this.data = _data;
        this.showMyReservations();
      }.bind(this);
      req.send();
    },
    showMyReservations: function() {
      this.setSummary();
      this.addCancelEventListener();
    },
    setSummary: function() {
      var allCount, confirmed, used, canceled;
      allCount = document.querySelector('.ct .my_summary .summary_board li:nth-child(1) .figure');
      confirmed = document.querySelector('.ct .my_summary .summary_board li:nth-child(2) .figure');
      used = document.querySelector('.ct .my_summary .summary_board li:nth-child(3) .figure');
      canceled = document.querySelector('.ct .my_summary .summary_board li:nth-child(4) .figure');
      allCount.innerText = this.data.size;
      confirmed.innerText = this.manageConfirmedItems();
      used.innerText = this.manageUsedItems();
      canceled.innerText = this.manageCancelItems();
      if (allCount != 0) {
        document.querySelector('.ct .section_my .err').remove();
      }
    },
    manageConfirmedItems: function() {
      var confirmedElement = document.querySelector('.wrap_mylist .card.confirmed');
      var template = document.querySelector('#confirmed-item').innerHTML;
      var confirmedCount = 0;
      var today = new Date();
      var html = this.data.reservations.reduce((prev, cur) => {
        var date = new Date(cur.reservationDate);
        if (!cur.cancelFlag && date >= today) {
          ++confirmedCount;
          return prev + template.replace('{{description}}', cur.displayInfo.productDescription)
                                .replace('{{reservationId}}', (cur.reservationInfoId + '').padStart(8, '0'))
                                .replace('{{reservationId}}', cur.reservationInfoId)
                                .replace('{{date}}', date.toLocaleDateString() + ' ' + this.day[date.getDay()])
                                .replace('{{place}}', cur.displayInfo.placeName)
                                .replace('{{total}}', (cur.totalPrice + '').replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'));
        } else return prev + '';
      }, '');
      if (confirmedCount > 0) {
        confirmedElement.innerHTML = confirmedElement.innerHTML + html;
      } else {
        document.querySelector('.wrap_mylist .card.confirmed').remove();
      }
      return confirmedCount;
    },
    manageUsedItems: function() {
      var usedElement = document.querySelector('.wrap_mylist .card.used');
      var template = document.querySelector('#used-item').innerHTML;
      var usedCount = 0;
      var today = new Date();
      var html = this.data.reservations.reduce((prev, cur) => {
        var date = new Date(cur.reservationDate);
        if (!cur.cancelFlag && date <= today) {
          ++usedCount;
          return prev + template.replace('{{description}}', cur.displayInfo.productDescription)
                                .replace('{{reservationId}}', (cur.reservationInfoId + '').padStart(8, '0'))
                                .replace('{{reservationId}}', cur.reservationInfoId)
                                .replace('{{date}}', date.toLocaleDateString() + ' ' + this.day[date.getDay()])
                                .replace('{{displayInfoId}}', cur.displayInfoId)
                                .replace('{{place}}', cur.displayInfo.placeName)
                                .replace('{{productId}}', cur.productId)
                                .replace('{{total}}', (cur.totalPrice + '').replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'));
        }  else return prev + '';
      }, '');
      if (usedCount > 0) {
        usedElement.innerHTML = usedElement.innerHTML + html;
      } else {
        document.querySelector('.wrap_mylist .card.used').remove();
      }
      return usedCount;
    },
    manageCancelItems: function() {
      var cancelElement = document.querySelector('.wrap_mylist .card.used.cancel');
      var template = document.querySelector('#canceled-item').innerHTML;
      var canceledCount = 0;
      var html = this.data.reservations.reduce((prev, cur) => {
        if (cur.cancelFlag) {
          canceledCount++;
          var date = new Date(cur.reservationDate);
          return prev + template.replace('{{description}}', cur.displayInfo.productDescription)
                                .replace('{{reservationId}}', (cur.reservationInfoId + '').padStart(8, '0'))
                                .replace('{{date}}', date.toLocaleDateString() + ' ' + this.day[date.getDay()])
                                .replace('{{place}}', cur.displayInfo.placeName)
                                .replace('{{total}}', (cur.totalPrice + '').replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'));
        } else return prev + '';
      }, ' ');
      if (canceledCount > 0) {
        cancelElement.innerHTML = cancelElement.innerHTML + html;
      } else {
        document.querySelector('.wrap_mylist .card.used.cancel').remove();
      }
      return canceledCount;
    },
    addCancelEventListener: function() {
      var btns = document.querySelectorAll('.booking_cancel .btn');
      btns.forEach((btn) => {
        if (btn.firstElementChild.textContent === '취소') {
          btn.addEventListener('click', this.cancelReservation);
        } else if (btn.firstElementChild.textContent === '예매자 리뷰 남기기') {
          btn.addEventListener('click', this.goWriteReview);
        }
      });
    },
    cancelReservation: function(e) {
      var id = e.target.id;
      var req = new XMLHttpRequest();
      req.open('PUT', '/reservation/api/reservations/' + id);
      req.onload = function() {
        alert('예약이 취소되었습니다.');
        location.reload();
      }
      req.onerror = function() {
        alert('예약 취소에 실패하였습니다.');
      }
      req.send();
    },
    goWriteReview: function(e) {
      var reservationInfoId, productId, displayInfoId, title;
      if (e.target.tagName === 'SPAN') {
        reservationInfoId = e.target.parentElement.id;
        productId = e.target.parentElement.parentElement.id;
        title = e.target.parentElement.parentElement.parentElement.children[1].textContent.trim();
        displayInfoId = e.target.id;
      } else {
        reservationInfoId = e.target.id;
        productId = e.target.parentElement.id;
        title = e.target.parentElement.parentElement.children[1].textContent.trim();
        displayInfoId = e.target.firstElementChild.id;
      }
      var url = '/reservation/reviewWrite?rid=' + reservationInfoId + '&pid=' 
                + productId + '&did=' + displayInfoId + '&tit=' + title;
      window.location.href = url;
    }
}

document.addEventListener('DOMContentLoaded', function() {
  myReservationApp.init();
})