DROP DATABASE IF EXISTS TKPM_QUANLYTHUVIEN;
CREATE DATABASE TKPM_QUANLYTHUVIEN;
USE TKPM_QUANLYTHUVIEN;

CREATE TABLE ACCOUNT(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	FULLNAME VARCHAR(255),
	ADDRESS VARCHAR(100),
	GENDER BOOLEAN,
	BIRTHDAY TIMESTAMP,
	ISDELETED BOOLEAN,
	ISREADER BOOLEAN,
	ROLE INT,
	USERNAME VARCHAR(100),
	PASSWORD VARCHAR(100),
	UPDATEDDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UPDATEDACCOUNT INT
);

CREATE TABLE SYSTEMCONSTANTS(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(100),
	TYPE INT,
	VALUE VARCHAR(255),
	ISDELETED BOOLEAN,
	UPDATEDDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UPDATEDACCOUNT INT
);

CREATE TABLE BOOKINFORMATION(
	ISBN VARCHAR(15) PRIMARY KEY,
	NAME VARCHAR(255),
	AUTHOR INT,
	PUBLISHER INT,
	RELEASEDATE TIMESTAMP,
	TYPE INT,
	LOCATION VARCHAR(25),
	PATH VARCHAR(255),
	ISDELETED BOOLEAN
);

CREATE TABLE BOOK(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	ISBN VARCHAR(15),
	ISDELETED BOOLEAN,
	STATUS INT
);

CREATE TABLE AUTHOR(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(255),
	ISDELETED BOOLEAN,
	UPDATEDDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UPDATEDACCOUNT INT
);

CREATE TABLE PUBLISHER(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(255),
	ISDELETED BOOLEAN,
	UPDATEDDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UPDATEDACCOUNT INT
);

CREATE TABLE LIBRARYCARD(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	CREATEDDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	DURATION INT,
	ACCOUNTID INT,
	ISDELETED BOOLEAN
);

CREATE TABLE RENTINGSLIP(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	ACCOUNTID INT,
	CREATEDDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	ISDELETED BOOLEAN
);

CREATE TABLE REPAYSLIP(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	ACCOUNTID INT,
	CREATEDDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	ISDELETED BOOLEAN,
	LOSTDATE TIMESTAMP,
	FEE BIGINT
);

CREATE TABLE RENTINGBOOK(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	SLIPID INT,
	BOOKID INT,
	STATUS INT
);


/*ROLE OF SYSTEM
	QUAN TRI HE THONG			1
	QUAN TRI VIEN				2
	THU THU						3
	DOC GIA						4
*/
#ACCOUNT 
INSERT INTO ACCOUNT(FULLNAME, ADDRESS, GENDER, BIRTHDAY, ISDELETED, ISREADER, ROLE, USERNAME, PASSWORD, UPDATEDACCOUNT)
VALUES(
	'Võ Thanh Hiếu',
	'Hồ Chí Minh',
	true,
	'1996-7-13',
	false,
	false,
	1,
	'zannaghazi',
	'zannaghazi',
	1
);
INSERT INTO ACCOUNT(FULLNAME, ADDRESS, GENDER, BIRTHDAY, ISDELETED, ISREADER, ROLE, USERNAME, PASSWORD, UPDATEDACCOUNT)
VALUES(
	'Nguyễn Thái Hoàng',
	'Hồ Chí Minh',
	true,
	'1996-1-1',
	false,
	false,
	2,
	'hoangnt',
	'admin',
	1
);
INSERT INTO ACCOUNT(FULLNAME, ADDRESS, GENDER, BIRTHDAY, ISDELETED, ISREADER, ROLE, USERNAME, PASSWORD, UPDATEDACCOUNT)
VALUES(
	'Võ Xuân Hiển',
	'Hồ Chí Minh',
	true,
	'1996-1-1',
	false,
	false,
	3,
	'hienvx',
	'thuthu',
	1
);
INSERT INTO ACCOUNT(FULLNAME, ADDRESS, GENDER, BIRTHDAY, ISDELETED, ISREADER, ROLE, USERNAME, PASSWORD, UPDATEDACCOUNT)
VALUES(
	'Trần Thị Hổ',
	'California',
	false,
	'1996-1-1',
	false,
	false,
	4,
	'docgia',
	'docgia',
	1
);

#AUTHOR
INSERT INTO AUTHOR(NAME, ISDELETED, UPDATEDACCOUNT)
VALUES ('J. K. Rowling', false, 1);

#PUBLISHER
INSERT INTO PUBLISHER(NAME, ISDELETED, UPDATEDACCOUNT)
VALUES ('Nhà xuất bản trẻ', false, 1);

/*TYPE OF BOOK INFORMATION (ENUM)
	Chính trị – pháp luật				1
	Khoa học công nghệ – Kinh tế		2
	Văn hóa xã hội – Lịch sử			3
	Văn học nghệ thuật					4
	Giáo trình							5
	Truyện, tiểu thuyết					6
	Tâm lý, tâm linh, tôn giáo			7
	Sách thiếu nhi						8
*/
/*LOCATION FORMAT: Tầng 1, Dãy H, Kệ 10, Hàng số 4 --> 1-H-10-4*/
INSERT INTO BOOKINFORMATION (ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)
VALUES (
	'0747532699',
	'Harry Potter và Hòn đá phù thủy',
	1,
	1,
	'2000-9-1',
	6,
	'1-H-10-1',
	'6/Harry_Potter_1.png',
	false
);
INSERT INTO BOOKINFORMATION (ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)
VALUES (
	'0747528492',
	'Harry Potter và Phòng chứa bí mật',
	1,
	1,
	'2001-2-1',
	6,
	'1-H-10-1',
	'6/Harry_Potter_2.png',
	false
);
INSERT INTO BOOKINFORMATION (ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)
VALUES (
	'0747542155',
	'Harry Potter và tên tù nhân ngục Azkaban',
	1,
	1,
	'2002-12-1',
	6,
	'1-H-10-2',
	'6/Harry_Potter_3.png',
	false
);
INSERT INTO BOOKINFORMATION (ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)
VALUES (
	'074754624X',
	'Harry Potter và chiếc cốc lửa',
	1,
	1,
	'2003-4-6',
	6,
	'1-H-10-2',
	'6/Harry_Potter_4.png',
	false
);
INSERT INTO BOOKINFORMATION (ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)
VALUES (
	'9780439567619',
	'Harry Potter và Hội phượng hoàng',
	1,
	1,
	'2003-7-21',
	6,
	'1-H-10-3',
	'6/Harry_Potter_5.png',
	false
);
INSERT INTO BOOKINFORMATION (ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)
VALUES (
	'9780747532743',
	'Harry Potter và Hoàng tử lai',
	1,
	1,
	'2005-7-16',
	6,
	'1-H-10-3',
	'6/Harry_Potter_6.png',
	false
);
INSERT INTO BOOKINFORMATION (ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)
VALUES (
	'9780545010221',
	'Harry Potter và Bảo bối tử thần',
	1,
	1,
	'2007-10-27',
	6,
	'1-H-10-3',
	'6/Harry_Potter_7.png',
	false
);

/*BOOK STATUS 
	Sẵn sàng			1
	Đang mượn			2
	Trễ hạn				3
	Bị mất				4
	Thanh lý			5
*/
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 2);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 3);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 4);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747532699', false, 5);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747528492', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747528492', false, 2);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747528492', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747528492', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747528492', false, 5);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747542155', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747542155', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747542155', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747542155', false, 1);
INSERT INTO BOOK(ISBN, ISDELETED, STATUS)
VALUES ('0747542155', false, 1);

/*RENTINGSLIP*/
INSERT INTO RENTINGSLIP(ACCOUNTID, ISDELETED)
VALUES (4, false);

/*STATUS
	Đang mượn		1
	Đã trả			2
	Đã mất			3
*/
INSERT INTO RENTINGBOOK(SLIPID, BOOKID, STATUS)
VALUES(1, 2, 1);
INSERT INTO RENTINGBOOK(SLIPID, BOOKID, STATUS)
VALUES(1, 6, 2);
INSERT INTO RENTINGBOOK(SLIPID, BOOKID, STATUS)
VALUES(1, 10, 1);