function onLoad() {
	getItems(0, 0);
	document.querySelector('footer .gototop').addEventListener('click', goToTop);
	document.querySelector('.more .btn').addEventListener('click', getMore);
}

function selectCategory(e) {
	var id = 0;
	if (e.target.className === 'item') {
		id = e.target.dataset.category;
		getItems(e.target.dataset.category, 0);
		toggleActive(id);
		toggleMore(false);
	}
	if (e.target.tagName === 'SPAN') {
		id = e.target.parentElement.parentElement.dataset.category;
		getItems(e.target.parentElement.parentElement.dataset.category, 0);
		toggleActive(id);
		toggleMore(false);
	}
}

function toggleActive(id) {
	var active = document.querySelector('.section_event_tab .active');
	active.classList.remove('active');
	var ul = document.querySelector('.event_tab_lst');
	ul.children[id].firstElementChild.classList.add('active');
}

function getItems(categoryId, start) {
	var request = new XMLHttpRequest();
	request.open('GET', '/reservation/api/products' + '?categoryId=' + categoryId + '&start=' + start);
	request.onload = function() {
		var leftSection = document.querySelector('.wrap_event_box').firstElementChild;
		var rightSection = leftSection.nextElementSibling;
		if (start == 0) {
			leftSection.innerText = '';
			rightSection.innerText = '';
		}
		var itemTemplate = document.querySelector('#itemList').innerHTML;
		var res = JSON.parse(request.response);
		var totalCount = res.totalCount;
		updateCount(totalCount);
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
			toggleMore(true);
		}
	};
	request.send();
}

function updateCount(count) {
	var showCount = document.querySelector('.section_event_lst .pink'); // n개
	showCount.innerText = count + '개';
}

function getMore() {
	var categoryId = document.querySelector('.section_event_tab .active').parentElement.dataset.category;
	var showing = document.querySelectorAll('.lst_event_box .item').length;
	console.log(categoryId, showing);
	if (categoryId) {
		getItems(categoryId, showing);
	}
}

function toggleMore(toggle) {
	if (toggle) {
		document.querySelector('.more .btn').style.display = 'none';
	} else {
		document.querySelector('.more .btn').style.display = 'block';
	}
}

function goToTop() {
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0;
}

document.addEventListener('DOMContentLoaded', onLoad);
document.querySelector('.section_event_tab').addEventListener('click', selectCategory);

