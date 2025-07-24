/*
상품분류코드와 거래처코드를 조회해 주세요.
 - 상품분류코드를 기준으로 오름차순,
 - 거래처코드를 기준으로 내림차순 정렬해주세요.
*/
SELECT Prod_lgu, Prod_Buyer
FROM Prod
ORDER BY Prod_lgu ASC, Prod_Buyer DESC;

/* 
<중복제거>
 - DISTINCT: 행단위 중복제거 수행
           : SELECT 다음에 정의함
           : DISTINCT 다음에 조회할 컬럼 정의
*/
SELECT DISTINCT Prod_lgu, Prod_Buyer
FROM Prod
ORDER BY Prod_lgu ASC, Prod_Buyer DESC;


/*
<연산자>
 - 산술 연산자
   +, -, *, /

 - 조건(비교)연산자
   >, <, >=, <=, != (<>), =
   
 - 논리 연산자
   AND, OR
*/


/*
상품의 판매가격이 17만원 이상인 데이터 조회하기
 - 조회컬럼: 상품코드, 상품명, 판매가격
 - 정렬: 판매가격을 기준으로 내림차순
*/
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale >= 170000
ORDER BY Prod_sale DESC;


/*
상품의 판매가격이 17만원 이상이고
20만원 이하인 데이터 조회하기
 - 조회컬럼: 상품코드, 상품명, 판매가격
 - 정렬: 상품명을 기준으로 오름차순,
        판매가격을 기준으로 내림차순
*/
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale >= 170000 AND Prod_sale <= 200000
ORDER BY Prod_name ASC, Prod_sale DESC;


/*
상품분류코드가 P201이고,
상품의 판매가격이 17만원 또는
20만원 이하인 데이터 조회하기
 - 조회컬럼: 상품코드, 상품명, 판매가격, 상품분류코드
 - 정렬: 상품분류코드을 기준으로 오름차순,
        판매가격을 기준으로 내림차순
*/
SELECT Prod_id, Prod_name, Prod_sale, Prod_lgu
FROM Prod
WHERE Prod_lgu = 'P201' 
   AND (Prod_sale = 170000 OR Prod_sale <= 200000)
ORDER BY Prod_lgu ASC, Prod_sale DESC;


/*
상품판매가격이 15만원, 17만원 33만원인 상품들을 조회
 - 조회 컬럼: 상품코드, 상품명, 판매가격
 - 정렬은 상품명 기준 오름차순
*/
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale = 150000 
   OR Prod_sale = 170000 
   OR Prod_sale = 330000
ORDER BY Prod_name ASC;


/*
논리연산자 OR인 경우에는 IN() 함수 사용 가능
 - 국제표준에 따른 함수임: 모든 데이터베이스에서 사용가능
 - 사용 조건: 하나의 컬럼에서 값이 다른 조건으로 조회하고자 할 때
           : 포함관계를 나타냅니다. (포함되어 있으면 TRUE)
*/
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale IN (150000, 170000, 330000)
ORDER BY Prod_name ASC;


/* 
정렬에 사용할 수 있는 컬럼
 - FROM의 테이블 내 모든 것
 - SELECT한 컬럼들의 모든 것
*/
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale IN (150000, 170000, 330000)
ORDER BY Prod_lgu ASC;


/*
상품판매가격이 15만원, 17만원 33만원이 아닌 상품들을 조회
 - 조회 컬럼: 상품코드, 상품명, 판매가격
 - 정렬은 상품명 기준 오름차순
*/
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale != 150000 
  AND Prod_sale != 170000 
  AND Prod_sale != 330000
ORDER BY Prod_name ASC;

SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale NOT IN (150000, 170000, 330000)
ORDER BY Prod_lgu ASC;


/*
현재까지 주문한 [회원의 수]를 확인하려고 합니다.
주문 내역이 있는 회원아이디만 조회
 - 오름차순 정렬
*/
SELECT DISTINCT Cart_member
FROM Cart
ORDER BY Cart_member ASC;


-- 행의 수 조회: count()
SELECT COUNT(DISTINCT Cart_member)
FROM Cart
ORDER BY Cart_member ASC;


/* 회원 전체 수 조회하기 */
SELECT COUNT(*) as cnt
FROM MEMBER;


/*
한번도 주문한 적이 없는 회원의 정보 조회하기
 - 회원아이디, 이름 조회하기
 - 정렬은 이름 기준 오름차순
*/
SELECT m.MEM_ID, m.Mem_name
FROM Member m
LEFT JOIN Cart c On m.Mem_id = c.Cart_member
WHERE c.Cart_member IS NULL
ORDER BY m.Mem_name ASC;


/*
<서브쿼리>
 - SELECT문 안에 SELECT문을 작성하는 방식
*/
SELECT Mem_id, Mem_name
FROM Member
-- <서브쿼리 방식1>
-- 서브쿼리의 SELECT 결과: 단일컬럼의 다중행 결과 방식 사용 가능
WHERE Mem_id NOT IN(
    SELECT DISTINCT Cart_member
    FROM Cart
    )
ORDER BY Mem_name ASC;


/*
주문한 내역이 있는 회원의 정보 조회하기
 - 회원아이디, 이름 조회하기
 - 단, 주문수량이 10개 이상인 회원들만 조회하기
 - 정렬은 이름 기준 오름차순
*/
SELECT Mem_id, Mem_name
FROM Member
WHERE Mem_id IN(
    SELECT DISTINCT Cart_member
    FROM Cart
    WHERE Cart_QTY >=10
    )
ORDER BY Mem_name ASC;

SELECT DISTINCT m.MEM_ID, m.Mem_name
FROM Member m
LEFT JOIN Cart c On m.Mem_id = c.Cart_member
WHERE c.Cart_qty >= 10
ORDER BY m.Mem_name ASC;


/*
상품분류코드와 상품분류명을 조회
상품정보가 없는 상품분류정보를 조회
 - 정렬은 상품분류코드를 기준으로 오름차순
*/
SELECT Lprod_gu, Lprod_nm
FROM Lprod
WHERE Lprod_gu not in(
    SELECT Prod_lgu
    FROM Prod
    )
ORDER BY Lprod_gu ASC;

SELECT l.Lprod_gu, l.Lprod_nm
FROM Lprod l
LEFT JOIN Prod p On l.Lprod_gu = p.Prod_lgu
Where p.Prod_lgu IS NULL
ORDER BY Lprod_gu ASC;

/*
회원아이디와 마일리지 조회하기
 - 회원아이디가 a001인 회원의 마일리지값 이상인 회원들만 조회
 - 정렬은 아이디 기준 오름차순
*/
SELECT Mem_id, Mem_mileage
FROM Member
-- (서브쿼리 방법2)
--  - 비교연산자 뒤에 서브쿼리를 사용하는 경우에는
--   -> 단일컬럼의 단일행 결과값만 허용합니다.
WHERE Mem_mileage >= (SELECT Mem_mileage
                      FROM Member
                      Where Mem_id = 'a001'
                      )
ORDER BY Mem_id ASC;

SELECT m1.Mem_id, m1.Mem_mileage
FROM Member m1
INNER JOIN Member m2
  ON m1.Mem_mileage >= m2.Mem_mileage
WHERE m2.Mem_id = 'a001'
ORDER BY m1.Mem_id ASC;

/*
상품명 '모니터 삼성전자15인치칼라'인 상품을
구매한 회원의 아이디, 이름을 조회해주세요.
 - 정렬은 이름 기준 오름차순
*/
SELECT Mem_id, Mem_name
FROM Member
WHERE Mem_id IN(SELECT Cart_member
                FROM Cart
                WHERE Cart_prod IN(SELECT Prod_id
                                   FROM Prod
                                   WHERE Prod_name = '모니터 삼성전자15인치칼라'
                                   )
                )
ORDER BY Mem_name ASC;

SELECT DISTINCT m.Mem_id, m.Mem_name
FROM Member m
LEFT JOIN Cart c ON m.Mem_id = c.Cart_member
LEFT JOIN Prod p ON c.Cart_prod = p.Prod_id
WHERE p.Prod_name = '모니터 삼성전자15인치칼라'
ORDER BY m.Mem_name ASC;


/*
구매정보를 조회하려고 합니다.
 - 조회컬럼: 회원아이디, 회원이름, 주문번호, 주문상품코드, 구매수량
 - 정렬: 회원이름 기준 오름차순, 구매번호 내림차순
*/
-- SELECT 뒤에 조회 할 컬럼명 대신 사용하는 서브쿼리는
-- : 단일컬럼의 단일행 결과만 조회되는 방식으로 사용해야함
SELECT 
    Cart_member,
    (SELECT Mem_name 
    FROM Member 
    WHERE Mem_id = Cart_member) AS Mem_name,
    Cart_no, 
    Cart_prod, 
    Cart_qty
FROM Cart
ORDER BY Mem_name DESC;

SELECT c.Cart_member, m.Mem_name, c.Cart_no, c.Cart_prod, c.Cart_qty
FROM Cart c
JOIN Member m ON c.Cart_member = m.Mem_id
ORDER BY m.Mem_name DESC;

/*
회원이 주문한 내역을 확인하고자 합니다.
 - 조회컬럼: 회원아이디, 회원이름, 상품명, 주문번호, 주문수량
 - 단, 상품의 판매가격이 1만원 이상인 제품을 구매한 회원에 대해서
 - 정렬 없음
*/
SELECT 
    Cart_member,
    (SELECT Mem_name 
     FROM Member 
     WHERE Mem_id = Cart_member) AS Mem_name,
    (SELECT Prod_name 
     FROM Prod 
     WHERE Prod_id = Cart_prod) AS prod_name,
    Cart_prod, 
    Cart_qty
FROM Cart
WHERE Cart_prod in(SELECT Prod_id
                       FROM Prod
                       WHERE Prod_sale >= 10000
                       );

SELECT c.Cart_member, m.Mem_name, p.Prod_name, c.Cart_prod, c.Cart_qty                       
From Cart c
JOIN Member m ON c.Cart_member = m.Mem_id
JOIN Prod p On c.Cart_prod = p.Prod_id
WHERE p.Prod_sale >= 10000;

-- Where 조건 튜닝하기
SELECT 
    Cart_member,
    (SELECT Mem_name 
     FROM Member 
     WHERE Mem_id = Cart_member) AS Mem_name,
    (SELECT Prod_name 
     FROM Prod 
     WHERE Prod_id = Cart_prod) AS prod_name,
    Cart_prod, 
    Cart_qty
FROM Cart
WHERE (SELECT Prod_sale
       FROM Prod
       WHERE Prod_id = Cart_prod) >= 10000;
       

/*
회원 이름, 취미 조회하기
 - 단, 회원의 취미가 수영이고,
 - 상품분류명이 컴퓨터제품이면서
 - 거래처명이 삼성컴퓨터인 제제품을 구매한 회원에 대해서만 조회
*/
SELECT Mem_name, Mem_like
FROM Member
WHERE Mem_like = '수영' 
AND Mem_id IN (
               SELECT Cart_member
               FROM Cart
               WHERE Cart_prod IN (
                                   SELECT Prod_id
                                   FROM Prod
                                   WHERE Prod_lgu IN(
                                                     SELECT Lprod_gu
                                                     FROM Lprod
                                                     Where Lprod_nm = '컴퓨터제품'
                                                     ) 
                                   AND Prod_buyer IN(
                                                     SELECT Buyer_id
                                                     FROM Buyer
                                                     WHERE Buyer_name = '삼성컴퓨터'
                                                     )
                                   )
               );
               

/*
김은대라는 회원이 주문한 상품의 상품분류코드와 상품분류명을 알고자함
 - 회원아이디, 회원이름, 상품분류코드, 상품분류명, 거래처명 조회
 - 정렬: 상품분류코드 오름차순
*/
SELECT DISTINCT
    (SELECT Mem_id
     FROM Member 
     WHERE Mem_id = Cart_member) AS Mem_id,
    (SELECT Mem_name 
     FROM Member 
     WHERE Mem_id = Cart_member) AS Mem_name,
    (SELECT Lprod_gu 
     FROM Lprod 
     WHERE Lprod_gu = (SELECT Prod_lgu 
                       FROM Prod 
                       WHERE Prod_id = Cart_Prod)) AS Lprod_gu,
    (SELECT Lprod_nm
     FROM Lprod
     WHERE Lprod_gu = (SELECT Prod_lgu
                       FROM Prod
                       WHERE Prod_id = Cart_Prod)) as Lprod_nm,
    (SELECT Buyer_name 
     FROM Buyer 
     WHERE Buyer_id = (SELECT Prod_buyer 
                       FROM Prod 
                       WHERE Prod_id = Cart_Prod)) AS Buyer_name
FROM Cart
WHERE Cart_member IN (SELECT Mem_id 
                      FROM Member 
                      WHERE Mem_name = '김은대')
ORDER BY Lprod_gu ASC;



/*
<조회 기본문법>
(필수 사용)------------------------------
SELECT
    조회할 컬럼 1, 컬럼2
FROM 조회할 테이블들
(아래는 필요에 따라 사용)------------------
WHERE 관계조건 or 일반조건
  AND 논리연산 조건들
   OR 논리연산 조건들
GROUP BY 그룹화할 컬럼들
  HAVING 그룹 조건들
ORDER BY 정렬 기준들

<입력 기본문법>
(필수 사용)------------------------------
INSERT INTO 입력할 테이블 (입력할 컬럼1, 컬럼2...)
    VALUES(입력할 값1, 값2...)
    
(테이블 내에 모든 컬럼들에게 입력 시)-------
INSERT INTO 입력할 테이블 (입력할 컬럼1, 컬럼2...)
    VALUES(입력할 값1, 값2...)
    
<수정 기본문법>
(필수 사용)------------------------------
UPDATE 수정할 테이블
    SET 수정할 컬럼1 = 수정할 값1
              컬럼2 = 수정할 값2 
(아래는 필요에 따라 사용)------------------
WHERE 관계조건 or 일반조건
  AND 논리연산 조건들
   OR 논리연산 조건들
   
<삭제 기본문법>
 *** 삭제는 행단위로 삭제가 됩니다.(열 삭제는 안됨)
(필수 사용)------------------------------
DELETE 삭제할 테이블 
(아래는 필요에 따라 사용)---
WHERE 관계조건 or 일반조건
  AND 논리연산 조건들
   OR 논리연산 조건들

   
<SQL 구문의 구분>
 - 문과 절로 이루어져 있음
 - 문: SELECT, INSERT, UPDATE, DELETE
 - 절: 
   * SELECT 문에서 사용하는 절
    - FROM, WHERE, GROUP BY, HAVING, ORDER BY
   * UPDATE 문에서 사용하는 절
    - SET, WHERE
   * DELETE 문에서 사용하는 절
    - FROM, WHERE
*/

/*
<서브쿼리(SubQuery)>
 - 서브쿼리를 사용할 수 있는 위치
   1. SELECT문 뒤에 컬럼명 대신 사용가능
     - (중요)규칙: 단일컬럼의 단일행 결과값이어야 함
   2. WHERE절 뒤에 비교연산(>, <, >=, <=, !=, =) 시 사용가능
     - (중요)규칙: 단일컬럼의 단일행 결과값이어야 함
   3. WHERE절 뒤에 IN(서브쿼리)함수에 사용가능
     - (중요)규칙: 단일컬럼의 단(일)중행 결과값이어야 함
   4. WHERE절 뒤에 EXISTS(서브쿼리) 함수에 사용가능
     - EXISTS(서브쿼리)개념: 서브쿼리의 결과값(행)이 존재하면 TRUE
     - (중요)규칙: 단(일)중컬럼의 단(일)중행 결과값이어야 함
   5. FROM절 뒤에 테이블 대신에 사용가능(별칭 사용해야함)
     - (중요)규칙: 단(일)중컬럼의 단(일)중행 결과값이어야 함
*/