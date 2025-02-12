CREATE TABLE tblMember(
	num INT AUTO_INCREMENT,
	name CHAR(10) NOT NULL,
	phon CHAR(20) NOT NULL,
	address CHAR(70) NOT NULL,
	team CHAR(20) NOT NULL,
	PRIMARY KEY (num)
)

-- 입력
INSERT tblmember (NAME, phone, address, team) VALUES ('박보영', '010-1111-2222', '서울', '배우') 

-- 가져오기
SELECT * FROM tblmember

-- 필요한 컬럼만 가져오기
SELECT NAME, num FROM tblmember WHERE num = 1

--조건에 맞는 레코드(행) 가져오기
SELECT * tblmember WHERE num = 1

SELECT * FROM tblmember WHERE NAME = '홍길동' OR team = '배우'

-- 수정
UPDATE tblmember SET NAME = '강호동', team = 'MC' WHERE num = 1;

-- 삭제
DELETE FROM tblmember WHERE num = 1

-- 테이블 전체 삭제 (스키마 포함)
DROP TABLE tblmember

CREATE TABLE tblZipcode(
 zipcode CHAR(5) NOT NULL,
 area1 CHAR(20) NOT NULL,
 area2 CHAR(30) NOT NULL,
 area3 CHAR(50) NOT NULL 
)

SELECT * FROM tblzipcode WHERE AREA3 LIKE '강남대로%';