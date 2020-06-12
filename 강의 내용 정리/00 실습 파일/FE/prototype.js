function Speaker(name, buyDate) {
  this.name = name;
  this.buyDate = buyDate;
}
Speaker.prototype.inform = function() {
    console.log(this.name + '은 ' + this.buyDate + '에 구매하였습니다.');
}

var s1 = new Speaker('JBL 1', '2019-11-12');
var s2 = new Speaker('Boss 1', '2017-06-14');
s1.inform();
s2.inform();