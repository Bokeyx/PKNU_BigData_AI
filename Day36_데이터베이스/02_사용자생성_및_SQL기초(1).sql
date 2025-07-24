-- 한줄 주석 달기
/*
 여러 행 주석 달기
 행 단위 주석...
*/
/*
 - system 계정은 DB 절대 관리자 계쩡으로 관리자만 알고 있어야함
 - 개발이나 사용자들에게는 별도로 사용자 계정을 생성하여 제공
 - Oracle 버전 17버전 이상부터는 최초에 한번 아래 구문 실행
  -> 아래 구문을 실행하지 않으면, 표준 SQL 구문 작성시에 @ 또는 #을 붙여주어야 합니다.(불편함)
  Alter session set "_ORACLE_SCRIPT"=True;
*/
Alter session set "_ORACLE_SCRIPT"=True;

/*
 ** 사용자 생성은 system 절대 관리자 계정으로 접속해야 생성이 가능함
*/

/* 사용자 객체 생성하기 */
-- 계정명: busan
-- 패스워드: dbdb
-- 생성(Create), 사용자(User), 패스워드(Identified By)
Create User busan
    Identified By dbdb;0
    
/* 사용자 수정하기 */
-- 사용자 수정은: 패스워드만 수정 가능합니다.
-- 객체수정(Alter), 사용자(User)
Alter User busan
    Identified By dbdb2;
    
/* 사용자 삭제하기 */
-- 객체삭제(Drop)
Drop User busan;

/* 객체(사용자, 테이블, 등등)컨트롤 문 */
-- 생성(Create), 수정(Alter), 삭제(Drop)

/*
<사용자 생성 순서>
 1. Create를 통해 사용자 생성
 2. 생성된 사용자에 접근권한을 부여해야 DB접속이 가능함
*/

/* 생성된 사용자에게 권한 부여하기 */
-- 권한부여(Grant)
-- Connect(접근권한), Resource(자원접근권한), DBA(데이터베이스 관리자 권한)
Grant Connect, Resource, DBA To busan;

/* 생성된 권한 회수(제거) 하기 */
-- Revoke(권한 회수)
Revoke Connect, Resource, DBA From busan