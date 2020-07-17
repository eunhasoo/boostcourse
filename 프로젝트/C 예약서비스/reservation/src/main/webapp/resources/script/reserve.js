var reserveApp = {
    res : '',
    classification: {
      'A': '성인',
      'Y': '청소년',
      'B': '유아',
      'S': '셋트',
      'D': '장애인',
      'C': '지역주민',
      'E': '얼리어버드',
      'V': 'VIP석',
      'R': 'R석',
      'B2': 'B석',
      'S': 'S석',
      'D': '평일'
    },
    setRes(res) {
      this.res = res;
    },
    init() {
      const urlParams = new URLSearchParams(window.location.search);
      const req = new XMLHttpRequest();
      req.open('GET', '/reservation/api/products/' + urlParams.get('id'));
      req.onload = function() {
          const jsonRes = JSON.parse(req.response);
          window.reserveApp.setRes(jsonRes.displayInfoResponse);
          window.reserveApp.setTopArea();
          window.reserveApp.setMidArea();
          window.reserveApp.setBotArea();
      }
      req.send();
    },
    // 상단 구역 (상품 설명)
    setTopArea() {
      this.backBtn();
      this.makeImage();
      this.makeProductInfo();
    },
    backBtn() {
      document.querySelector('.ct_wrap .btn_back').addEventListener('click', function() {
        var id = new URLSearchParams(window.location.search).get('id');
        window.location.href = '/reservation/detail?id=' + id;
      })
    },
    makeImage() {
      var item = document.querySelector('.group_visual .visual_img .item');
      item.firstElementChild.src = '/reservation/api/image/' + this.res.productImage[0].type + '/' + this.res.productImage[0].productId;
      item.style.transition = '';
      item.style.transform = 'translate(0px)';
      document.querySelector('.preview_txt_dsc').innerText = this.res.displayInfo.openingHours;
    },
    makeProductInfo() {
      var template = document.querySelector('#product-info').innerHTML;
      var html = '';
      var priceClassify = '';
      for (var i = 0; i < this.res.productPrice.length; i++) {
        priceClassify += this.classification[this.res.productPrice[i].priceTypeName] + ' ' 
                          + this.res.productPrice[i].price + '원 / ';
      }
      priceClassify = priceClassify.slice(0, -3);
      html = template.replace('{placeName}', this.res.displayInfo.placeName)
                     .replace('{openingHours}', this.res.displayInfo.openingHours.replace(/\n/g,'<br>'))
                     .replace('{productPrice}', priceClassify);
      document.querySelector('.section_store_details').innerHTML = html;
    },
    // 중간 구역 (예매 수량)
    setMidArea() {
      this.makeTicketBody();
      this.changeQty();
    },
    makeTicketBody() {
      var template = document.querySelector('#price-info').innerHTML;
      var html = '';
      for(var i = 0; i < this.res.productPrice.length; i++) {
        var formattedPrice = (this.res.productPrice[i].price + '').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
        var type = this.res.productPrice[i].priceTypeName;
        if (this.res.displayInfo.categoryId == 2 && type === 'B') {
          type = 'B2';
        }
        html += template.replace('{priceTypeName}', this.classification[type])
                        .replace('{price}', formattedPrice);
        if (this.res.productPrice[i].discountRate != 0) {
          html += '<em class="product_dsc">' + formattedPrice + '원 (' 
                    + this.res.productPrice[i].discountRate + '% 할인가)' + '</em>'
        }
        html += '</div> </div>';
      }
      document.querySelector('.section_booking_ticket .ticket_body').innerHTML = html;
    },
    changeQty() {
      var minusBoxes = document.querySelectorAll('.ico_minus3');
      var plusBoxes = document.querySelectorAll('.ico_plus3');
      
      minusBoxes.forEach(box => {
        box.addEventListener('click', function(e) {
          if (e.target.classList.contains('disabled')) return;
          
          // 버튼 하이라이트 관리
          e.target.nextElementSibling.value = parseInt(e.target.nextElementSibling.value) - 1;
          if (e.target.nextElementSibling.value === "0") {
            e.target.classList.add('disabled');
            e.target.nextElementSibling.classList.add('disabled');
          } 
          
          // 총 금액 관리
          var money = e.target.parentElement.parentElement.parentElement.children[1].children[1]
                      .firstElementChild.innerText.split(',').join('');
          var moneyTimesCount = parseInt(e.target.nextElementSibling.value) * parseInt(money);
          if (moneyTimesCount > 0) {
            e.target.parentElement.nextElementSibling.classList.add('on_color');
          } else {
            e.target.parentElement.nextElementSibling.classList.remove('on_color');
          }
          var totalPrice = e.target.parentElement.nextElementSibling.children[0];
          totalPrice.innerText = (moneyTimesCount + '').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
          
          this.changeTotalQty(-1);
        }.bind(this));
      });
      
      plusBoxes.forEach(box => {
          box.addEventListener('click', function(e) {
            if (e.target.classList.contains('disabled')) return;

            // 버튼 하이라이트 관리
            e.target.previousElementSibling.value = parseInt(e.target.previousElementSibling.value) + 1;
            if (e.target.previousElementSibling.value >= "1") {
              e.target.previousElementSibling.previousElementSibling.classList.remove('disabled');
              e.target.previousElementSibling.classList.remove('disabled');
            }
            if (e.target.previousElementSibling.value == "99") {
              e.target.classList.add('disabled');
            }

            // 총 금액 관리
            var money = e.target.parentElement.parentElement.parentElement.children[1].children[1]
                        .firstElementChild.innerText.split(',').join('');
            var moneyTimesCount = parseInt(e.target.previousElementSibling.value) * parseInt(money);
            var totalPrice = e.target.parentElement.nextElementSibling.children[0];
            totalPrice.innerText = (moneyTimesCount + '').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');

            if (moneyTimesCount > 0) {
              e.target.parentElement.nextElementSibling.classList.add('on_color');
            } else {
              e.target.parentElement.nextElementSibling.classList.remove('on_color');
            }

            this.changeTotalQty(1);
        }.bind(this));
      });
    },
    // 하단 구역 (예매자 정보 확인 및 예매 요청 전송)
    setBotArea() {
      this.showFullTerms();
      this.checkAgreeBtn();
      this.validateOnChange();
      this.addreserveBtnListener();
    },
    showFullTerms() {
      var agmBtns = document.querySelectorAll('.section_booking_agreement .btn_agreement');
      agmBtns.forEach(btn => {
        btn.addEventListener('click', function(e) {
          if (e.target.tagName === 'SPAN' || e.target.tagName === 'I') {
            if (!e.target.parentElement.parentElement.classList.contains('open')) {
              e.target.parentElement.parentElement.classList.add('open');
            } else {
              e.target.parentElement.parentElement.classList.remove('open');
            }
          }
          if (e.target.tagName === 'A') {
            if (!e.target.parentElement.classList.contains('open')) {
              e.target.parentElement.classList.add('open');
            } else {
              e.target.parentElement.classList.remove('open');
            }
          }
        });
      })
    },
    changeTotalQty(value) {
      var totalCount = document.querySelector('.inline_form.last #totalCount');
      totalCount.innerText = parseInt(totalCount.innerText) + value;
    },
    checkAgreeBtn(e) {
      document.querySelector('#chk3').addEventListener('click', function() {
        if (this.checked) {
          document.querySelector('.bk_btn_wrap').classList.remove('disable');
          document.querySelector('.section_booking_agreement').style.backgroundColor = '#fff';
        } else {
          document.querySelector('.bk_btn_wrap').classList.add('disable');
        }
      })
    },
    addreserveBtnListener() {
      document.querySelector('.box_bk_btn').addEventListener('click', function() {
        if (!document.querySelector('#chk3').checked) {
          document.querySelector('.section_booking_agreement').style.backgroundColor = 'antiquewhite';
          return;
        }
        
        var name = $('#name').val();
        var email = $('#email').val();
        var tel = $('#tel').val();
        var count = 0;
        var priceType = document.querySelectorAll('.count_control_input');
        priceType.forEach(p => {
          if (p.value === "0") {
            count++;
          }
        });
        
        if (count == priceType.length) {
          var a = setInterval(() => {
            $('.section_booking_ticket').css('background-color', 'antiquewhite');
          }, 700);
          var b = setInterval(() => {
            $('.section_booking_ticket').css('background-color', '#fff');
          }, 900);
          setTimeout(() => {
            clearInterval(a);
            clearInterval(b);
          }, 1800);
        } else if (!name) {
          $('.name_wrap .warning_msg').css('visibility', 'visible');
          setTimeout(() => {
            $('.name_wrap .warning_msg').css('visibility', 'hidden');
          }, 800);
        } else if (!/^01[016-9]-\d{3,4}-\d{4}$/.test(tel) || tel === '') {
          $('.tel_wrap .warning_msg').css('visibility', 'visible');
          setTimeout(() => {
            $('.tel_wrap .warning_msg').css('visibility', 'hidden');
          }, 800);
        } else if (!/^\w+@\w+\.\w+$/.test(email) || email === '') {
          $('.email_wrap .warning_msg').css('visibility', 'visible');
          setTimeout(() => {
            $('.email_wrap .warning_msg').css('visibility', 'hidden');
          }, 800);
        } else {
          this.sendRequest();
        }
      }.bind(this));
    },
    validateOnChange() {
      $('#tel').change(function() {
        var tel = $(this).val();
        if (!(/^01[016-9]-\d{3,4}-\d{4}$/.test(tel))) {
          $('.tel_wrap .warning_msg').css('visibility', 'visible');
          setTimeout(function() {
            $('.tel_wrap .warning_msg').css('visibility', 'hidden');
          }, 800)
          return false;
        } else {
          return true;
        }
      })
      
      $('#email').change(function() {
        var email = $(this).val();
        if (!(/^\w+@\w+\.\w+$/.test(email))) {
          $('.email_wrap .warning_msg').css('visibility', 'visible');
          setTimeout(function() {
            $('.email_wrap .warning_msg').css('visibility', 'hidden');
          }.bind(this), 800)
          return false;
        } else {
          return true;
        }
      })
    },
    sendRequest() {
      var date = $('.inline_form.last .inline_txt').text().slice(0, -6).split('.');
      var now = new Date();
      var reserveDate = new Date(date[0], date[1], date[2], now.getHours(), now.getMinutes(), now.getSeconds());
      var reservationDate = `${
          (reserveDate.getFullYear()).toString().padStart(4, '0')}/${
          reserveDate.getMonth().toString().padStart(2, '0')}/${
          reserveDate.getDate().toString().padStart(2, '0')} ${
          reserveDate.getHours().toString().padStart(2, '0')}:${
          reserveDate.getMinutes().toString().padStart(2, '0')}:${
          reserveDate.getSeconds().toString().padStart(2, '0')}`;      
      var prices = [];
      var countInputs = document.querySelectorAll('.count_control_input');
      for (var i = 0; i < countInputs.length; i++) {
        if (countInputs[i].value !== '0') {
          prices.push({
            count: countInputs[i].value,
            productPriceId: this.res.productPrice[i].productPriceId,
            reservationInfoId: 0,
            reservationInfoPriceId: 0
          });
        }
      }
      var name = $('#name').val();
      var tel = $('#tel').val();
      var email = $('#email').val();
      var ReservationRequestDto = {
          displayInfoId : this.res.displayInfo.displayInfoId,
          productId : this.res.displayInfo.productId,
          prices: prices,
          reservationEmail : email,
          reservationName : name,
          reservationTelephone : tel,
          reservationYearMonthDay : reservationDate
      }
      
      $.ajax({
        url: '/reservation/api/reservations',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(ReservationRequestDto),
        success: function() {
          alert('예약이 완료되었습니다.');
          window.location.href = '/reservation';
        },
        error: function() {
          alert('예약에 실패했습니다.');
        }
      })
    },
}

document.addEventListener('DOMContentLoaded', () => {
  reserveApp.init();
});