function Tab(tabMenu) {
    this.tabMenu = tabMenu;
    this.registerEvent();
}

Tab.prototype = {
    registerEvent() {
        this.tabMenu.addEventListener('click', function(e) {
            this.sendAjax('./json.text', e.target.innerText)
        }.bind(this));
    },
    sendAjax(url, mName) {
        var oReq = new XMLHttpRequest();
        oReq.addEventListener("load", () => {
            data = JSON.parse(oReq.responseText);
            this.makeTemplate(data, mName);
        });
        oReq.open("GET", url);
        oReq.send();
    },
    makeTemplate(data, mName) {
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
}

const tabMenu = document.querySelector('.tab-menu');
var oTab = new Tab(tabMenu);