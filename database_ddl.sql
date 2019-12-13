drop table hospital cascade CONSTRAINTS;
drop table doctor  cascade CONSTRAINTS;
drop table patient cascade CONSTRAINTS ;
drop table employee cascade CONSTRAINTS;
drop table hospitalsheet cascade CONSTRAINTS;
drop table login cascade CONSTRAINTS;

select * from login;

create table LOGIN(
Login_id VARCHAR(20),
Login_pw VARCHAR(20),
Login_name VARCHAR(20),
Login_level NUMBER,
Login_rank VARCHAR(10),
Login_otp VARCHAR(50),
primary key(Login_id)
);

commit;

-- 병원 테이블 생성
create table HOSPITAL(
Hospital_name VARCHAR(50),
Address VARCHAR(100),
tel CHAR(20),
fax CHAR(20),
primary key(Hospital_name)
);
commit;

-- 의사 테이블 생성
create table DOCTOR(
License_number number,
Name VARCHAR(20),
Hospital_name VARCHAR(50),
primary key(License_number)
);
commit;

--직원
create table employee(
employee_number number,
Name VARCHAR(20),
Hospital_name VARCHAR(50),
primary key(employee_number)
);
commit;

-- 환자 테이블 생성
create table PATIENT(
Reg_number CHAR(14),
Name VARCHAR(20),
Sex CHAR(1),
Age NUMBER,
Address VARCHAR(20),
primary key(Reg_number)
);

--진단서
create table HOSPITALSHEET(
Issue_number NUMBER,
Patient_Number char(13),
Doctor_Number NUMBER,
Hospital_name VARCHAR(50),
diagnosis_content VARCHAR(200), --진단내용
diagnosis_date DATE, --진단날짜
print_date DATE, --발행날짜
PRIMARY key(Issue_number)
);
commit;

alter table doctor
add constraint doc_hos_fk foreign key (hospital_name)
references hospital(hospital_name);

alter table employee
add constraint emp_hos_fk foreign key (hospital_name)
references hospital(hospital_name);

alter table hospitalsheet
add constraint sheet_pat_fk foreign key (Patient_number)
references patient(reg_number);

alter table hospitalsheet
add constraint sheet_doc_fk foreign key (Doctor_number)
references doctor(license_number);

alter table hospitalsheet
add constraint sheet_hos_fk foreign key (hospital_name)
references hospital(hospital_name);

insert into HOSPITAL values ('대구파티마병원','대구광역시 북구 칠곡중앙대로 194','053-311-2001','053-376-411');
insert into HOSPITAL values	('국군대구병원','경상북도 경산시 하양읍 대경로 425-41','053-956-533','053-587-141');
insert into HOSPITAL values	('애플산부인과의원','대구광역시 중구 동성로 25' , '053-482-8898','053-529-141');
insert into HOSPITAL values	('누네안과병원','대구광역시 중구 달구벌대로 2179','053-715-3300','053-541-390');
insert into HOSPITAL values	('크리미의원','대구광역시 중구 동성로2길 50','053-421-0082','053-690-790');
insert into HOSPITAL values	('일맥한의원','대구광역시 중구 동성로 2 협성빌딩', '053-424-1072','053-291-991');

select name from doctor where license_number=24360446;
commit;
-- 의사 데이터 삽입
insert into doctor values (24360446,'박관성','대구파티마병원');
insert into doctor values (40225111,'최다우','대구파티마병원');
insert into doctor values (62861659,'박현후','대구파티마병원');
insert into doctor values (71982022,'윤현은','대구파티마병원');
insert into doctor values (23654136,'장상유','대구파티마병원');
insert into doctor values (80792928,'윤현율','대구파티마병원');
insert into doctor values (58903252,'박예준','대구파티마병원');
insert into doctor values (96445671,'조지민','대구파티마병원');
insert into doctor values (83945186,'조찬호','대구파티마병원');
insert into doctor values (10277641,'김관호','대구파티마병원');
insert into doctor values (92801406,'조관후','대구파티마병원');
insert into doctor values (64618133,'조주희','대구파티마병원');
insert into doctor values (14513861,'정건민','대구파티마병원');
insert into doctor values (86113416,'임건현','대구파티마병원');
insert into doctor values (21593419,'조형윤','대구파티마병원');
insert into doctor values (87552567,'장언채','대구파티마병원');
insert into doctor values (80799082,'임재민','대구파티마병원');
insert into doctor values (40045978,'강다율','대구파티마병원');
insert into doctor values (25416513,'김형주','대구파티마병원');
insert into doctor values (67527674,'임승은','대구파티마병원');
insert into doctor values (99417683,'장예은','국군대구병원');
insert into doctor values (15937921,'임도아','국군대구병원');
insert into doctor values (70789553,'임재후','국군대구병원');
insert into doctor values (57588858,'임현현','국군대구병원');
insert into doctor values (52650682,'임재윤','국군대구병원');
insert into doctor values (15160871,'강지완','국군대구병원');
insert into doctor values (92300570,'박형서','국군대구병원');
insert into doctor values (90668036,'장형성','국군대구병원');
insert into doctor values (94356974,'윤시산','국군대구병원');
insert into doctor values (47713272,'장지구','국군대구병원');
insert into doctor values (31815586,'윤민아','국군대구병원');
insert into doctor values (81146446,'박찬율','국군대구병원');
insert into doctor values (45518120,'정재주','국군대구병원');
insert into doctor values (13769239,'임관인','국군대구병원');
insert into doctor values (77417261,'강지후','국군대구병원');
insert into doctor values (84004060,'최건기','국군대구병원');
insert into doctor values (66111900,'이시율','국군대구병원');
insert into doctor values (58141584,'박진기','국군대구병원');
insert into doctor values (77250005,'장지우','국군대구병원');
insert into doctor values (45854723,'김연윤','국군대구병원');
insert into doctor values (52547679,'김건성','애플산부인과의원');
insert into doctor values (84016153,'정찬인','애플산부인과의원');
insert into doctor values (65611235,'장예은','애플산부인과의원');
insert into doctor values (43797492,'이연수','애플산부인과의원');
insert into doctor values (25827731,'김건산','애플산부인과의원');
insert into doctor values (80825851,'김찬후','애플산부인과의원');
insert into doctor values (16727779,'정재서','애플산부인과의원');
insert into doctor values (34895426,'장현성','애플산부인과의원');
insert into doctor values (73520577,'조상현','애플산부인과의원');
insert into doctor values (33912074,'최시오','애플산부인과의원');
insert into doctor values (27672069,'강시성','애플산부인과의원');
insert into doctor values (47277726,'윤상철','애플산부인과의원');
insert into doctor values (47558484,'윤찬구','애플산부인과의원');
insert into doctor values (39649868,'임지호','애플산부인과의원');
insert into doctor values (70713264,'정언현','애플산부인과의원');
insert into doctor values (45480531,'박연우','애플산부인과의원');
insert into doctor values (72334948,'박다기','애플산부인과의원');
insert into doctor values (77294629,'윤연현','애플산부인과의원');
insert into doctor values (78648309,'박상주','애플산부인과의원');
insert into doctor values (61250765,'김주원','애플산부인과의원');
insert into doctor values (39457974,'강우호','누네안과의원');
insert into doctor values (85001928,'강주민','누네안과의원');
insert into doctor values (48978096,'장상준','누네안과의원');
insert into doctor values (88686940,'조건주','누네안과의원');
insert into doctor values (95680557,'강도우','누네안과의원');
insert into doctor values (12601420,'정연채','누네안과의원');
insert into doctor values (44104174,'장예우','누네안과의원');
insert into doctor values (67097639,'정연희','누네안과의원');
insert into doctor values (98581398,'장건후','누네안과의원');
insert into doctor values (88543875,'최언서','누네안과의원');
insert into doctor values (61628621,'윤승원','누네안과의원');
insert into doctor values (82003886,'윤서우','누네안과의원');
insert into doctor values (62840506,'조승산','누네안과의원');
insert into doctor values (54958890,'박찬기','누네안과의원');
insert into doctor values (66343249,'최다기','누네안과의원');
insert into doctor values (82928817,'강지수','누네안과의원');
insert into doctor values (45687374,'박지기','누네안과의원');
insert into doctor values (63012795,'정지민','누네안과의원');
insert into doctor values (70427788,'장다원','누네안과의원');
insert into doctor values (57614437,'임유철','누네안과의원');
insert into doctor values (64169338,'조현수','크리미의원');
insert into doctor values (86344478,'조하우','크리미의원');
insert into doctor values (86399066,'임형현','크리미의원');
insert into doctor values (49676837,'임승민','크리미의원');
insert into doctor values (56395994,'최현준','크리미의원');
insert into doctor values (11059580,'이관수','크리미의원');
insert into doctor values (84560330,'이주희','크리미의원');
insert into doctor values (36201174,'임연채','크리미의원');
insert into doctor values (67283979,'최동우','크리미의원');
insert into doctor values (37675494,'장민민','크리미의원');
insert into doctor values (84262782,'강지기','크리미의원');
insert into doctor values (46160262,'최건준','크리미의원');
insert into doctor values (53441539,'장찬희','크리미의원');
insert into doctor values (68399246,'최유희','크리미의원');
insert into doctor values (53409538,'조연서','크리미의원');
insert into doctor values (61715235,'이연원','크리미의원');
insert into doctor values (48254783,'임서우','크리미의원');
insert into doctor values (63709003,'조형준','크리미의원');
insert into doctor values (35906407,'정주기','크리미의원');
insert into doctor values (84979705,'정시윤','크리미의원');
insert into doctor values (49154350,'임현채','일맥한의원');
insert into doctor values (95427967,'임지오','일맥한의원');
insert into doctor values (55834898,'박찬후','일맥한의원');
insert into doctor values (81341855,'김지우','일맥한의원');
insert into doctor values (19952572,'박언민','일맥한의원');
insert into doctor values (84298640,'임서은','일맥한의원');
insert into doctor values (38281225,'장민구','일맥한의원');
insert into doctor values (38610686,'윤형산','일맥한의원');
insert into doctor values (46422720,'장유우','일맥한의원');
insert into doctor values (90406158,'박예우','일맥한의원');
insert into doctor values (13112713,'이도유','일맥한의원');
insert into doctor values (70105571,'강민아','일맥한의원');
insert into doctor values (79628916,'조도오','일맥한의원');
insert into doctor values (55257754,'윤지현','일맥한의원');
insert into doctor values (93025168,'윤동주','일맥한의원');
insert into doctor values (53111000,'정지준','일맥한의원');
insert into doctor values (58625442,'장지주','일맥한의원');
insert into doctor values (38231502,'조형서','일맥한의원');
insert into doctor values (77119298,'조형인','일맥한의원');
insert into doctor values (78384966,'최우민','일맥한의원');


