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
			window.app.setRes(JSON.parse(req.response));
			window.app.makeTopArea();
			window.app.makeMiddleArea();
			window.app.makeBottomArea();
		}
		req.send();
	},
	// 상단 영역
	makeTopArea() {
		this.setProductTitle();
		this.setProductImage();
		this.setProductContent();
		this.setToggle();
		this.setEvent();
	},
	setProductTitle() {
		var container = document.querySelector('.container_visual');
		container.addChild
	},
	setProductImage() {
		
	},
	setProductContent() {
		var dsc = document.querySelector('.store_details .dsc');
		dsc.innerText = this.res.displayInfoResponse.displayInfo.productContent;
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
		
	},
	// 중간 영역
	makeMiddleArea() {
		
	},
	// 하단 영역 
	makeBottomArea() {
		
	}
}

document.addEventListener('DOMContentLoaded', () => {
	app.init();
});