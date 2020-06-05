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
			console.log(jsonRes);
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
	},
	setProductImage() {
		var images = this.res.productImage;
		if (images.length == 1) {
			this.hideBtn();
		}
		this.setImageNo(images.length);
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
	setImageNo(length) {
		var numOff = document.querySelector('.figure_pagination > span.num.off > span');
		numOff.innerText = length;
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
		if (this.res.productPrice.length == 1 && this.res.productPrice[0].discountRate == 0) {
			document.querySelector('.section_event').style.display = 'none';
			return;
		}
		var discountInfo = this.res.productPrice.reduce((prev, cur) => {
			if (cur.discountRate == 0) return prev + '';
			return prev + cur.priceTypeName + '석 ' + cur.discountRate + '%, '
		}, '[네이버예약 특별할인]<br>');
		p.innerHTML = discountInfo.slice(0, -2).concat(' ', '할인');
	},
	// 중간 영역
	makeMiddleArea() {
		
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