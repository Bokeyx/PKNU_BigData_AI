/*
<조인(JOIN)>
 - 관계되는 테이블들의 데이터를 PK 및 FK를 이용하여
   조회하고자 할 때 사용하는 문법(JOIN문 이라고 칭함)
 - 지금까지 사용한 서브쿼리는 대부분 조인(JOIN)으로 처리 가능
 - 조인(JOIN) 문법은 국제표준 문법과 DB 자체 문법이 있음
 
<문법>
 SELECT 조회할 컬럼
 FROM 테이블 1, 조인명령 테이블2
                ON(관계조건 PK=FK
                   AND 일반조건
                   AND 일반조건)
               조인명령 테이블3
                ON(관계조건 PK=FK
                   AND 일반조건
                   AND 일반조건)
 WHERE 컬럼 조건
 GROUP BY 그룹 컬럼
   HAVING 그룹 조건
 ORDER BY 정렬 조건
 
<조인 조건>
 ** 국제표준 조인: CROSS, INNER, OUTER, SELF조인
 1. CROSS JOIN: 관계조건 (PK=FK) 없이 사용됨
                (사용되지 않습니다.)
 2. INNER JOIN: 관계조건 (PK=FK)이 성립하는 경우
                (가장 많이 사용됨,국제표준을 따르지 않아도됨
 3. OUTER JOIN: LEFT OUTER JOIN, RIGHT OUTER JOIN,
                FULL OUTER JOIN(사용되고 있지 않음)
    : INNER JOIN을 만족하면서,
      관계조건이 성립하지 않는 경우에도 조회
    : 있으면 있는데로, 없으면 NULL
    : 통계 처리시에 주로 사용됨 (국제표준을 무조건 따라야함)
 4. SELF JOIN: 자기 자신의 테이블을 두개로 정의 후 사용
             : 테이블에 별명을 붙여서 사용해야 함
 5. 자연적인 JOIN: 각 테이블의 PK=FK가 정의되지 않았으나,
                  컬럼명이 같은 경우
 6. 값에 의한 JOIN: 각 테이블의 PK=FK가 정의되지 않았으나,
                   컬럼의 값들이 같은 경우
*/

/*
<CROSS JOIN>
 - ** 관계조건 없이 모든 테이블의 행의 값들을 CROSS해서 조회하는 방식
 - 조회건수 = 테이블행수 * 테이블행수* ....
 - CROSS JOIN은 잘못 사용하게 되면 DB시스템에 부하가 발생할 수 있음
  -> 테이블 내에 데이터가 매우 많이 존재하는 경우에 시간 오래걸림
*/

--[CROSS JOIN 사용]--------------------------
/* 회원아이디, 회원이름, 주문번호 주문수량 조회 */
--[국제 표준 방식]
SELECT Mem_id, Mem_name, Cart_no, Cart_qty
FROM Member CROSS JOIN Cart;

--[일반 방식]
SELECT Mem_id, Mem_name, Cart_no, Cart_qty
FROM Member, Cart;


--[INNER JOIN 사용]--------------------------
/*
 - 일반적으로 불리우는 JOIN을 INNER JOIN이라 칭함
 - JOIN 방식 중 가장 많이 사용되는 방식
 - INNER JOIN은 사용되는 테이블의 관계가 같은 값들에 대해서만 조회
   (같지 않은 값들은 조회되지 않음)
 - 관계 조건(PK=FK)을 필수적으로 정의해야함
 - 국제표준과 일반방식 모두 사용가능하며,
 - 일반방식도 모든 DB에서 사용가능(표준처럼 사용함)
*/

/* 회원아이디, 회원이름, 주문번호 주문수량 조회 */
-- 1. 테이블 찾기: Member, Cart
-- 2. 관계조건 찾기: mem_id = cart_member
--    * 관계조건 갯수: (테이블 갯수 - 1)개 이상 있어야함
-- 3. 일반조건 찾기: 없음
-- 4. 그룹조건 찾기: 없음
-- 5. 조회할 컬럼: Mem_id, Mem_name, Cart_no, Cart_qty
-- 6. 정렬 조건 찾기: 없음(예의상 해주면 됩니다.)

--[일반 방식]
SELECT Mem_id, Mem_name, Cart_no, Cart_qty
FROM Member, Cart
WHERE Mem_id = Cart_member;

--[국제 표준 방식]
SELECT Mem_id, Mem_name, Cart_no, Cart_qty
FROM Member
INNER JOIN Cart ON Mem_id = Cart_member;

/*
회원아이디, 회원이름, 주문번호, 주문수량, 상품코드, 상품명 조회하기
 - 정렬: 회원이름 기준 오름, 주문번호 내림차순
 - 일반방식과 국제표준 방식 모두 작성
*/
--[일반 방식]
SELECT m.Mem_id, m.Mem_name, c.Cart_no, c.Cart_qty, p.Prod_name
FROM Member m, Cart c, prod p
WHERE m.Mem_id = c.Cart_member AND c.Cart_prod = p.Prod_id
ORDER BY m.Mem_name ASC, c.Cart_no DESC;

--[국제 표준 방식]
SELECT m.Mem_id, m.Mem_name, c.Cart_no, c.Cart_qty, p.Prod_name
FROM Member m
INNER JOIN Cart c ON m.Mem_id = c.Cart_member
INNER JOIN Prod p ON c.Cart_prod = p.Prod_id
ORDER BY m.Mem_name ASC, c.Cart_no DESC;

/* 
<테이블에 별칭 붙이기>
 - 테이블 2개 이상을 사용하는 경우에는 
   사용되는 컬럼들이 어느 테이블에 소속됐는지 모르기 때문에
   -> 테이블 별칭을 이용해서 소속을 알려주는 것이
      코드 이해하는데 명확합니다
 - 테이블 별칭 작성법: 테이블 이름 뒤에 스페이스(한칸 띄우고)
                     별칭으로 사용 할 이름 작성
                   : 테이블 이름이 긴 경우에 주로 사용됨
                   : 별칭은 압축된 이름 또는 하나의 단어를 주로 사용
*/
SELECT M.Mem_id, M.Mem_name, C.Cart_no, C.Cart_qty, P.Prod_name
FROM Member M
INNER JOIN Cart C ON M.Mem_id = C.Cart_member
INNER JOIN Prod P ON C.Cart_prod = p.Prod_id
ORDER BY M.Mem_name ASC, C.Cart_no DESC;

/*
주문 내역이 있는 회원정보 조회하기
 - 조회컬럼: 회원아이디, 회원이름, 주문수량, 상품명
 - 단, 회원의 성씨가 '이'씨인 회원에 대해서
 - 일반상식과 국제표준 방식 모두 작성
*/
--[일반 방식]
SELECT DISTINCT 
    M.Mem_id, M.Mem_name, C.Cart_qty, P.Prod_name
FROM Member m, Cart c, prod p
WHERE SUBSTR(M.Mem_name, 1, 1) = '이'
  AND m.Mem_id = c.Cart_member 
  AND p.Prod_id = c.Cart_prod;
  
--[국제 표준 방식]
-- 일반조건을 WHERE절로 분리 가능 INNER JOIN인 경우만
SELECT DISTINCT 
    M.Mem_id, M.Mem_name, C.Cart_qty, P.Prod_name
FROM Member M
INNER JOIN Cart C ON M.Mem_id = C.Cart_member
INNER JOIN Prod P ON p.Prod_id = C.Cart_prod
WHERE SUBSTR(M.Mem_name, 1, 1) = '이';

/*
주문 내역이 있는 회원정보 조회하기
 - 조회컬럼: 회원아이디, 회원이름, 주문수량, 상품명
 - 단, 회원의 성씨가 '이'씨인 회원에 대해서
   그리고, 상품명에 '셔츠'가 포함된 경우 조회
 - 일반상식과 국제표준 방식 모두 작성
*/
--[일반 방식]
SELECT DISTINCT 
    M.Mem_id, M.Mem_name, C.Cart_qty, P.Prod_name
FROM Member m, Cart c, prod p
WHERE m.Mem_id = c.Cart_member 
  AND p.Prod_id = c.Cart_prod
  AND SUBSTR(M.Mem_name, 1, 1) = '이'
  AND P.Prod_name LIKE '%셔츠%';

--[국제 표준 방식]
SELECT DISTINCT 
    M.Mem_id, M.Mem_name, C.Cart_qty, P.Prod_name
FROM Member M
INNER JOIN Cart C ON M.Mem_id = C.Cart_member
INNER JOIN Prod P ON p.Prod_id = C.Cart_prod
WHERE SUBSTR(M.Mem_name, 1, 1) = '이' AND P.Prod_name LIKE '%셔츠%';

/*
회원이름, 주문번호, 상품명, 상품분류명, 거래처명 조회하기
*/
--[일반 방식]
SELECT M.Mem_name, C.Cart_no, P.Prod_name, L.Lprod_NM, B.Buyer_name
FROM Member M, Cart C, Prod P, Lprod L, Buyer B
WHERE M.Mem_id = C.Cart_member
  AND P.Prod_id = C.Cart_prod
  AND L.Lprod_gu = P.Prod_lgu
  AND B.Buyer_id = P.Prod_buyer;
  
--[국제 표준 방식]
SELECT M.Mem_name, C.Cart_no, P.Prod_name, L.Lprod_NM, B.Buyer_name
FROM Member M
INNER JOIN Cart C ON M.Mem_id = C.Cart_member
INNER JOIN Prod P ON P.Prod_id = C.Cart_prod
INNER JOIN LPROD L ON L.Lprod_gu = P.Prod_lgu
INNER JOIN BUYER B ON B.Buyer_id = P.Prod_buyer;

/*
상품분류별로 상품의 갯수를 집계하고자 합니다.
 - 조회컬럼: 상품분류코드, 상품분류명, 상품갯수
 - 정렬은 상품의 갯수를 기준으로 내림차순
*/
SELECT L.Lprod_gu, L.Lprod_nm, COUNT(P.Prod_id) AS cnt
FROM LProd L
INNER JOIN Prod P ON L.Lprod_gu = P.Prod_lgu
GROUP BY L.Lprod_gu, L.Lprod_nm
ORDER BY cnt DESC;

/*
서울 또는 대전 지역에 거주하는 회원이 구매한 상품정보 조회하기
 - 상품명에는 '삼성'이 포함되어 있어야함
 - 조회컬럼: 회원이름, 거주지역(서울 or 대전), 상품명, 상품분류명, 거래처명
 - 정렬: 회원지역 기준 오름차순, 상품명 기준 내림차순
*/
SELECT M.Mem_name, 
       SUBSTR(M.Mem_add1, 1, 2) AS Mem_add, 
       P.Prod_name, 
       L.Lprod_nm, 
       B.Buyer_name
FROM Member M
INNER JOIN Cart C ON M.Mem_id = C.Cart_member
INNER JOIN Prod P ON P.Prod_id = C.Cart_prod
INNER JOIN LPROD L ON L.Lprod_gu = P.Prod_lgu
INNER JOIN BUYER B ON B.Buyer_id = P.Prod_buyer
WHERE P.Prod_name LIKE '%삼성%'
GROUP BY M.Mem_name, 
         SUBSTR(M.Mem_add1, 1, 2), 
         P.Prod_name, 
         L.Lprod_nm, 
         B.Buyer_name
HAVING SUBSTR(M.Mem_add1, 1, 2) = '서울' 
   OR SUBSTR(M.Mem_add1, 1, 2) = '대전'
ORDER BY Mem_add ASC, P.Prod_name DESC;

/*
상품분류명, 상품코드, 상품판매가, 회원아이디, 주문수량 조회
 - 상품분류코드가 P101인 것
 - 정렬: 거래처담당자 기준 오름차순, 주문수량 기준 내림차순
*/
--[일반 방식]
SELECT
    L.Lprod_nm,
    C.Cart_prod,
    P.Prod_sale,
    C.Cart_member,
    C.Cart_qty
FROM Cart C, Prod P, Lprod L, Buyer B
WHERE P.Prod_id = C.Cart_prod
  AND L.Lprod_gu = P.Prod_lgu
  AND B.Buyer_id = P.Prod_buyer
  AND L.Lprod_gu = 'P101'
ORDER BY B.Buyer_charger ASC, Cart_qty DESC;


--[국제 표준 방식]
SELECT
    L.Lprod_nm,
    C.Cart_prod,
    P.Prod_sale,
    C.Cart_member,
    C.Cart_qty
FROM Cart C
INNER JOIN Prod P ON P.Prod_id = C.Cart_prod
INNER JOIN LPROD L ON L.Lprod_gu = P.Prod_lgu
INNER JOIN BUYER B ON B.Buyer_id = P.Prod_buyer
WHERE L.Lprod_gu = 'P101'
ORDER BY B.Buyer_charger ASC, C.Cart_qty DESC;

/*
회원 전체의 마일리지 평균 이상인 회원들의 정보를 조회하려고 합니다.
 - 취미별 회원의 수를 조회해 주세요.
 - 단, 회원 전체 마일리지 평균 이상인 회원들에 대해서만
 - 평균은 null 체크해서 평균을 산정해서주세요.
*/
SELECT 
    mem_like,
    COUNT(mem_id) AS cnt
FROM member
WHERE mem_mileage >= (SELECT AVG(NVL(Mem_mileage, 0))
                      FROM member)
GROUP BY mem_like;

/*
회원의 성씨가 '이'씨이고,
 서울 또는 대전 또는 부산 또는 광주에 거주하는 회원들의
 평균 마일리지 이상인 회원이 구매한 상품정보를 조회하려고 합니다.
조회 컬럼: 상품코드, 상품명, 주문일자(0000-00-00), 주문수량, 
          회원아이디, 회원지역(서울, 대전..), 마일리지
단, 상품명에 '삼성'이 포함되어 있어야함
정렬은 주문일자 기준 내림차순, 마일리지 기준 오름차순
 -일반방식과 표준방식 모두 작성
*/
--[일반 방식]
SELECT
    P.Prod_id,
    P.Prod_name,
    TO_CHAR(TO_DATE(SUBSTR(C.Cart_no, 1, 8)), 'yyyy-mm-dd') AS orderdate,
    C.Cart_qty,
    M.Mem_id,
    SUBSTR(m.mem_add1, 1, 2) AS address,
    M.Mem_mileage
FROM Member M, Cart C, Prod P
WHERE M.Mem_id = C.Cart_member
  AND P.Prod_id = C.Cart_prod
  AND P.Prod_name LIKE '%삼성%'
  AND M.Mem_mileage >= (SELECT AVG(Mem_mileage)
                        FROM Member 
                        WHERE SUBSTR(Mem_name, 1, 1) = '이' 
                          AND SUBSTR(Mem_add1, 1, 2) IN ('서울', '대전', '부산', '광주'))
ORDER BY orderdate DESC, M.Mem_mileage ASC;

--[국제 표준 방식]
SELECT
    P.Prod_id,
    P.Prod_name,
    TO_CHAR(TO_DATE(SUBSTR(C.Cart_no, 1, 8)), 'yyyy-mm-dd') AS orderdate,
    C.Cart_qty,
    M.Mem_id,
    SUBSTR(m.mem_add1, 1, 2) AS address,
    M.Mem_mileage
FROM Member M
INNER JOIN Cart C ON M.Mem_id = C.Cart_member 
INNER JOIN Prod P ON P.Prod_id = C.Cart_prod
WHERE P.Prod_name LIKE '%삼성%'
  AND M.Mem_mileage >= (SELECT AVG(Mem_mileage)
                        FROM Member 
                        WHERE SUBSTR(Mem_name, 1, 1) = '이' 
                          AND SUBSTR(Mem_add1, 1, 2) IN ('서울', '대전', '부산', '광주'))
ORDER BY orderdate DESC, M.Mem_mileage ASC;

/*
상품분류명, 거래처명 조회하기
 - 상품분류코드 P101, P201, P301이고
 - 매입수량이 15개 이상이며,
 - 회원 생일이 74년인 회원에 대해 조회
 - 정렬은 상품분류명 기준 오른차순, 거래처명 기준 오름차순
*/
SELECT  DISTINCT L.Lprod_nm, B.Buyer_name
FROM Lprod L, Prod P, Buyer B, Buyprod BP, Cart C, Member M
WHERE L.Lprod_gu = P.Prod_lgu
  AND B.buyer_id = P.prod_buyer
  AND P.Prod_id = BP.Buy_prod
  AND P.Prod_id = C.Cart_prod
  AND M.mem_id = C.Cart_member
  AND Lprod_gu IN ('P101', 'P201', 'P301')
  AND BP.Buy_qty >= 15
  AND SUBSTR(mem_add1, 1, 2) = '서울'
  AND SUBSTR(mem_bir, 1, 2) = '74'
ORDER BY L.Lprod_nm ASC, B.Buyer_name ASC;


SELECT  DISTINCT L.Lprod_nm, B.Buyer_name
FROM Lprod L
INNER JOIN Prod P ON L.Lprod_gu = P.Prod_lgu
INNER JOIN Buyer B ON B.buyer_id = P.prod_buyer
INNER JOIN Buyprod BP ON P.Prod_id = BP.Buy_prod
INNER JOIN Cart C ON P.Prod_id = C.Cart_prod
INNER JOIN Member M ON M.mem_id = C.Cart_member
WHERE Lprod_gu IN ('P101', 'P201', 'P301')
  AND BP.Buy_qty >= 15
  AND SUBSTR(mem_add1, 1, 2) = '서울'
  AND SUBSTR(mem_bir, 1, 2) = '74'
ORDER BY L.Lprod_nm ASC, B.Buyer_name ASC;

/*
상품분류명, 거래처명, 매입수량의 합 조회하기
 - 상품분류코드 P101, P201, P301이고
 - 매입수량이 15개 이상이며,
 - 회원 생일이 74년인 회원에 대해 조회
 - 정렬은 상품분류명 기준 오른차순, 거래처명 기준 오름차순
 - 일반, 표준 모두 작성
*/
SELECT L.Lprod_nm, B.Buyer_name, SUM(NVL(Buy_qty, 0)) AS Totalqty
FROM Lprod L, Prod P, Buyer B, Buyprod BP, Cart C, Member M
WHERE L.Lprod_gu = P.Prod_lgu
  AND B.buyer_id = P.prod_buyer
  AND P.Prod_id = BP.Buy_prod
  AND P.Prod_id = C.Cart_prod
  AND M.mem_id = C.Cart_member
  AND Lprod_gu IN ('P101', 'P201', 'P301')
  AND BP.Buy_qty >= 15
  AND SUBSTR(mem_add1, 1, 2) = '서울'
  AND SUBSTR(mem_bir, 1, 2) = '74'
GROUP BY L.Lprod_nm, B.Buyer_name
ORDER BY L.Lprod_nm ASC, B.Buyer_name ASC;


SELECT L.Lprod_nm, B.Buyer_name, SUM(NVL(Buy_qty, 0)) AS Totalqty
FROM Lprod L
INNER JOIN Prod P ON L.Lprod_gu = P.Prod_lgu
INNER JOIN Buyer B ON B.buyer_id = P.prod_buyer
INNER JOIN Buyprod BP ON P.Prod_id = BP.Buy_prod
INNER JOIN Cart C ON P.Prod_id = C.Cart_prod
INNER JOIN Member M ON M.mem_id = C.Cart_member
WHERE Lprod_gu IN ('P101', 'P201', 'P301')
  AND BP.Buy_qty >= 15
  AND SUBSTR(mem_add1, 1, 2) = '서울'
  AND SUBSTR(mem_bir, 1, 2) = '74'
GROUP BY L.Lprod_nm, B.Buyer_name
ORDER BY L.Lprod_nm ASC, B.Buyer_name ASC;

/*
거래처코드, 거래처명, 매입금액의 합 조회하기
 - 입고일자 2005년 1월
 - 매입금액 = 매입수량 * 매입단가
 - 매입금액의 합은 NULL 체크
*/
SELECT 
    B.Buyer_id, 
    B.Buyer_name,
    SUM(NVL(BP.Buy_qty * BP.Buy_cost, 0)) AS Buy_price
FROM Buyer B
INNER JOIN Prod P ON B.Buyer_id = P.Prod_buyer
INNER JOIN Buyprod BP ON BP.Buy_prod = P.Prod_id
WHERE TO_CHAR(TO_DATE(BP.Buy_date), 'yyyy-mm') = '2005-01'
GROUP BY B.Buyer_id, B.Buyer_name;