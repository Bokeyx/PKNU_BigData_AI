/* 회원정보에서 회원아이디, 이름, 직업, 취미 조회 
<구문 작성 시 순서>
 1. 조회하고자하는 컬럼(데이터)들이 속한 [테이블 찾기]
 2. 해당 컬럼의 영문명 찾기
 3. 관계조건 (PK=FK)이 있는지 찾기
    - 테이블이 2개 이상 사용하는 경우만 해당됨
 4. 일반조건이 있는지 찾기
 5. 정렬조건 있는지 찾기(보통 예의상 정렬합니다.)
*/
SELECT Mem_id, Mem_name, Mem_job, Mem_like 
FROM Member;

/* 상품정보 전체 모든 컬럼 조회 */
SELECT * 
FROM Prod;

/* 상품분류 정보 전체 모든 컬럼 조회 */
SELECT * 
FROM Lprod;

/* 거래처정보 전체 모든 컬럼 조회 */
SELECT * 
FROM Buyer;

/* 구매정보 전체 모든 컬럼 조회 */
SELECT * 
FROM Cart;

/* 상품명, 매입가격, 소비자가격, 판매가격 조회 */
-- 별칭 추가
SELECT 
    Prod_name as pnm, 
    Prod_cost pct, 
    Prod_price "ppc", 
    Prod_sale as "ps"
FROM Prod
-- 정렬 ; 오름차순(Asc), 내림차순(Desc)
-- ORDER BY Prod_name Asc;
-- ORDER BY 1 Asc;
ORDER BY pnm ASC;

/* 
상품아이디, 상품명 조회
 - 상품명이 '남성 겨울 라운드 셔츠 1' 인것에 대해서만
 - 별칭: 아이디는 id로, 상품명은 name으로...
 - 정렬은 상품명을 기준으로 내림차순

<조회 구문을 Oracle이 해석하는 순서>
 1. SELECT
 2. FROM 테이블
 3. WHERE
 4. 조회할 컬럼
 5. 정렬
*/
SELECT 
    Prod_id as id,
    Prod_name as name
FROM Prod
WHERE Prod_name = '남성 겨울 라운드 셔츠 1'
ORDER BY Prod_name DESC;

/*
회원아이디, 마일리지, 마일리지를 12로 나눈값을 조회
 - 별칭사용, id, mileage, mileage12
 - 정렬은 mileage12를 기준으로 내림차순
*/
-- 별칭을 사용해야 하는 경우
--  : 컬럼을 이용하여 연산 또는 함수를 이용한 경우
SELECT 
    Mem_id AS id,
    Mem_mileage AS mileage,
    Mem_mileage / 12 AS mileage12
FROM Member
ORDER BY mileage12 DESC;

/*
상품코드, 상품명, 판매금액 조회하기
 - 단, 판매금액 = 판매가격 * 55 한 값을 사용
 - 별칭이 필요한 경우 별칭을 자유롭게 사용
 - 조회 시 판매금액이 1000 이상인 경우만 조회
 - 정렬은 판매금액을 기준으로 오름차순
*/
SELECT 
    Prod_id AS id,
    prod_name AS name,
    prod_sale * 55 AS sale
FROM Prod
WHERE prod_sale * 55 >= 1000
ORDER BY sale ASC;