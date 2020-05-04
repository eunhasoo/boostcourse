-- 테이블 생성
CREATE TABLE todo (
	id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, 
	title VARCHAR(255) NOT NULL, 
	name VARCHAR(100) NOT NULL, 
	sequence INT(1) NOT NULL, 
	type VARCHAR(20) DEFAULT 'TODO', 
	regdate DATETIME DEFAULT NOW(), 
	PRIMARY KEY (id)
);

insert into todo(title, name, sequence) values('자바스크립트 공부하기', '정은하', 1); 
insert into todo(title, name, sequence) values('Spring 공부하기', '정은하', 1);
insert into todo(title, name, sequence) values('Vue.js 공부하기', '정은하', 1); 
insert into todo(title, name, sequence) values('운동하기', '정은하', 1);
