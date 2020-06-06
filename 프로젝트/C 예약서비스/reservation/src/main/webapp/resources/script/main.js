function getItems(categoryId, start) {
	var request = new XMLHttpRequest();
	request.open('GET', '/reservation/api/products' + '?categoryId=' + categoryId + '&start=' + start);
	request.onload = function() {
		var res = JSON.parse(request.response);

		var leftSection = document.querySelector('.wrap_event_box').firstElementChild;
		var rightSection = leftSection.nextElementSibling;
		
		if (start == 0) {
			leftSection.innerText = '';
			rightSection.innerText = '';
		}
		
		var totalCount = res.totalCount;
		updateCount(totalCount);
		
		var itemTemplate = document.querySelector('#itemList').innerHTML;
		var leftTemplate = '';
		var rightTemplate = '';
		var itemList = res.items;
		for (var i = 0; i < itemList.length; i++) {
			if (i % 2 == 0) {
				rightTemplate = itemTemplate.replace('{id}', itemList[i].displayInfoId)
							    .replace('{productContent}', itemList[i].productContent)
							    .replace('{productImageUrl}', itemList[i].productImageUrl)
							    .replace('{placeName}', itemList[i].placeName)
							    .replace('{productDescription}', itemList[i].productDescription)
							    .replace('{productDescription}', itemList[i].productDescription);
				rightSection.innerHTML += rightTemplate;
			} else {
				leftTemplate = itemTemplate.replace('{id}', itemList[i].displayInfoId)
							   .replace('{productContent}', itemList[i].productContent)
							   .replace('{productImageUrl}', itemList[i].productImageUrl)
							   .replace('{placeName}', itemList[i].placeName)
							   .replace('{productDescription}', itemList[i].productDescription)
							   .replace('{productDescription}', itemList[i].productDescription);
				leftSection.innerHTML += leftTemplate;
			}
		}
		
		var showing = document.querySelectorAll('.lst_event_box .item').length;
		if (showing >= totalCount) {
			toggleMore(false);
		}
	};
	request.send();
}

function selectCategory(e) {
	var id;
	
	if (e.target.className === 'item') {
		id = e.target.dataset.category;
	}
	if (e.target.tagName === 'SPAN') {
		id = e.target.parentElement.parentElement.dataset.category;
	}
	if (e.target.tagName === 'A') {
		id = e.target.parentElement.dataset.category;
	}
	
	if (id) {
		getItems(id, 0);
		toggleActive(id);
		toggleMore(true);
	}
}

function toggleActive(id) {
	var active = document.querySelector('.section_event_tab .active');
	active.classList.remove('active');
	var ul = document.querySelector('.event_tab_lst');
	ul.children[id].firstElementChild.classList.add('active');
}

function updateCount(count) {
	var showCount = document.querySelector('.section_event_lst .pink'); // n개
	showCount.innerText = count + '개';
}

function getMore() {
	var categoryId = document.querySelector('.section_event_tab .active').parentElement.dataset.category;
	var showing = document.querySelectorAll('.lst_event_box .item').length;
	if (categoryId) {
		getItems(categoryId, showing);
	}
}

function toggleMore(isOff) {
	if (isOff) {
		document.querySelector('.more .btn').style.display = 'block';
	} else {
		document.querySelector('.more .btn').style.display = 'none';
	}
}

function showSlides() {
	var request = new XMLHttpRequest();
	request.open('GET', '/reservation/api/promotions');
	request.onload = function() {
		var promotionItems = JSON.parse(request.response).items;
		var promotionTemplate = document.querySelector('#promotionItem').innerHTML;
		var ul = document.querySelector('.section_visual .visual_img');
		var template = '';
		for (var i = 0, len = promotionItems.length; i < len; i++) {
			template = promotionTemplate.replace('{id}', promotionItems[i].id)
						    .replace('{productImageUrl}', promotionItems[i].productImageUrl);
			ul.innerHTML += template;
		}
		slideAnimate(promotionItems.length);
	}
	request.send();
}

function slideAnimate(maxLength) {
	var currentTranslate = [];
	var ul = document.querySelector('.section_visual .visual_img');
	var ulOffset = ul.offsetWidth; // 414
	for (var i = 0; i < maxLength; i++) {
		currentTranslate[i] = -ulOffset;
	}
	var startIndex = 0;
	setInterval(() => {
		startIndex++;
		var outerIndex = (startIndex - 1) % (maxLength);
		for(var i = 0; i < maxLength; i++) {
			var img = ul.children[i];
			img.style.opacity = '1';
			img.style.transform = 'translate('+(currentTranslate[i] - ulOffset)+'px)';
			currentTranslate[i] = currentTranslate[i] - ulOffset;
		}
		var outerImg = ul.children[outerIndex];
		outerImg.style.transform = 'translate('+(currentTranslate[outerIndex]+ulOffset*(maxLength))+'px)';
		outerImg.style.opacity = '0';
		currentTranslate[outerIndex] = currentTranslate[outerIndex] + ulOffset * (maxLength);
	}, 3000);
}

function onLoad() {
	getItems(0, 0);
	showSlides();
	document.querySelector('.more .btn').addEventListener('click', getMore);
	document.querySelector('.section_event_tab').addEventListener('click', selectCategory);
}

document.addEventListener('DOMContentLoaded', onLoad);