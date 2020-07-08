var reviewWriteApp = {
    init: function() {
      this.showTitle();
      this.controlScore();
      this.startWriting();
      this.watchCharCount();
      this.manageFile();
      this.clickSendBtn();
    },
    showTitle: function() {
      const title = decodeURIComponent(window.location.href.split('tit=')[1]);
      document.querySelector('.ct .top_title.review_header .title').innerText = title;
    },
    controlScore: function() {
      const ratingPoint = document.querySelector('.review_rating.rating_point');
      ratingPoint.addEventListener('click', function(e) {
        e.preventDefault();
        if (e.target.type !== 'checkbox') {
          return;
        }
        const stars = document.querySelector('.review_rating.rating_point .rating').children;
        const value = parseInt(e.target.value);
        for (var i = 2; i <= 6; i++) {
          if (i <= value + 1) {
            stars['rating' + i].classList.add('checked');
          } else {
            stars['rating' + i].classList.remove('checked');
          }
        }
        const rank = document.querySelector('.review_rating.rating_point .star_rank')
        rank.classList.remove('gray_star');
        rank.innerText = value;
      })
    },
    startWriting: function() {
      document.querySelector('.ct .review_contents .review_write_info').addEventListener('click', () => {
        document.querySelector('.ct .review_contents .review_write_info').style.visibility = 'hidden';
        document.querySelector('.ct .review_contents .review_textarea').focus();
      })
    },
    watchCharCount: function() {
      const textArea = document.querySelector('.ct .review_contents .review_textarea');
      textArea.addEventListener('keyup', function() {
        document.querySelector('.ct .review_write_footer_wrap .guide_review span').innerText = this.value.length;
      });
    },
    manageFile: function() {
      document.querySelector('#reviewImageFileOpenInput').addEventListener('change', function(e) {
        const image = e.target.files[0];
        const checker = (['image/jpeg', 'image/png', 'image/jpg'].indexOf(image.type) > -1);
        if (!checker) {
          alert('파일 확장자가 png/jpeg/jpg 중 하나인지 다시 한 번 확인해주세요.');
          return;
        }
        const thumb = document.querySelector('.review_photos .item_thumb');
        thumb.src = window.URL.createObjectURL(image);
        thumb.parentElement.style.display = 'inline-block';
      });
    },
    checkAllDone: function() {
      const score = document.querySelector('.review_rating.rating_point .star_rank').innerText;
      const charCount = document.querySelector('.ct .review_write_footer_wrap .guide_review span').innerText;
      if (score === '0') {
        alert('별점을 선택해주세요.');
      } else if (charCount < '5') {
        alert('최소 5자 이상으로 입력해주세요.');
      }
      return (score !== '0' && charCount >= '5');
    },
    clickSendBtn: function() {
      document.querySelector('.box_bk_btn .bk_btn').addEventListener('click', function() {
        if (!this.checkAllDone()) {
          return;
        }
        
        const urlParams = new URLSearchParams(window.location.search);
        const productId = parseInt(urlParams.get('pid'));
        const reservationInfoId = parseInt(urlParams.get('rid'));
        const score = parseInt(document.querySelector('.review_rating.rating_point .star_rank').innerText);
        const comment = document.querySelector('.ct .review_contents .review_textarea').value;
        const file = document.querySelector('#reviewImageFileOpenInput').value;
        const commentRequestDto = {
            productId,
            reservationInfoId,
            score,
            comment,
            file
        };
        $.ajax({
          url: '/reservation/api/reservations/' + reservationInfoId + '/comments',
          type: 'POST',
          contentType: "application/json; charset=utf-8",
          data: JSON.stringify(commentRequestDto),
          success: function() {
            alert('한줄평 작성이 완료되었습니다.');
            window.location.href = '/reservation/review?id' + productId;
          },
          error: function() {
            alert('오류가 발생했습니다. 다시 시도해주세요.');
          }
        })

      }.bind(this));
    }
}

document.addEventListener('DOMContentLoaded', function() {
  reviewWriteApp.init();
})