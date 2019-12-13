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

-- ���� ���̺� ����
create table HOSPITAL(
Hospital_name VARCHAR(50),
Address VARCHAR(100),
tel CHAR(20),
fax CHAR(20),
primary key(Hospital_name)
);
commit;

-- �ǻ� ���̺� ����
create table DOCTOR(
License_number number,
Name VARCHAR(20),
Hospital_name VARCHAR(50),
primary key(License_number)
);
commit;

--����
create table employee(
employee_number number,
Name VARCHAR(20),
Hospital_name VARCHAR(50),
primary key(employee_number)
);
commit;

-- ȯ�� ���̺� ����
create table PATIENT(
Reg_number CHAR(14),
Name VARCHAR(20),
Sex CHAR(1),
Age NUMBER,
Address VARCHAR(20),
primary key(Reg_number)
);

--���ܼ�
create table HOSPITALSHEET(
Issue_number NUMBER,
Patient_Number char(13),
Doctor_Number NUMBER,
Hospital_name VARCHAR(50),
diagnosis_content VARCHAR(200), --���ܳ���
diagnosis_date DATE, --���ܳ�¥
print_date DATE, --���೯¥
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

insert into HOSPITAL values ('�뱸��Ƽ������','�뱸������ �ϱ� ĥ���߾Ӵ�� 194','053-311-2001','053-376-411');
insert into HOSPITAL values	('�����뱸����','���ϵ� ���� �Ͼ��� ���� 425-41','053-956-533','053-587-141');
insert into HOSPITAL values	('���û���ΰ��ǿ�','�뱸������ �߱� ������ 25' , '053-482-8898','053-529-141');
insert into HOSPITAL values	('���׾Ȱ�����','�뱸������ �߱� �ޱ������ 2179','053-715-3300','053-541-390');
insert into HOSPITAL values	('ũ�����ǿ�','�뱸������ �߱� ������2�� 50','053-421-0082','053-690-790');
insert into HOSPITAL values	('�ϸ����ǿ�','�뱸������ �߱� ������ 2 ��������', '053-424-1072','053-291-991');

select name from doctor where license_number=24360446;
commit;
-- �ǻ� ������ ����
insert into doctor values (24360446,'�ڰ���','�뱸��Ƽ������');
insert into doctor values (40225111,'�ִٿ�','�뱸��Ƽ������');
insert into doctor values (62861659,'������','�뱸��Ƽ������');
insert into doctor values (71982022,'������','�뱸��Ƽ������');
insert into doctor values (23654136,'�����','�뱸��Ƽ������');
insert into doctor values (80792928,'������','�뱸��Ƽ������');
insert into doctor values (58903252,'�ڿ���','�뱸��Ƽ������');
insert into doctor values (96445671,'������','�뱸��Ƽ������');
insert into doctor values (83945186,'����ȣ','�뱸��Ƽ������');
insert into doctor values (10277641,'���ȣ','�뱸��Ƽ������');
insert into doctor values (92801406,'������','�뱸��Ƽ������');
insert into doctor values (64618133,'������','�뱸��Ƽ������');
insert into doctor values (14513861,'���ǹ�','�뱸��Ƽ������');
insert into doctor values (86113416,'�Ӱ���','�뱸��Ƽ������');
insert into doctor values (21593419,'������','�뱸��Ƽ������');
insert into doctor values (87552567,'���ä','�뱸��Ƽ������');
insert into doctor values (80799082,'�����','�뱸��Ƽ������');
insert into doctor values (40045978,'������','�뱸��Ƽ������');
insert into doctor values (25416513,'������','�뱸��Ƽ������');
insert into doctor values (67527674,'�ӽ���','�뱸��Ƽ������');
insert into doctor values (99417683,'�忹��','�����뱸����');
insert into doctor values (15937921,'�ӵ���','�����뱸����');
insert into doctor values (70789553,'������','�����뱸����');
insert into doctor values (57588858,'������','�����뱸����');
insert into doctor values (52650682,'������','�����뱸����');
insert into doctor values (15160871,'������','�����뱸����');
insert into doctor values (92300570,'������','�����뱸����');
insert into doctor values (90668036,'������','�����뱸����');
insert into doctor values (94356974,'���û�','�����뱸����');
insert into doctor values (47713272,'������','�����뱸����');
insert into doctor values (31815586,'���ξ�','�����뱸����');
insert into doctor values (81146446,'������','�����뱸����');
insert into doctor values (45518120,'������','�����뱸����');
insert into doctor values (13769239,'�Ӱ���','�����뱸����');
insert into doctor values (77417261,'������','�����뱸����');
insert into doctor values (84004060,'�ְǱ�','�����뱸����');
insert into doctor values (66111900,'�̽���','�����뱸����');
insert into doctor values (58141584,'������','�����뱸����');
insert into doctor values (77250005,'������','�����뱸����');
insert into doctor values (45854723,'�迬��','�����뱸����');
insert into doctor values (52547679,'��Ǽ�','���û���ΰ��ǿ�');
insert into doctor values (84016153,'������','���û���ΰ��ǿ�');
insert into doctor values (65611235,'�忹��','���û���ΰ��ǿ�');
insert into doctor values (43797492,'�̿���','���û���ΰ��ǿ�');
insert into doctor values (25827731,'��ǻ�','���û���ΰ��ǿ�');
insert into doctor values (80825851,'������','���û���ΰ��ǿ�');
insert into doctor values (16727779,'���缭','���û���ΰ��ǿ�');
insert into doctor values (34895426,'������','���û���ΰ��ǿ�');
insert into doctor values (73520577,'������','���û���ΰ��ǿ�');
insert into doctor values (33912074,'�ֽÿ�','���û���ΰ��ǿ�');
insert into doctor values (27672069,'���ü�','���û���ΰ��ǿ�');
insert into doctor values (47277726,'����ö','���û���ΰ��ǿ�');
insert into doctor values (47558484,'������','���û���ΰ��ǿ�');
insert into doctor values (39649868,'����ȣ','���û���ΰ��ǿ�');
insert into doctor values (70713264,'������','���û���ΰ��ǿ�');
insert into doctor values (45480531,'�ڿ���','���û���ΰ��ǿ�');
insert into doctor values (72334948,'�ڴٱ�','���û���ΰ��ǿ�');
insert into doctor values (77294629,'������','���û���ΰ��ǿ�');
insert into doctor values (78648309,'�ڻ���','���û���ΰ��ǿ�');
insert into doctor values (61250765,'���ֿ�','���û���ΰ��ǿ�');
insert into doctor values (39457974,'����ȣ','���׾Ȱ��ǿ�');
insert into doctor values (85001928,'���ֹ�','���׾Ȱ��ǿ�');
insert into doctor values (48978096,'�����','���׾Ȱ��ǿ�');
insert into doctor values (88686940,'������','���׾Ȱ��ǿ�');
insert into doctor values (95680557,'������','���׾Ȱ��ǿ�');
insert into doctor values (12601420,'����ä','���׾Ȱ��ǿ�');
insert into doctor values (44104174,'�忹��','���׾Ȱ��ǿ�');
insert into doctor values (67097639,'������','���׾Ȱ��ǿ�');
insert into doctor values (98581398,'�����','���׾Ȱ��ǿ�');
insert into doctor values (88543875,'�־�','���׾Ȱ��ǿ�');
insert into doctor values (61628621,'���¿�','���׾Ȱ��ǿ�');
insert into doctor values (82003886,'������','���׾Ȱ��ǿ�');
insert into doctor values (62840506,'���»�','���׾Ȱ��ǿ�');
insert into doctor values (54958890,'������','���׾Ȱ��ǿ�');
insert into doctor values (66343249,'�ִٱ�','���׾Ȱ��ǿ�');
insert into doctor values (82928817,'������','���׾Ȱ��ǿ�');
insert into doctor values (45687374,'������','���׾Ȱ��ǿ�');
insert into doctor values (63012795,'������','���׾Ȱ��ǿ�');
insert into doctor values (70427788,'��ٿ�','���׾Ȱ��ǿ�');
insert into doctor values (57614437,'����ö','���׾Ȱ��ǿ�');
insert into doctor values (64169338,'������','ũ�����ǿ�');
insert into doctor values (86344478,'���Ͽ�','ũ�����ǿ�');
insert into doctor values (86399066,'������','ũ�����ǿ�');
insert into doctor values (49676837,'�ӽ¹�','ũ�����ǿ�');
insert into doctor values (56395994,'������','ũ�����ǿ�');
insert into doctor values (11059580,'�̰���','ũ�����ǿ�');
insert into doctor values (84560330,'������','ũ�����ǿ�');
insert into doctor values (36201174,'�ӿ�ä','ũ�����ǿ�');
insert into doctor values (67283979,'�ֵ���','ũ�����ǿ�');
insert into doctor values (37675494,'��ι�','ũ�����ǿ�');
insert into doctor values (84262782,'������','ũ�����ǿ�');
insert into doctor values (46160262,'�ְ���','ũ�����ǿ�');
insert into doctor values (53441539,'������','ũ�����ǿ�');
insert into doctor values (68399246,'������','ũ�����ǿ�');
insert into doctor values (53409538,'������','ũ�����ǿ�');
insert into doctor values (61715235,'�̿���','ũ�����ǿ�');
insert into doctor values (48254783,'�Ӽ���','ũ�����ǿ�');
insert into doctor values (63709003,'������','ũ�����ǿ�');
insert into doctor values (35906407,'���ֱ�','ũ�����ǿ�');
insert into doctor values (84979705,'������','ũ�����ǿ�');
insert into doctor values (49154350,'����ä','�ϸ����ǿ�');
insert into doctor values (95427967,'������','�ϸ����ǿ�');
insert into doctor values (55834898,'������','�ϸ����ǿ�');
insert into doctor values (81341855,'������','�ϸ����ǿ�');
insert into doctor values (19952572,'�ھ��','�ϸ����ǿ�');
insert into doctor values (84298640,'�Ӽ���','�ϸ����ǿ�');
insert into doctor values (38281225,'��α�','�ϸ����ǿ�');
insert into doctor values (38610686,'������','�ϸ����ǿ�');
insert into doctor values (46422720,'������','�ϸ����ǿ�');
insert into doctor values (90406158,'�ڿ���','�ϸ����ǿ�');
insert into doctor values (13112713,'�̵���','�ϸ����ǿ�');
insert into doctor values (70105571,'���ξ�','�ϸ����ǿ�');
insert into doctor values (79628916,'������','�ϸ����ǿ�');
insert into doctor values (55257754,'������','�ϸ����ǿ�');
insert into doctor values (93025168,'������','�ϸ����ǿ�');
insert into doctor values (53111000,'������','�ϸ����ǿ�');
insert into doctor values (58625442,'������','�ϸ����ǿ�');
insert into doctor values (38231502,'������','�ϸ����ǿ�');
insert into doctor values (77119298,'������','�ϸ����ǿ�');
insert into doctor values (78384966,'�ֿ��','�ϸ����ǿ�');


