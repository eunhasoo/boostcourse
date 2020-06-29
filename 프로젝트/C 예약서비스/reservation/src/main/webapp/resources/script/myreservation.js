var myReservationApp = {
    data: '',
    init: function() {
      var req = new XMLHttpRequest();
      var qs = new URLSearchParams(window.location.search);
      req.open('GET', 'api/reservations?resrv_email=' + qs.get('resrv_email'));
      req.onload = function() {
        var _data = JSON.parse(req.response);
        this.data = _data;
        this.makeTopArea();
        this.makeBotArea();
      }.bind(this);
      req.send();
    },
    makeTopArea: function() {
      var today = this.formatDate();
      
    },
    formatDate :function() {
      var date = new Date();
      var year = date.getFullYear(),
          month = date.getMonth(),
          day = date.getDay();
      return [year, month, day].join('-');
    },
    makeBotArea: function() {
      
    }
}

document.addEventListener('DOMContentLoaded', function() {
  myReservationApp.init();
})