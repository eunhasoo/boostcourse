# 1. 데이터베이스

### 1) 데이터베이스란?
#### 정의
- 데이터의 집합 _A Set of Data_
- 여러 프로그램들의 통합된 정보들을 저장하여 운영할 수 있는 공용 데이터의 집합
- 효율적으로 저장, 검색, 갱신하도록 데이터 집합끼리 연관 및 조직화 필요

#### 특성
- 실시간 접근 (Real-time Accessability)\
사용자의 요구를 즉시 처리
- 지속적 변화 (Continuous Evolution)\
정확한 값을 유지하려고 삽입, 삭제, 수정을 통해 데이터를 지속적으로 갱신
- 동시 공유성 (Concurrent Sharing)\
동시에 여러 사람이 동일한 데이터에 접근 및 이용 가능
- 내용 참조 (Content Reference)\
저장한 데이터 레코드의 위치나 주소가 아닌 데이터 값에 따라 참조 가능


#### 테이블
- 데이터를 저장하는 공간
- 생성 및 이용을 위해서 SQL을 사용
- 1개 이상의 컬럼_column_ 과 0개 이상의 열_row_ 로 구성
- 행 (=레코드): 컬럼들의 값의 조합. 중복을 허용하지 않고 필수로 값을 갖는 '기본키'에 의해 구분
- 열 (=컬럼): 테이블 상에서의 단일 종류의 데이터를 나타내고, 특정 데이터 타입과 크기를 가짐
- 필드: 행과 열의 교차점. 데이터가 없으면 null값을 가짐

### 2) 데이터베이스 관리 시스템 DBMS
#### 정의
- DataBase Management System의 약자
- 데이터베이스를 관리하는 소프트웨어
- 여러 응용 프로그램 또는 시스템이 동시에 데이터베이스에 접근하여 사용가능하도록 함
- 필수 3기능: `정의`_Definition_ `조작`_Manipulation_ `제어`_Control_
- 종류: Oracle, SQL Server, MySQL 등

#### 장점
- 데이터 중복 최소화
- 데이터 일관성 및 무결성 유지
- 데이터 보안을 보장

#### 단점
- 비싼 운영비
- 백업 및 복구에 대한 관리의 복잡성
- 부분적 데이터베이스 손실이 전체 시스템을 정지시킴

# 2. SQL
### 1) SQL이란?
#### 정의
- 구조화된 쿼리 언어 Structured Query Language의 약자
- 데이터를 보다 쉽게 조작하도록 고안된 컴퓨터 언어
- 관계형 데이터베이스에서 데이터를 조작하고 쿼리하는 표준 수단

#### 종류
- 조작어 (DML: _Data Manipulation Language_)\
데이터를 조작하기 위해 사용 `INSERT` `UPDATE` `DELETE` `SELECT` 등

- 정의어 (DDL: _Data Definition Language_)\
데이터베이스 스키마 정의 및 조작을 위해 사용 `CREATE` `DROP` `ALTER` 등

- 제어어 (DCL: _Data Control Language_)\
권한 관리, 데이터 보안, 무결성 등을 정의하기 위해 사용 `GRANT` `REVOKE` 등

### 2) MySQL 8.0
#### 시작하기
```SQL
# root 계정으로 로그인
mysql -uroot -p
```

```SQL
# 데이터베이스 생성하기
create database DB이름;
```

```SQL
# 데이터베이스 사용 계정 생성 및 권한 부여
create user '유저명'@'localhost' identified by '유저비번';
grant all privileges on DB이름.* to '유저명'@'%';
grant all privileges on DB이름.* to '유저명'@'%' with grant option;
flush privileges;
```

```SQL
# 새 사용자로 로그인
mysql -h호스트명(127.0.0.1) -u유저명 -p DB이름
```

#### 기본 명령문 살펴보기
```SQL
# 키워드는 대소문자 구분 X (' '안에 들어가는 단일값은 대소문자 구분 필요)
SELECT current_date();
select Current_Date();
```

```SQL
# 세미콜론으로 문장을 구분하며
SELECT version(); SELECT NOW();
# 여러 줄로 표현할 수 있음
> SELECT
> now();
```

```SQL
# 문장 입력중 취소는 \c 입력
> SELECT
> now(),
> \c
```

```SQL
# 테이블 목록보기
show tables;
# 테이블 구조 확인하기
desc 테이블명;
```


### 데이터 조작어 DML

#### 1) SELECT: 검색
```SQL
SELECT (DISTINCT) 컬럼명 (AS 지정하고 싶은 컬럼명)
FROM 테이블명
WHERE 조건식
ORDER BY 컬럼 혹은 표현식 (ASC | DESC);
```
- 괄호가 쳐진 키워드들은 옵션으로, 필수가 아님
- `DISTINCT`는 중복행을 제거
- `AS` (=ALIAS)는 컬럼명 대신 다른 **별칭**을 부여. 키워드 생략해서도 사용 가능
- 컬럼명을 나열하는 대신 `*` 기호를 기술함으로써 모든 데이터를 출력할 수 있음
- `WHERE`절은 조건을 이용해서 특정 행만 가져옴
- `ORDER BY`절은 어떤 기준을 가지고 정렬해서 불러올 수 있음
- `ASC`는 오름차순(디폴트. 생략가능), `DESC`는 내림차순으로 출력

- `IN` 키워드\
여러 조건을 간단하게 나열하기 편하다

```SQL
# 부서 번호가 10, 30인 사원의 이름과 부서 번호 출력하기
SELECT name, deptno FROM employee
WHERE deptno IN (10, 30);
```
- `LIKE` 키워드\
와일드 카드를 사용하여 특정 문자를 포함한 값에 대한 조건을 처리한다

```SQL
# %는 특정 문자를 0개 이상 포함한 문자열을 나타내는 와일드 카드
# 이름에 A가 들어가는 모든 사원 이름 출력
SELECT name FROM employee
WHERE name LIKE '%A%';

# _는 단 하나의 문자만을 포함하는 와일드 카드
# 아무 문자 한개 다음 e를 만족시키는 사원 이름 출력 (ex. Jennifer, Ben, ...)
SELECT name FROM employee
WHERE name LIKE '_e%';
```

- 함수\
(LCASE = LOWER), (UCASE = UPPER), substring, LPAD, RPAD, TRIM 등 DBMS별로 다양한 함수 존재


#### 2) INSERT: 등록
```SQL
# 필드명 지정 방식
INSERT INTO 테이블명(필드1, 필드2, ...)
	VALUES(필드1의 값, 필드2의 값, ...)

# 필드명 생략 방식
INSERT INTO 테이블명
	VALUES(필드1의 값, 필드2의 값, ...)
```
- 필드명을 지정하는 방식은 디폴트 값이 세팅되는 필드 생략 가능\
추후 필드가 추가, 변경, 수정 되는 변경에 유연하게 대처가 가능
- 필드명을 생략하는 경우 모든 필드 값을 **반드시** 입력해야 함


#### 3) UPDATE: 수정
```SQL
UPDATE 테이블명
SET 필드1 = 새 필드1의 값, 필드2 = 새 필드2의 값, ...
WHERE 조건식
```
- **조건식**이 주어지지 않으면 전체 row에 영향을 미치므로 주의!

#### 4) DELETE: 삭제
```SQL
DELETE FROM 테이블명
WHERE 조건식
```
- UPDATE문과 마찬가지로 조건식이 주어지지 않으면 전체 row에 영향을 미침

### 데이터 정의어 DDL
- 데이터베이스에서 제공하는 table, view, index 등을 생성, 수정, 제거
- 테이블에 관한 명령어만을 중점적으로 알아볼 예정
- <참고> [MySQL 8.0 DDL 레퍼런스](https://dev.mysql.com/doc/refman/8.0/en/sql-data-definition-statements.html)

#### 데이터 타입

![](https://cphinf.pstatic.net/mooc/20180131_89/1517386938999sf3SM_PNG/2_8_3_MySQL__-1.PNG)

![](https://cphinf.pstatic.net/mooc/20180131_46/1517386952668I67cM_PNG/2_8_3_MySQL__-2.PNG)

#### 테이블 추가
```SQL
CREATE table 테이블명( 
	필드명1 타입 [NULL | NOT NULL] [DEFAULT 기본값] [AUTO_INCREMENT] ..., 
	필드명2 타입 ..., 
	필드명3 타입 ... [primary key],
	혹은 
	[primary key(필드명3)]
);
```
#### 테이블 수정
```SQL
# 컬럼 추가하기
ALTER table 테이블명
ADD 필드명 타입 [NOT NULL | NULL] ...
```

```SQL
# 컬럼 삭제하기
ALTER table 테이블명 
DROP 필드명;
```

```SQL
# 컬럼 수정하기
ALTER table 테이블명
CHANGE 필드명 새필드명 타입 [NULL] ...
```