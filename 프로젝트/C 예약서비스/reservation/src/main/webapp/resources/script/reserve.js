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
      'V': 'VIP',
      'R': 'R석',
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
      item.firstElementChild.src = 'resources/' + this.res.productImage[0].saveFileName;
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
        var formattedPrice = (this.res.productPrice[i].price + '').replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
        html += template.replace('{priceTypeName}', this.classification[this.res.productPrice[i].priceTypeName])
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
          totalPrice.innerText = (moneyTimesCount + '').replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
          
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
            }
            if (e.target.previousElementSibling.value == "99") {
              e.target.classList.add('disabled');
            }

            // 총 금액 관리
            var money = e.target.parentElement.parentElement.parentElement.children[1].children[1]
            .firstElementChild.innerText.split(',').join('');
            var moneyTimesCount = parseInt(e.target.previousElementSibling.value) * parseInt(money);
            var totalPrice = e.target.parentElement.nextElementSibling.children[0];
            totalPrice.innerText = (moneyTimesCount + '').replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");

            if (moneyTimesCount > 0) {
              e.target.parentElement.nextElementSibling.classList.add('on_color');
            } else {
              e.target.parentElement.nextElementSibling.classList.remove('on_color');
            }

            
            this.changeTotalQty(1);
        }.bind(this));
      });
    },
    // 하단 구역 (예매자 정보)
    setBotArea() {
      this.showFullTerms();
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
    }
}

document.addEventListener('DOMContentLoaded', () => {
  reserveApp.init();
});