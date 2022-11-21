CREATE TABLE User_Member (
user_Number varchar(20) not null, -- 회원번호   
user_ID varchar(20) not null, -- email
user_Password varchar(20), -- 비밀번호
user_Password_re varchar(20), -- 비밀번호학인
user_Date date, -- 생년월일
user_active varchar(3), -- 탈퇴여부
user_out_date date, -- 탈퇴날짜 
primary key (user_Number)
);

CREATE TABLE Genre (
genre_code varchar(20) not null, -- 장르코드
genre_name varchar(20), -- 장르명
primary key (genre_code)
);

create table movie_info
(
    m_code number(10),
    m_title varchar2(128),
    open_dt date,
    close_dt date,
    genre_code number(4),
    poster_path varchar2(256),
    rank number(2),
    audi_acc number(10),
    audits number(10),
    price number(5),
    CONSTRAINT movie_info PRIMARY KEY(m_code)
);

CREATE TABLE TICKET_LIST 
(
  USER_NUMBER VARCHAR2(20 BYTE) NOT NULL 
, M_CODE NUMBER(10, 0) NOT NULL 
, TICKET_DATE VARCHAR2(20 BYTE) NOT NULL 
, WATCH_DATE VARCHAR2(20 BYTE) NOT NULL 
, TICKET_NUM VARCHAR2(20 BYTE) NOT NULL 
);

CREATE TABLE WATCHED_LIST 
(
  USER_NUMBER VARCHAR2(20 BYTE) NOT NULL 
, M_CODE NUMBER(10, 0) NOT NULL 
, WATCH_DATE VARCHAR2(20 BYTE) NOT NULL 
, WATCH_NUM VARCHAR2(20 BYTE) NOT NULL 
);