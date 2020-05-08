var data;

function makeTemplate(data, mName) {
    var tempHtml = document.querySelector('#template').innerHTML;
    var resultHtml = "";
    for (var i = 0; i < data.length; i++) {
        if (data[i].name === mName) {
            resultHtml += tempHtml.replace('{id}', data[i].id)
                                  .replace('{name}', data[i].name)
                                  .replace('{say}', data[i].say);
            break;
        }
    }
    document.querySelector('.content').innerHTML = resultHtml;
}

function sendAjax(url, mName) {
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", () => {
        data = JSON.parse(oReq.responseText);
        makeTemplate(data, mName);
    });
    oReq.open("GET", url);
    oReq.send();
}

var menu = document.querySelector('.tab-menu');
menu.addEventListener('click', function(e) {
    if (!data) {
        sendAjax('./json.text', e.target.innerText)
    }
    else {
        makeTemplate(data, e.target.innerText);
    }
});