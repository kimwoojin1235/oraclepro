--시퀀스 삭제
DROP SEQUENCE seq_person_id;
--테이블 삭제
DELETE FROM person;

--테이블 생성
CREATE TABLE person(
    person_id NUMBER(5),
    name VARCHAR2(30) NOT NULL,
    hp VARCHAR2(20),
    company VARCHAR2(20),
    PRIMARY KEY(person_id)
);
--시퀀스 생성
CREATE SEQUENCE seq_person_id
INCREMENT BY 1 
START WITH 1 ;

--전화번호 생성
INSERT INTO person VALUES (seq_person_id.nextval,'이효리','010-1111-1111','02-1111-1111');

--전화번호 수정
UPDATE person SET name ='김00',hp='11111111',company='111111' WHERE person_id = 1;

--전화번호 삭제
DELETE FROM person WHERE person_id = 1;


--SELECT
SELECT  person_id,
        name,
        hp,
        company
FROM person;


--검색
select  person_id,
        name,
        hp,
        company
from person
where name like '%3%'
or hp like '%3%'
or company like '%3%';





































































































































