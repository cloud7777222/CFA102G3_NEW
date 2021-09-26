CREATE DATABASE IF NOT EXISTS beloveDB;

use beloveDB;

DROP TABLE IF EXISTS NEWS;
DROP TABLE IF EXISTS ADVERTISE;
#======================================分隔線==================================================
DROP TABLE IF EXISTS `MEMBER`;
DROP TABLE IF EXISTS `FRIEND`;
DROP TABLE IF EXISTS `CHAT`;
#======================================分隔線==================================================
DROP TABLE IF EXISTS EXPERT;
DROP TABLE IF EXISTS EXPERTGENRE;
DROP TABLE IF EXISTS EMPFUNCTION;
DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS FUNCTIONEMP;
DROP TABLE IF EXISTS LESSONREPORT;
DROP TABLE IF EXISTS LESSONAPPO;
DROP TABLE IF EXISTS LESSONREVIEW;
DROP TABLE IF EXISTS DATEREPORT;
DROP TABLE IF EXISTS DATEAPPOORDER;
#======================================分隔線==================================================
DROP TABLE IF EXISTS `ACTIVITYTYPE`;
DROP TABLE IF EXISTS `ACTIVITY`;
DROP TABLE IF EXISTS `ACTIVITYORDER`;
DROP TABLE IF EXISTS `ACTIVITYEVALUATION`;
DROP TABLE IF EXISTS `ACTIVITYMESSAGE`;
DROP TABLE IF EXISTS `ACTIVITYMESSAGEREPORT`;
#======================================分隔線==================================================
drop table if exists PRODUCTSORT;
drop table if exists `ORDER`;
drop table if exists ORDERLIST;
drop table if exists PRODUCT;
#======================================分隔線==================================================
DROP TABLE IF EXISTS MEMPERSONALPAGE;
DROP TABLE IF EXISTS EXPERTPERSONALPAGE;
DROP TABLE IF EXISTS POST;
DROP TABLE IF EXISTS POSTTYPE;
DROP TABLE IF EXISTS POSTREPORT;
DROP TABLE IF EXISTS MESSAGEREPORT;
DROP TABLE IF EXISTS POSTMESSAGE;
DROP TABLE IF EXISTS EXPERTARTICLE;

#======================================分隔線==================================================
set auto_increment_offset=1001;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
set auto_increment_increment=1;
create table NEWS (
	NEWSNO     int auto_increment not null,
	NEWSTITLE     varchar(20) not null,
	NEWS       varchar(200) not null,
	NEWSPIC  mediumblob,
	NEWSSTATE       tinyint(1) default 1,
	POSTTIME      datetime default now(),    
	constraint NEWS_NEWSNO_PK primary key (NEWSNO)
) AUTO_INCREMENT = 1001;

insert into NEWS (NEWSTITLE, NEWS, NEWSSTATE, POSTTIME)  value ('新聞1'  ,'這是內容', 0, str_to_date('1981-11-17','%Y-%m-%d'));
insert into NEWS (NEWSTITLE, NEWS, NEWSSTATE, POSTTIME)  value ('新聞2'  ,'這是內容', 1, str_to_date('1985-11-17','%Y-%m-%d'));
insert into NEWS (NEWSTITLE, NEWS, NEWSSTATE, POSTTIME)  value ('新聞3'  ,'這是內容', 0, str_to_date('1989-11-17','%Y-%m-%d'));
insert into NEWS (NEWSTITLE, NEWS, NEWSSTATE, POSTTIME)  value ('新聞4'  ,'這是內容', 1, str_to_date('2011-11-17','%Y-%m-%d'));
insert into NEWS (NEWSTITLE, NEWS, NEWSSTATE, POSTTIME)  value ('新聞5'  ,'這是內容', 0, str_to_date('2020-11-17','%Y-%m-%d'));
insert into NEWS (NEWSTITLE, NEWS, NEWSSTATE)  value ('新聞6'  ,'這是內容', 0);

#======================================分隔線==================================================
set auto_increment_offset=1001;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
set auto_increment_increment=1;
create table ADVERTISE (
	ADNO     int auto_increment not null,
	ADTITLE     varchar(20) not null,
	AD       varchar(200) not null,
	ADPIC1  varchar(200) not null,
    ADPIC2  varchar(200),
    ADPIC3  varchar(200),
	ADSTATE       tinyint(1) default 1,
	POSTTIME      datetime default now(),
    DEADLINE      datetime not null,
	constraint ADVERTISE_ADNO_PK primary key (ADNO)
) AUTO_INCREMENT = 1001;

insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, POSTTIME, DEADLINE)  value ('廣告1'  , '這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('1981-11-17','%Y-%m-%d'), str_to_date('1982-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告2'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 1, str_to_date('1999-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, POSTTIME, DEADLINE)  value ('廣告3'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('1981-11-17','%Y-%m-%d'), str_to_date('1989-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告4'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('1999-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, POSTTIME, DEADLINE)  value ('廣告5'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 1, str_to_date('1981-11-17','%Y-%m-%d'), str_to_date('2010-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告6'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告7'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告8'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告9'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告10'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告11'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告12'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告13'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告14'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));
insert into ADVERTISE (ADTITLE, AD, ADPIC1, ADPIC2, ADPIC3, ADSTATE, DEADLINE)  value ('廣告15'  ,'這是內容', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 'https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg','https://tyenews.com/wp-content/uploads/2021/05/%E7%8B%97%E7%8B%975411953.jpg', 0, str_to_date('2023-11-17','%Y-%m-%d'));

#======================================分隔線==================================================
CREATE TABLE `MEMBER`(
`memberNo` INT NOT NULL AUTO_INCREMENT COMMENT '會員編號',
`memberAccount` VARCHAR(10) Not Null COMMENT '帳號',
`memberPassword` VARCHAR(10) NOT NULL COMMENT '密碼',
`memberPhoto` MEDIUMBLOB COMMENT '相片',
`memberName` VARCHAR(20) COMMENT '姓名',
`memberGender` TINYINT(1) COMMENT '性別',
`memberBirthday` DATETIME COMMENT '生日',
`memberJob` VARCHAR(20) COMMENT '職業',
`memberCountry` TINYINT(1) COMMENT '居住地',
`memberPhone` CHAR(10) COMMENT '電話',
`memberEmail` VARCHAR(40) NOT NULL COMMENT '信箱',
`memberIntroduce` VARCHAR(300) COMMENT '自我介紹',
`memberPoint` INT NOT NULL DEFAULT 0 COMMENT '點數',
`memberBlackList` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '黑名單',
PRIMARY KEY PK_MEMBER_MEMBERNO (memberNo),
UNIQUE KEY UK_MEMBER_MEMBERACCOUNT (memberAccount),
UNIQUE KEY UK_MEMBER_MEMBEREMAIL (memberEmail),
CONSTRAINT CK_MEMBER_MEMBERPOINT CHECK (memberPoINT>=0)
)COMMENT '會員';

INSERT INTO MEMBER(memberNo,memberAccount,memberPassword,memberPhoto,memberName,memberGender,memberBirthday,memberJob,memberCountry,memberPhone,memberEmail,memberIntroduce,memberPoint,memberBlackList)
VALUES(NULL,'abe','abe',null,'ABE',1,now(),'學生',1,'1234567890','abe@gmail.com','我是帥哥',default,default);
INSERT INTO MEMBER(memberNo,memberAccount,memberPassword,memberPhoto,memberName,memberGender,memberBirthday,memberJob,memberCountry,memberPhone,memberEmail,memberIntroduce,memberPoint,memberBlackList)
VALUES(NULL,'abee','abe',null,'ABE',1,now(),'學生',1,'1234567890','abee@gmail.com','我是帥哥',default,default);
INSERT INTO MEMBER(memberNo,memberAccount,memberPassword,memberPhoto,memberName,memberGender,memberBirthday,memberJob,memberCountry,memberPhone,memberEmail,memberIntroduce,memberPoint,memberBlackList)
VALUES(NULL,'abeee','abe',null,'ABE',1,now(),'學生',1,'1234567890','abeee@gmail.com','我是帥哥',default,default);
INSERT INTO MEMBER(memberNo,memberAccount,memberPassword,memberPhoto,memberName,memberGender,memberBirthday,memberJob,memberCountry,memberPhone,memberEmail,memberIntroduce,memberPoint,memberBlackList)
VALUES(NULL,'abeeee','abe',null,'ABE',1,now(),'學生',1,'1234567890','abeeee@gmail.com','我是帥哥',default,default);
INSERT INTO MEMBER(memberNo,memberAccount,memberPassword,memberPhoto,memberName,memberGender,memberBirthday,memberJob,memberCountry,memberPhone,memberEmail,memberIntroduce,memberPoint,memberBlackList)
VALUES(NULL,'abeeeee','abe',null,'ABE',1,now(),'學生',1,'1234567890','abeeeee@gmail.com','我是帥哥',default,default);

#======================================分隔線==================================================
CREATE TABLE `FRIEND`(
`memberNoA` INT NOT NULL COMMENT '會員A編號',
`memberNoB` INT NOT NULL COMMENT '會員B編號',
`friendRequest` TINYINT(1) NOT NULL COMMENT '好友邀請者',
`friendStatus` TINYINT(1) NOT NULL COMMENT '好友狀態',
PRIMARY KEY PK_FRIEND_MEMBERNOAB(memberNoA,memberNoB),
CONSTRAINT FK_FRIEND_MEMBERNOA FOREIGN KEY (memberNoA) REFERENCES MEMBER(memberNo)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT FK_FRIEND_MEMBERNOB FOREIGN KEY (memberNoB) REFERENCES MEMBER(memberNo)
ON UPDATE CASCADE
ON DELETE CASCADE
)COMMENT '好友';

INSERT INTO FRIEND(memberNoA,memberNoB,friendRequest,friendStatus)
VALUES(1,2,1,1);

#======================================分隔線==================================================
CREATE TABLE CHAT(
`chatNo` INT NOT NULL AUTO_INCREMENT COMMENT '聊天紀錄編號',
`memberNoA` INT NOT NULL COMMENT '會員A編號',
`memberNoB` INT NOT NULL COMMENT '會員B編號',
`chatTime` DATETIME NOT NULL default now() COMMENT '訊息發送時間',
`chatSender` TINYINT(1) NOT NULL COMMENT '訊息發送者',
`chatSeen` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '已讀',
`chatText` VARCHAR(100) COMMENT '文字',
`chatPhoto` MEDIUMBLOB COMMENT '相片',
`chatSticker` BLOB COMMENT 'BLOB',
PRIMARY KEY PK_CHAT_CHATNO(chatNo),
CONSTRAINT FK_CHAT_MEMBERNOA FOREIGN KEY(memberNoA) REFERENCES MEMBER(memberNo)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT FK_CHAT_MEMBERNOB FOREIGN KEY(memberNoB) REFERENCES MEMBER(memberNo)
ON UPDATE CASCADE
ON DELETE CASCADE
)COMMENT'聊天紀錄';

INSERT INTO CHAT(chatNo,memberNoA,memberNoB,chatTime,chatSender,chatSeen,chatText,chatPhoto,chatSticker)
VALUES(NULL,1,2,DEFAULT,1,DEFAULT,NULL,NULL,NULL);

#======================================分隔線==================================================
CREATE TABLE EXPERTGENRE(
 expertGenreNo 		INT(3) NOT NULL,
 exGenreName   		VARCHAR(20) NOT NULL,
 
CONSTRAINT EXPERTGENRE_expertGenreNo PRIMARY KEY (expertGenreNo)
 );
 
 INSERT INTO EXPERTGENRE VALUES (1,'exGenreName01');
 INSERT INTO EXPERTGENRE VALUES (2,'exGenreName02');
 INSERT INTO EXPERTGENRE VALUES (3,'exGenreName03');
 INSERT INTO EXPERTGENRE VALUES (4,'exGenreName04');
 INSERT INTO EXPERTGENRE VALUES (5,'exGenreName05');


#======================================分隔線==================================================
CREATE TABLE EXPERT (
 expertNo           INT(10) NOT NULL AUTO_INCREMENT,
 expertGenreNo		INT(3) NOT NULL,
 exAcount			VARCHAR(16) NOT NULL,
 exPassword			VARCHAR(16) NOT NULL,
 exPhoto			MEDIUMBLOB,
 exName				VARCHAR(20) NOT NULL,
 exGender			TINYINT(1) NOT NULL,
 exPhone			CHAR(10),
 exEmail			VARCHAR(40),
 exAdress			VARCHAR(100),
 exIntroduce		VARCHAR(300),
 checkData			MEDIUMBLOB NOT NULL,
 checkState			TINYINT(1) NOT NULL DEFAULT 0,
 exPoint			INT(7) NOT NULL DEFAULT 0,
 exBlackList		TINYINT(1) NOT NULL DEFAULT 1,
 exPopSum			INT(8) NOT NULL DEFAULT 0,
 exPointSum			INT(8) NOT NULL DEFAULT 0,
               
 CONSTRAINT EXPERT_expertNo PRIMARY KEY (expertNo),
 CONSTRAINT EXPERT_expertGenreNo FOREIGN KEY (expertGenreNo) REFERENCES EXPERTGENRE (expertGenreNo)
 ON UPDATE CASCADE
 ON DELETE CASCADE,
 CONSTRAINT EXPERT_exAcount UNIQUE (exAcount)
 )AUTO_INCREMENT = 1;
 
 INSERT INTO EXPERT VALUES (null,1,'exAcount01','exPW01',null,'exName01',0,'0123456789','123@abc.com','addr01','intro01','',1,10,1,1,1);
 INSERT INTO EXPERT VALUES (null,2,'exAcount02','exPW02',null,'exName02',1,'9876543210','234@abc.com','addr02','intro02','',1,20,1,2,2);
 INSERT INTO EXPERT VALUES (null,3,'exAcount03','exPW03',null,'exName03',0,'1111111111','345@abc.com','addr03','intro03','',1,30,1,3,3);
 INSERT INTO EXPERT VALUES (null,4,'exAcount04','exPW04',null,'exName04',1,'2222222222','456@abc.com','addr04','intro04','',1,40,1,4,4);
 INSERT INTO EXPERT VALUES (null,5,'exAcount05','exPW05',null,'exName05',0,'3333333333','567@abc.com','addr05','intro05','',1,50,1,5,5);
 
 
#======================================分隔線================================================== 
CREATE TABLE EMPLOYEE(
 empNo				INT(30) NOT NULL AUTO_INCREMENT,
 empAccount			VARCHAR(16) NOT NULL,
 empPassword		VARCHAR(16) NOT NULL,
 empName			VARCHAR(20) NOT NULL,
 empState			TINYINT(1) NOT NULL DEFAULT 1,
 
CONSTRAINT EMPLOYEE_empNo PRIMARY KEY(empNo),
CONSTRAINT EMPLOYEE_empAccount UNIQUE (empAccount)
 )AUTO_INCREMENT = 1;
 
 INSERT INTO EMPLOYEE VALUES (null,'empAcount01','empPW01','empName01','1');
 INSERT INTO EMPLOYEE VALUES (null,'empAcount02','empPW02','empName02','1');
 INSERT INTO EMPLOYEE VALUES (null,'empAcount03','empPW03','empName03','1');
 INSERT INTO EMPLOYEE VALUES (null,'empAcount04','empPW04','empName04','1');
 INSERT INTO EMPLOYEE VALUES (null,'empAcount05','empPW05','empName05','0');
 
 
#======================================分隔線==================================================
CREATE TABLE FUNCTIONEMP(
funcNo				INT(5) NOT NULL,
funcContent			VARCHAR(50) NOT NULL,

CONSTRAINT FUNCTIONEMP_funcNo PRIMARY KEY (funcNo)
);

 INSERT INTO FUNCTIONEMP VALUES (1,'func1Content');
 INSERT INTO FUNCTIONEMP VALUES (2,'func2Content');
 INSERT INTO FUNCTIONEMP VALUES (3,'func3Content');
 INSERT INTO FUNCTIONEMP VALUES (4,'func4Content');
 INSERT INTO FUNCTIONEMP VALUES (5,'func5Content');
 
 
#======================================分隔線================================================== 
CREATE TABLE EMPFUNCTION(
 empNo				INT(10) NOT NULL ,
 funcNo				INT(5) NOT NULL ,

PRIMARY KEY (empNo,funcNo),
CONSTRAINT EMPFUNCTION_COMPOSITE_empNo FOREIGN KEY (empNo) REFERENCES EMPLOYEE (empNo)
ON UPDATE CASCADE,
CONSTRAINT EMPFUNCTION_COMPOSITE_funcNo FOREIGN KEY (funcNo) REFERENCES FUNCTIONEMP (funcNo)
ON UPDATE CASCADE
ON DELETE CASCADE
 );
 
INSERT INTO EMPFUNCTION VALUES (1,1);
INSERT INTO EMPFUNCTION VALUES (2,2);
INSERT INTO EMPFUNCTION VALUES (3,3);
INSERT INTO EMPFUNCTION VALUES (4,4);
INSERT INTO EMPFUNCTION VALUES (5,5);


#======================================分隔線================================================== 
CREATE TABLE LESSONREPORT(
 memberNo				INT(10) NOT NULL ,
 expertNo			INT(10) NOT NULL ,
 expertRepTime		DATETIME NOT NULL,
 expertRepFor		VARCHAR(50) NOT NULL,
 expertRevState		TINYINT(1) NOT NULL,
 
PRIMARY KEY (memberNo,expertNo),
CONSTRAINT LESSONREPORT_COMPOSITE_mNo FOREIGN KEY (memberNo) REFERENCES MEMBER (memberNo)
ON UPDATE CASCADE,
CONSTRAINT LESSONREPORT_COMPOSITE_expertNo FOREIGN KEY (expertNo) REFERENCES EXPERT (expertNo)
ON UPDATE CASCADE
 );
 
 INSERT INTO LESSONREPORT VALUES (1,1,'2001-01-01 12:00:00','abc',1);
 INSERT INTO LESSONREPORT VALUES (1,2,'2001-02-02 14:00:00','bcd',2);
 INSERT INTO LESSONREPORT VALUES (1,3,'2001-03-03 16:00:00','def',3);
 INSERT INTO LESSONREPORT VALUES (1,4,'2001-04-04 18:00:00','efg',1);
 INSERT INTO LESSONREPORT VALUES (1,5,'2001-05-05 20:00:00','fgh',2);
 
 
#======================================分隔線================================================== 
CREATE TABLE LESSONAPPO(
 lsonAppoNo 		INT(10) NOT NULL AUTO_INCREMENT,
 memberNo				INT(10) NOT NULL,
 expertNo   		INT(10) NOT NULL,
 appoDateTime 		DATETIME NOT NULL,
 appoLsonName 		VARCHAR(20) NOT NULL,
 lsonContent 		VARCHAR(50) NOT NULL,
 lsonState	 		TINYINT(1) NOt NULL DEFAULT 0,
 
CONSTRAINT LESSONAPPO_lsonAppoNo PRIMARY KEY (lsonAppoNo),
CONSTRAINT LESSONAPPO_COMPOSITE_mNo FOREIGN KEY (memberNo) REFERENCES MEMBER (memberNo)
ON UPDATE CASCADE,
CONSTRAINT LESSONAPPO_COMPOSITE_expertNo FOREIGN KEY (expertNo) REFERENCES EXPERT (expertNo)
ON UPDATE CASCADE
)AUTO_INCREMENT = 1;

 INSERT INTO LESSONAPPO VALUES (null,1,1,'2001-01-01 10:00:00','lsonName01','lsonContent01',0);
 INSERT INTO LESSONAPPO VALUES (null,1,2,'2001-02-02 12:00:00','lsonName02','lsonContent02',1);
 INSERT INTO LESSONAPPO VALUES (null,1,3,'2001-03-03 14:00:00','lsonName03','lsonContent03',2);
 INSERT INTO LESSONAPPO VALUES (null,1,4,'2001-04-04 16:00:00','lsonName04','lsonContent04',3);
 INSERT INTO LESSONAPPO VALUES (null,1,5,'2001-05-05 18:00:00','lsonName05','lsonContent05',0);


#======================================分隔線==================================================
CREATE TABLE LESSONREVIEW(
  lsonReviewNo          INT(10) NOT NULL AUTO_INCREMENT ,
  memberNo				INT(10) NOT NULL ,
  expertNo			INT(10) NOT NULL ,
  lsonRate			TINYINT(1) NOT NULL,
  repContent		VARCHAR(255) NOT NULL,
  
CONSTRAINT LESSONREVIEW_lsonReviewNo PRIMARY KEY (lsonReviewNo),
CONSTRAINT LESSONREVIEW_COMPOSITE_mNo FOREIGN KEY (memberNo) REFERENCES MEMBER (memberNo)
ON UPDATE CASCADE,
CONSTRAINT LESSONREVIEW_COMPOSITE_expertNo FOREIGN KEY (expertNo) REFERENCES EXPERT (expertNo)
ON UPDATE CASCADE
);

 INSERT INTO LESSONREVIEW VALUES (null,1,1,1,'repContent01');
 INSERT INTO LESSONREVIEW VALUES (null,2,2,2,'repContent02');
 INSERT INTO LESSONREVIEW VALUES (null,1,3,3,'repContent03');
 INSERT INTO LESSONREVIEW VALUES (null,2,4,4,'repContent04');
 INSERT INTO LESSONREVIEW VALUES (null,1,5,5,'repContent05');


#======================================分隔線==================================================
CREATE TABLE DATEAPPOORDER(
 dateOrderNo		INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 memberNoA				INT(10) NOT NULL,
 memberNoB				INT(10) NOT NULL,
 dateOrderDate		DATETIME NOT NULL,
 dateAppoDate		DATETIME NOT NULL,
 dateOrderState		TINYINT(1) NOT NULL DEFAULT 0,
 dateStarRateA		TINYINT(1) NOT NULL DEFAULT 0,
 dateStarRateB		TINYINT(1) NOT NULL DEFAULT 0,
 dateCE				TINYINT(1) NOT NULL DEFAULT 0,

CONSTRAINT DATEAPPOORDER_mNoA FOREIGN KEY (memberNoA) REFERENCES MEMBER (memberNo)
ON UPDATE CASCADE,
CONSTRAINT DATEAPPOORDER_mNoB FOREIGN KEY (memberNoB) REFERENCES MEMBER (memberNo)
ON UPDATE CASCADE
)AUTO_INCREMENT = 1;

 INSERT INTO DATEAPPOORDER VALUES (null,1,2,'2002-01-01 11:11:11','2002-01-10 10:00:00',1,1,1,1);
 INSERT INTO DATEAPPOORDER VALUES (null,2,1,'2002-02-02 11:11:11','2002-02-10 12:00:00',1,2,2,2);
 INSERT INTO DATEAPPOORDER VALUES (null,1,2,'2002-03-03 11:11:11','2002-03-10 14:00:00',1,3,3,3);
 INSERT INTO DATEAPPOORDER VALUES (null,2,1,'2002-04-04 11:11:11','2002-04-10 16:00:00',1,4,4,4);
 INSERT INTO DATEAPPOORDER VALUES (null,1,2,'2002-05-05 11:11:11','2002-05-10 18:00:00',1,5,5,5);
 
 
#======================================分隔線================================================== 
CREATE TABLE DATEREPORT(
 dateOrderNo 		INT(10) NOT NULL ,
 memberNo				INT(10) NOT NULL ,
 dateRepTime			DATETIME NOT NULL,
 dateRepFor			VARCHAR(50) NOT NULL,
 dateRevState		TINYINT(1) NOT NULL DEFAULT 1,
 
PRIMARY KEY (dateOrderNo,memberNo),
CONSTRAINT DATEREPORT_dateOrderNo FOREIGN KEY (dateOrderNo) REFERENCES DATEAPPOORDER (dateOrderNo)
ON UPDATE CASCADE,
CONSTRAINT DATEREPORT_mNo FOREIGN KEY (memberNo) REFERENCES MEMBER (memberNo)
ON UPDATE CASCADE
);

 INSERT INTO DATEREPORT VALUES (1,1,'2002-01-10 12:00:00','dateRepFor01',1);
 INSERT INTO DATEREPORT VALUES (2,2,'2002-02-10 14:00:00','dateRepFor02',2);
 INSERT INTO DATEREPORT VALUES (3,1,'2002-03-10 16:00:00','dateRepFor03',3);
 INSERT INTO DATEREPORT VALUES (4,2,'2002-04-10 18:00:00','dateRepFor04',1);
 INSERT INTO DATEREPORT VALUES (5,1,'2002-05-10 20:00:00','dateRepFor05',2);
 
#======================================分隔線==================================================
CREATE TABLE `ACTIVITYTYPE` (
  `actType` INT NOT NULL COMMENT '活動類別編號(主鍵)',
  `actTypeName` VARCHAR(20) COMMENT '活動類別名稱',
  PRIMARY KEY (`actType`)
) COMMENT = '活動類別';

INSERT INTO
  `ACTIVITYTYPE`(`actType`,`actTypeName`)
VALUES
  (10,'INTEREST'),
  (20,'AGE'),
  (30,'CONDITION'),
  (40,'ATTENDANCE');


#======================================分隔線==================================================
CREATE TABLE `ACTIVITY` (
  `actNo` INT NOT NULL COMMENT '活動編號(主鍵)',
  `actType` INT NOT NULL COMMENT '活動類別編號(外來鍵)',
  `actName` VARCHAR(20) NOT NULL COMMENT '活動名稱',
  `actDate` DATE NOT NULL COMMENT '活動日期',
  `actLocation` VARCHAR(255) NOT NULL COMMENT '活動地點',
  `actDirection` VARCHAR(255) NOT NULL COMMENT '活動說明',
  `maxParticipant` INT(100) NOT NULL COMMENT '報名人數上限',
  `minParticipant` INT(1) NOT NULL COMMENT '報名人數下限',
  `actState` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '活動上下架狀態',
  `actHoldState` TINYINT(1) NOT NULL COMMENT '活動舉辦狀態',
  `actRegisterStartDate` DATETIME NOT NULL COMMENT '活動報名開始日期',
  `actRegisterDeadLine` DATETIME NOT NULL COMMENT '活動報名截止日期',
  `actPicture` MEDIUMBLOB COMMENT '活動圖片', 
  `totalStar` TINYINT NOT NULL COMMENT '總星星數',
  `totalEvaluator` INT(100) NOT NULL COMMENT '評價總人數',
  PRIMARY KEY (`actNo`),
  FOREIGN KEY (`actType`) REFERENCES ACTIVITYTYPE(`actType`)
) COMMENT = '活動';

INSERT INTO
  `ACTIVITY`(`actNo`,`actType`,`actName`,`actDate`,`actLocation`,`actDirection`,`maxParticipant`,
`minParticipant`,`actState`,`actHoldState`,`actRegisterStartDate`,`actRegisterDeadLine`,`actPicture`,`totalStar` ,`totalEvaluator`)
VALUES
  (1001,10,'KTV我們的主打歌','2021-08-30','中壢錢櫃','唱歌',16,6,1,1,'2021-08-25 00:00:00','2021-08-28 00:00:00',null,10,16),
  (1002,10,'滾滾保齡球','2021-08-30','亞運保齡球館','打保齡球',16,10,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,8,16),
  (1003,10,'料理小廚神','2021-08-30','中壢店家','料理',20,6,1,1,'2021-08-27 00:00:00','2021-08-29 00:00:00',null,10,20),
  (1004,10,'環遊世界','2021-08-30','中壢店家','旅遊',28,16,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,30,28),
  (1005,10,'戀在放映室','2021-08-30','中壢威尼斯','旅遊',16,10,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,28,16),
  (1006,10,'登在百岳中','2021-08-30','東眼山國家森林遊樂區','登山',28,16,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,12,16),
  (1007,10,'桌上會友','2021-08-30','中壢桌遊店家','桌遊',12,6,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,6,12),
  (1008,10,'愛情密碼','2021-08-30','A5 Studio','密室逃脫',12,6,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,8,12),
  (1009,20,'7年級聯誼','2021-08-30','中壢錢櫃','7年級',28,16,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,16,28),
  (1010,20,'8年級同溫層','2021-08-30','中壢店家','8年級',28,16,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,12,28),
  (1011,30,'鍾愛一生','2021-08-30','中壢店家','結婚',16,6,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,6,16),
  (1012,30,'醫起守護','2021-08-30','中壢店家','護士',28,16,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,10,28),
  (1013,30,'海海人生','2021-08-30','中壢店家','海外打工',20,6,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,12,20),
  (1014,40,'六人小聚會','2021-08-30','中壢店家','小聚會',6,6,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,3,6),
  (1015,40,'十人不寂寞','2021-08-30','中壢店家','不寂寞',10,10,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,5,10),
  (1016,40,'十六人來派對','2021-08-30','中壢店家','派對',16,16,1,1,'2021-08-27 00:00:00','2021-08-27 00:00:00',null,12,16);
 
#======================================分隔線==================================================
CREATE TABLE `ACTIVITYORDER` (
  `actNo` INT NOT NULL COMMENT '活動編號(主鍵,外來鍵)',
  `memberNo` INT(10) NOT NULL COMMENT '會員編號(主鍵,外來鍵)',
  `actOrderPoint` INT NOT NULL COMMENT '活動報名點數',
  `actTotalParticipant` INT(10) NOT NULL COMMENT '活動總參與人數',
  `actRegisterOrderDate` DATETIME NOT NULL COMMENT '活動報名訂單日期',
  PRIMARY KEY (`actNo`,memberNo),
  FOREIGN KEY (`actNo`) REFERENCES ACTIVITY(`actNo`),
  FOREIGN KEY (`memberNo`) REFERENCES MEMBER(`memberNo`)
) COMMENT = '活動報名訂單';

INSERT INTO
  `ACTIVITYORDER`(`actNo`,`memberNo`,`actOrderPoint`,`actTotalParticipant`,`actRegisterOrderDate`)
VALUES
  (1001,1,300,16,'2021-08-29 00:00:00'),
  (1009,2,300,28,'2021-08-29 00:00:00');
 
#======================================分隔線==================================================
CREATE TABLE `ACTIVITYEVALUATION` (
  `actNo` INT NOT NULL COMMENT '活動編號(主鍵,外來鍵)',
  `memberNo` INT(10) NOT NULL COMMENT '會員編號(主鍵,外來鍵)',
  `actStarRate` TINYINT(1) NOT NULL COMMENT '活動星數評價',
  PRIMARY KEY (`actNo`,memberNo),
  FOREIGN KEY (`actNo`) REFERENCES ACTIVITY(`actNo`),
  FOREIGN KEY (`memberNo`) REFERENCES MEMBER(`memberNo`)
) COMMENT = '活動評價';

INSERT INTO
  `ACTIVITYEVALUATION`(`actNo`,`memberNo`,`actStarRate`)
VALUES
  (1001,1,'1'),
  (1009,2,'2');
  
#======================================分隔線==================================================
CREATE TABLE `ACTIVITYMESSAGE` (
  `actMessageNo` INT NOT NULL COMMENT '活動留言編號(主鍵)',
  `actNo` INT NOT NULL COMMENT '活動編號(外來鍵)',
  `memberNo` INT(10) NOT NULL COMMENT '會員編號(外來鍵)',
  `actMessageContent` VARCHAR(100) NOT NULL COMMENT '活動留言內容',
  `actMessageTime` DATETIME NOT NULL COMMENT '活動留言時間',
  `actMessageState` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '留言狀態',
  PRIMARY KEY (`actMessageNo`),
  FOREIGN KEY (`actNo`) REFERENCES ACTIVITY(`actNo`),
  FOREIGN KEY (`memberNo`) REFERENCES MEMBER(`memberNo`)
) COMMENT = '活動留言';

INSERT INTO
  `ACTIVITYMESSAGE`(`actMessageNo`,`actNo`,`memberNo`,`actMessageContent`,`actMessageTime`,`actMessageState`)
VALUES
  (3001,1001,1,'好讚','2021-08-29 00:00:00',1),
  (3002,1002,2,'還行','2021-08-29 00:00:00',1);
  
#======================================分隔線==================================================
CREATE TABLE `ACTIVITYMESSAGEREPORT` (
  `actMessageNo` INT NOT NULL COMMENT '活動留言編號(主鍵,外來鍵)',
  `memberNo` INT(10) NOT NULL COMMENT '會員編號(主鍵,外來鍵)',
  `actMessageReportReason` VARCHAR(20) NOT NULL COMMENT '活動留言檢舉原因',
  `actMessageReportState` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '活動留言檢舉狀態',
  PRIMARY KEY (`actMessageNo`,`memberNo`),
  FOREIGN KEY (`actMessageNo`) REFERENCES ACTIVITYMESSAGE(`actMessageNo`),
  FOREIGN KEY (`memberNo`) REFERENCES MEMBER(`memberNo`)
) COMMENT = '活動留言檢舉';

INSERT INTO
  `ACTIVITYMESSAGEREPORT`(`actMessageNo`,`memberNo`,`actMessageReportReason`,`actMessageReportState`)
VALUES
  (3001,1,'人身攻擊',1),
  (3002,2,'言語含不雅字眼',1);
#======================================分隔線==================================================
create table PRODUCTSORT(
prodsortNo tinyint(1) not null comment"商品類別編號",
prodsortName varchar(10) comment"商品類別名稱"
)comment = "商品類別";

insert into 
	PRODUCTSORT(prodsortNo,prodsortName)
values
	(1,"票券"),
    (2,"保養品"),
    (3,"香水"),
    (4,"御守"),
    (5,"衣著"),
    (6,"珠寶");

#======================================分隔線==================================================
create table `ORDER`(
orderNo int(10) zerofill not null auto_increment comment"訂單編號" primary key,
memberNo int  not null comment"會員編號",
orderstate tinyint(1) comment"訂單狀態",
total int comment"總金額",
orderer varchar(20) not null default '1' comment"收件姓名",
address varchar(255) not null comment"收件地址",
tel char(10) not null comment"收件電話",
orderDate datetime comment"訂單成立時間",
creditcardNum char(16) comment"信用卡號",
paymentmeth tinyint(1) comment"付款方式",
deliverymeth tinyint(1) comment"交貨方式"
)comment = "訂單";

insert into `ORDER`(memberNo,orderstate,total,orderer,address,tel,orderDate,creditcardNum,paymentmeth,deliverymeth)
values (1,1,1600,"aaa","新北市板橋區文化路一段25號","0900000000",'1980-12-17',1234567887654321,1,1),
 (2,2,8000,"bbb","桃園市觀音區經建二路11號","0911111111",'1980-12-18',1234567887654322,2,2);

#======================================分隔線==================================================
create table ORDERLIST(
orderNo int(10) zerofill not null comment"訂單編號" references`ORDER`(orderNo),
prodNo int(3) zerofill not null comment"商品編號"references PRODUCT(prodNo),
quantity int(10) comment"商品數量",
price int(10) comment"商品單價"
)comment = "訂單明細";

alter table ORDERLIST add PRIMARY key(orderNo,prodNo);
insert into ORDERLIST(orderNo,prodNo,quantity,price)
values(1,1,2,1600),
(2,2,1,8000);

#======================================分隔線==================================================
create table PRODUCT(
prodNo int(3) zerofill not null auto_increment comment"商品編號" primary key,
prodsortNo tinyint(1) not null comment"商品類別編號" references PRODUCTSORT(prodsortNo),
prodName varchar(20) comment"商品名稱",
price int(10) comment"商品單價",
indroce varchar(500) comment"商品介紹",
prodPic1 varchar(255) comment"商品圖片一",
prodPic2 varchar(255) comment"商品圖片二",
prodPic3 varchar(255) comment"商品圖片三",
prodState tinyint(1) default '1' comment"商品狀態",
postTime datetime
)comment = "商品";

insert into PRODUCT(prodsortNo,prodName,price,indroce,prodPic1,prodPic2,prodPic3,prodState,postTime)
values 
(1,"mealticket",800,"just mealticket","ticket1.jpg","ticket2.jpg","ticket3.jpg",1,now()),
(2,"SK2",8000,"just skincare products","skincare1.jpg","skincare2.jpg","skincare3.jpg",1,now()),
(3,"香水",500,"just perfume","perfume1.jpg","perfume2.jpg","perfume3.jpg",1,now()),
(4,"姻緣御守",100,"just amulet","amulet1.jpg","amulet2.jpg","amulet3.jpg",1,now()),
(5,"潮T",1000,"just T-shirt","T-shirt1.jpg","T-shirt2.jpg","T-shirt3.jpg",1,now()),
(6,"鑽戒",80000,"just diamond","diamond1.jpg","diamond2.jpg","diamond3.jpg",1,now()),
(1,"movieticket",400,"just movieticket","ticket4.jpg","ticket5.jpg","ticket6.jpg",1,now()),
(2,"SK3",9000,"just skincare products","skincare4.jpg","skincare5.jpg","skincare6.jpg",1,now()),
(3,"香水2",400,"just perfume2","perfume4.jpg","perfume5.jpg","perfume6.jpg",1,now()),
(4,"健康御守",200,"just amulet2","amulet4.jpg","amulet5.jpg","amulet6.jpg",1,now()),
(5,"潮T2",1500,"just T-shirt2","T-shirt4.jpg","T-shirt5.jpg","T-shirt6.jpg",1,now()),
(6,"珠寶",88000,"just jerwerl","diamond4.jpg","diamond5.jpg","diamond6.jpg",1,now());

#======================================分隔線==================================================
create table MEMPERSONALPAGE(
postNo int not null auto_increment comment '貼文編號' ,
memberNo int not null comment '會員編號',
postPhoto mediumblob not null comment '貼文圖片',
postContent varchar(500) not null comment '貼文內容',
postTime datetime not null comment '貼文發表時間',
numOfLike int not null default 0 comment '按讚數',
postState tinyint(1) not null  default 1 comment '貼文狀態',
constraint PK_MPP_postNo primary key (postNo),
constraint FK_MPP_memberNo foreign key (memberNo) references `MEMBER` (memberNo)
) comment '會員個人頁面貼文';

INSERT INTO MEMPERSONALPAGE VALUES (null, 1, '', '我終於打到莫德那了!!', '2021-06-15', 23, 1);
INSERT INTO MEMPERSONALPAGE VALUES (null, 2, '', '樂透上看5億! 中了我該如何花呢? ', '2021-06-19', 12, 1);

#======================================分隔線==================================================
create table EXPERTPERSONALPAGE(
postNo int not null auto_increment comment '貼文編號',
expertNo int not null comment '專家編號',
postPhoto mediumblob not null comment '貼文圖片',
postContent varchar(500) not null comment '貼文內容',
postTime datetime not null comment '貼文發表時間',
numOfLike int not null  default 0 comment '按讚數',
postState tinyint(1) not null  default 1 comment '貼文狀態',
constraint PK_EPP_postNo primary key (postNo),
constraint FK_EPP_expertNo foreign key (expertNo) references EXPERT (expertNo)
) comment '專家個人頁面貼文';

INSERT INTO EXPERTPERSONALPAGE VALUES (null, 1, '', '好久沒帶我的阿柴去散步...', '2021-05-02', 34, 1);
INSERT INTO EXPERTPERSONALPAGE VALUES (null, 2, '', '小花腮紅又出新色了! 收~~', '2021-05-05', 100, 1);
INSERT INTO EXPERTPERSONALPAGE VALUES (null, 3, '', '今天藏壽司扭蛋居然完全槓龜! 機器壞了嗎QQ', '2021-05-09', 76, 1);

#======================================分隔線==================================================
create table POSTTYPE(
postTypeNo int not null auto_increment comment '文章類別編號',
postType varchar(20) not null comment '文章類別',
constraint PK_POSTTYPE_TypeNo primary key (postTypeNo)
) comment '文章類別';

INSERT INTO POSTTYPE VALUES (null, '旅遊');
INSERT INTO POSTTYPE VALUES (null, '吃喝');
INSERT INTO POSTTYPE VALUES (null, '兩性關係');

#======================================分隔線==================================================
create table POST(
postNo int not null auto_increment comment '文章編號',
postTypeNo int not null comment '文章類別編號',
memberNo int not null comment '會員編號',
postContent varchar(500) not null comment '文章內容',
postTime datetime not null comment '文章發表時間',
postState tinyint(1) not null  default 1 comment '文章狀態',
mesCount int not null  default 0 comment '文章留言數',
numOfLike int not null  default 0 comment '按讚數',
constraint PK_POST_postNo primary key (postNo),
constraint FK_POST_postTypeNo foreign key (postTypeNo) references POSTTYPE (postTypeNo),
constraint FK_POST_memberNo foreign key (memberNo) references `MEMBER` (memberNo)
) comment '文章';

INSERT INTO POST VALUES (null, 1, 1, '花東縱谷一日遊', '2021-05-12', 1, 1, 10);
INSERT INTO POST VALUES (null, 2, 2, '淡水老街的阿給', '2021-05-13', 1, 1, 22);

#======================================分隔線==================================================
create table POSTREPORT(
postNo int not null comment '文章編號', 
memberNo int not null comment '檢舉人會員編號',
postRepTime datetime not null comment '檢舉時間',
postRepFor varchar(20) not null comment '檢舉原因',
mesRevState tinyint (1) not null  default 1 comment '審核狀態',
primary key (postNo, memberNo),
constraint FK_PR_postNo foreign key (postNo) references POST (postNo),
constraint FK_PR_memberNo foreign key (memberNo) references `MEMBER` (memberNo)
) comment '文章檢舉';

INSERT INTO POSTREPORT VALUES (1, 3, '2021-05-14', 1, 1);
INSERT INTO POSTREPORT VALUES (2, 3, '2021-05-15', 2, 1);

#======================================分隔線==================================================
create table POSTMESSAGE(
mesNo int not null auto_increment comment '留言編號',
memberNo int not null comment '會員編號',
postNo int not null comment '文章編號',
mesContent varchar(100) not null comment '留言內容',
mesTime datetime not null comment '留言時間',
mesState tinyint (1) not null  default 1 comment '留言狀態',
constraint PK_PM_mesNo primary key(mesNo),
constraint FK_PM_memberNo foreign key (memberNo) references `MEMBER` (memberNo),
constraint FK_PM_postNo foreign key (postNo) references POST (postNo)
) comment '文章留言'; 

INSERT INTO POSTMESSAGE VALUES (null, 1, 1, '慕谷慕魚超美的!', '2021-05-12', 1);
INSERT INTO POSTMESSAGE VALUES (null, 2, 2, '真假?!阿給倒了?!~', '2021-05-13', 1);
INSERT INTO POSTMESSAGE VALUES (null, 2, 1, '愛現~~真無聊', '2021-05-12', 1);

#======================================分隔線==================================================
create table MESSAGEREPORT(
memberNo int not null comment '檢舉人會員編號',
mesNo int not null comment '留言編號',
mesRepTime datetime not null comment '檢舉時間',
mesRepFor varchar(20) not null comment '檢舉原因',
mesRevState tinyint(1) not null default 1 comment '審核狀態',
primary key (memberNo, mesNo),
constraint FK_MR_memberNo foreign key (memberNo) references `MEMBER` (memberNo),
constraint FK_MR_mestNo foreign key (mesNo) references POSTMESSAGE (mesNo)
) comment '文章留言檢舉'; 

INSERT INTO MESSAGEREPORT VALUES (3, 2, '2021-05-14', 1, 1);
INSERT INTO MESSAGEREPORT VALUES (3, 3, '2021-05-13', 2, 1);

#======================================分隔線==================================================
create table EXPERTARTICLE(
articleNo int not null auto_increment comment '專家專欄編號',
expertNo int not null comment '專家編號',
articleContent varchar(500) not null comment '文章內容',
articlePhoto mediumblob not null comment '文章圖片',
articleTime datetime not null comment '文章發表時間',
articleState tinyint not null  default 1 comment '文章狀態',
numOfLike int not null  default 0 comment '按讚數',
constraint PK_ExpArticle_articleNo primary key (articleNo),
constraint FK_ExpArticle_articleNo foreign key (expertNo) references EXPERT (expertNo)
) comment '專家專欄';

INSERT INTO EXPERTARTICLE VALUES (null, 1, '總是不知道該如何開啟話題嗎?', '' ,'2021-05-14', 1, 10);
INSERT INTO EXPERTARTICLE VALUES (null, 2, '擺脫大叔大嬸Look嗎?', '' ,'2021-05-18', 1, 30);
INSERT INTO EXPERTARTICLE VALUES (null, 3, '戀人未滿', '' ,'2021-05-21', 1, 14);

#======================================分隔線==================================================

#======================================分隔線==================================================
-- 以下測試變量用:
-- show variables like '%auto_inc%';
-- show session variables like '%auto_inc%';  -- //session變量
-- show global variables  like '%auto_inc%';  -- //global變量

-- 以下測試環境的 版本、SSL、 字元編碼用:
-- select version();
-- show variables like '%ssl%';  [ 或執行 mysql> \s ]

-- show variables like '%character%';