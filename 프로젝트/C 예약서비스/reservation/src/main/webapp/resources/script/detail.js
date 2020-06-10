var app = {
	res: '',
	setRes(res) {
		this.res = res;
	},
	init() {
		var urlParams = new URLSearchParams(window.location.search);
		var req = new XMLHttpRequest();
		req.open('GET', '/reservation/api/products/' + urlParams.get('id'));
		req.onload = function() {
			var jsonRes = JSON.parse(req.response);
			window.app.setRes(jsonRes.displayInfoResponse);
			window.app.makeTopArea();
			window.app.makeMiddleArea();
			window.app.makeBottomArea();
		}
		req.send();
	},
	// 상단 영역
	makeTopArea() {
		this.setProductImage();
		this.setProductContent();
		this.setToggle();
		this.setEvent();
		this.manageImageSlider();
	},
	setProductImage() {
		var images = this.res.productImage;
		if (images.length == 1) {
			this.hideBtn();
		}
		this.setNumOff(images.length);
		var ul = document.querySelector('.group_visual ul.visual_img');
		var template = document.querySelector('#image-list').innerHTML;
		var html = '';
		for (var i = 0; i < images.length; i++) {
			html += template.replace('{fileName}', images[i].fileName)
							.replace('{saveFileName}', images[i].saveFileName)
							.replace('{productDescription}', this.res.displayInfo.productDescription);
		}
		ul.innerHTML = html;
	},
	setNumOff(length) {
		document.querySelector('.figure_pagination > span.num.off > span').innerText = length;
	},
	hideBtn() {
		$('.group_visual .prev').css('display', 'none');
		$('.group_visual .nxt').css('display', 'none');
	},
	setProductContent() {
		var content = this.res.displayInfo.productContent;
		document.querySelector('.store_details .dsc').innerText = content;
		document.querySelector('.detail_info .in_dsc').innerText = content; 
	},
	setToggle() {
		$('.bk_more._open').click(function() {
			$('.store_details').removeClass('close3');
			$('.bk_more._open').css('display', 'none');
			$('.bk_more._close').css('display', 'block');
		});
		$('.bk_more._close').click(function() {
			$('.store_details').addClass('close3');
			$('.bk_more._open').css('display', 'block');
			$('.bk_more._close').css('display', 'none');
		});
	},
	setEvent() {
		var p = document.querySelector('.section_event .event_info .in_dsc');
		
		var discountFlag = false;
		this.res.productPrice.forEach((price) => {
			if (price.discountRate !== 0) {
				discountFlag = true;
			}
		})
		if (!discountFlag) {
			document.querySelector('.section_event').style.display = 'none';
			return;
		}
		
		var discountInfo = this.res.productPrice.reduce((prev, cur) => {
			if (cur.discountRate == 0) return prev + '';
			return prev + cur.priceTypeName + '석 ' + cur.discountRate + '%, '
		}, '[네이버예약 특별할인]<br>');
		p.innerHTML = discountInfo.slice(0, -2).concat(' ', '할인');
	},
	manageImageSlider() {
		var ul = document.querySelector('.group_visual .visual_img');
		var ulOffset = ul.offsetWidth;
		var imageCount = ul.children.length;
		
		if (ul.children.length == 1) {
			ul.style.transform = 'translate(414px)';
			return;
		}
		
		var flag = false;
		$('.group_visual .prev').click(() => {
			moveSlide();
			changePage();
		});
		$('.group_visual .nxt').click(() => { 
			moveSlide();
			changePage();
		});
		
		function moveSlide(idx) {
			if (flag) {
				ul.style.transform = 'translate(0px)';
			} else {
				ul.style.transform = 'translate(' + ulOffset + 'px)';
			}
			flag = !flag;
		}
		
		function changePage(idx) {
			var num = document.querySelector('.pagination span.num');
			if (flag) {
				num.innerText = 2;
			} else {
				num.innerText = 1;				
			}
		}
	},
	clickBookingBtn() {
		$('.section_btn button.bk_btn').click(() => {
			window.location.href = '/reservation/reserve?id=' + this.res.displayInfo.displayInfoId;
		});
	},
	// 중간 영역
	makeMiddleArea() {
		if (this.res.averageScore == 0) {
			document.querySelector('.btn_review_more').style.display = 'none';
			return;
		}
		this.setGradeArea();
		this.showComments();
		this.viewAll();
	},
	setGradeArea() {
		var grade = this.res.averageScore.toFixed(1);
		document.querySelector('.short_review_area em.graph_value').style.width = (grade * 20) + '%';
		document.querySelector('.short_review_area .text_value span').innerText = grade;
		document.querySelector('.short_review_area em.green').innerText = this.res.comments.length + '건';
	},
	showComments() {
		var context = [];
		var comments = this.res.comments.slice(0, 3);
		for (var i = 0; i < comments.length; i++) {
			var commentImage, commentImageCount;
			if (comments[i].userCommentImage === null) {
				commentImage = false;
				commentImageCount = 0;
			} else {
				commentImage = comments[i].userCommentImage.saveFileName;
				commentImageCount = 1;
			}
			var obj = {
				"productDescription": this.res.displayInfo.productDescription,
				"commentImage": commentImage,
				"commentImageCount": commentImageCount,
				"comment": comments[i].comment,
				"score": comments[i].score,
				"date": comments[i].reservationDate.match(/(\d+)-(\d+)-(\d+)/g)[0].split('-').join('.'),
				"id": comments[i].email.slice(0, 4).concat('', '****')
			}
			context.push(obj);
		}
		var template = document.querySelector('#comment-list').innerText;
		var bindTemplate = Handlebars.compile(template);
		var bindHTML = context.reduce((prev, current) => {
			return prev + bindTemplate(current);
		}, '');
		var ul = document.querySelector('.section_review_list .list_short_review').innerHTML = bindHTML;
	},
	viewAll() {
		$('.section_review_list .btn_review_more').click(() => {
		  var url = '/reservation/review?id=' + this.res.displayInfo.displayInfoId;
			window.location.href = url;
		});
	},
	// 하단 영역 
	makeBottomArea() {
		this.toggleTab();
		this.setStoreInfo();
	},
	toggleTab() {
		$('.section_info_tab .info_tab_lst li').click(function() {
			var itemClass = Array.from(this.classList);
			if (itemClass.includes('_detail')) {
				if (itemClass.includes('active'))
					$('.section_info_tab .info_tab_lst li').addClass('active');
				$('.detail_area_wrap').removeClass('hide');
				$('.detail_location').addClass('hide');
				$('.section_info_tab .info_tab_lst ._detail a').addClass('active');
				$('.section_info_tab .info_tab_lst ._path').removeClass('active');
				$('.section_info_tab .info_tab_lst ._path a').removeClass('active');
			} else if (itemClass.includes('_path')) {
				if (itemClass.includes('active'))
					$('.section_info_tab .info_tab_lst li').addClass('active');
				$('.detail_area_wrap').addClass('hide');
				$('.detail_location').removeClass('hide');
				$('.section_info_tab .info_tab_lst ._path a').addClass('active');
				$('.section_info_tab .info_tab_lst ._detail').removeClass('active');
				$('.section_info_tab .info_tab_lst ._detail a').removeClass('active');
			}
		});
	},
	setStoreInfo() {
		var template = document.querySelector('#store-info').innerHTML;
		var div = document.querySelector('.detail_location .box_store_info');
		div.innerHTML = template.replace('{productDescription}', this.res.displayInfo.productDescription)
								.replace('{saveFileName}', this.res.displayInfoImage.saveFileName)
								.replace('{placeLot}', this.res.displayInfo.placeLot)
								.replace('{placeStreet}', this.res.displayInfo.placeStreet)
								.replace('{placeName}', this.res.displayInfo.placeName)
								.replace('{telephone}', this.res.displayInfo.telephone)
								.replace('{telephone}', this.res.displayInfo.telephone)
								.replace('{telephone}', this.res.displayInfo.telephone);
		if (this.res.displayInfo.telephone === '') {
			document.querySelector('.lst_store_info_wrap').style.display = 'none';
		}
	}
}

document.addEventListener('DOMContentLoaded', () => {
	app.init();
});