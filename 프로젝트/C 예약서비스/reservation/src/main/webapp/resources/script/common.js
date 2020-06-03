document.addEventListener('DOMContentLoaded', onLoad);

function onLoad() {
	document.querySelector('footer .gototop').addEventListener('click', () => {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
	});
}