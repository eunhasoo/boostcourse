const data = {
    "debug": "on",
    "window": {
        "title": "Sample Konfabulator Widget",
        "name": "main_window",
        "width": 500,
        "height": 500
    },
    "image": { 
        "src": "Images/Sun.png",
        "name": "sun1",
        "hOffset": 250,
        "vOffset": 250,
        "alignment": "center"
    },
    "text": {
        "data": "Click Here",
        "size": 36,
        "style": "bold",
        "name": "text1",
        "hOffset": 250,
        "vOffset": 100,
        "alignment": "center",
        "onMouseUp": "sun1.opacity = (sun1.opacity / 100) * 90;"
    }
}

// 숫자 타입으로만 구성된 요소를 뽑아 배열 생성
var keys = [];
function getKeys(data) {
  for (let key in data) {
    if (typeof data[key] === 'number') {
      keys.push(key);
    }
    if (typeof data[key] === 'object') {
      getKeys(data[key]);
    }
  }
}
getKeys(data);
console.log(keys);