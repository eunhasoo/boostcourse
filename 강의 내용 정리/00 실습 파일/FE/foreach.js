var obj1 = {
    time: 0,
    getTimeIncrement() {
       return this.time++;
    },
    showRunningTime() {
        // bind 메소드를 사용해 this 인자를 바인딩해주지 않을 경우
        // this를 console로 찍어보면 window 객체가 나온다. 
        setInterval(function() {
            console.log(this.getTimeIncrement() + '초가 지났습니다.');
        }.bind(this), 1000);
    }
}

var obj2 = {
    time: 0,
    getTimeIncrement() {
       return this.time++;
    },
    showRunningTime() {
        setInterval(() => {
            console.log(this.getTimeIncrement() + '초가 지났습니다.');
        }, 1000);
        // ES6의 arrow 함수를 사용하면 bind 메소드가 없이도 this를 인식한다!
    }
}

//obj1.showRunningTime();
obj2.showRunningTime();
