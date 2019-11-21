drop table member;
drop table data;
create table member(
user_id varchar(20) primary key,
user_pw varchar(20) not null,
user_name varchar(20) not null,
user_level number not null,
otpkey varchar(50)
);

commit;

create table data(
data_id number primary key,
site_id  varchar(30) not null,
site_pw varchar(30) not null,
site    varchar(100) not null,
data_level number not null,
data_description varchar(100)
);
drop sequence data_seq;
CREATE SEQUENCE data_seq
start with 1
increment by 1
NOMAXVALUE
CACHE 200;


insert into data values(data_seq.nextVal, 'knu1', 'knu1', 'http://www.knu.ac.kr', 1, '경북대학교 홈페이지 관리자 계정');
insert into data values(data_seq.nextVal, 'abeek1', 'abeek1', 'http://abeek.knu.ac.kr', 2, '경북대학교 공학인증 홈페이지 관리자 계정');
insert into data values(data_seq.nextVal, 'lms1', 'lms2', 'http://lms.knu.ac.kr', 3, '경북대학교 학습 관리 홈페이지 관리자 계정');
commit;

update member set user_level = 3 where user_id='nsun9505';
commit;