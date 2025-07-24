/*
회원 전체에 대한 구매수량의 합을 조회
 - 조회컬럼: 회원아이디, 회원이름, 구매수량의 합
 - 일반, 표준 모두 작성
*/
--[서브쿼리 이용]
SELECT
    Mem_id,
    Mem_name,
    NVL((SELECT SUM(Cart_qty)
         FROM Cart
         WHERE Cart_member = mem_id), 0) AS qty_sum
FROM Member
ORDER BY Qty_sum ASC;

--[일반 방식]
SELECT
    M.Mem_id,
    M.Mem_name,
    (SELECT (CASE
                WHEN SUM(C.Cart_qty) IS NOT NULL
                    THEN SUM(C.Cart_qty)
                WHEN SUM(C.Cart_qty) IS NULL
                    THEN NULL
                ELSE 0
            END) 
     FROM Cart C
     WHERE C.Cart_member = M.mem_id) AS Qty_sum
FROM Member M
GROUP BY
    M.Mem_id,
    M.Mem_name
ORDER BY Qty_sum ASC;


--[INNER JOIN 일반]
SELECT
    Mem_id,
    Mem_name,
    SUM(NVL(Cart_qty, 0)) AS Qty_sum
FROM Member, Cart
WHERE Mem_id = Cart_member
GROUP BY mem_id, mem_name
ORDER BY Qty_sum ASC;

--[INNER JOIN 표준]
SELECT
    Mem_id,
    Mem_name,
    SUM(NVL(Cart_qty, 0)) AS Qty_sum
FROM Member
INNER JOIN Cart ON Mem_id = Cart_member
GROUP BY mem_id, mem_name
ORDER BY Qty_sum ASC;


/*
<OUTER JOIN>
 - 어느 한쪽의 테이블을 기준으로 전체 조회,
   즉, 전체 집계할 때 주로 사용됩니다.
 - LEFT OUTER JOIN: 왼쪽 테이블 기준으로 전체 조회
                  : 오른쪽 테이블과 같은 값은 그대로 조회
                  : 오른쪽 테이블과 같은 값이 없으면 NULL 조회
 - RIGHT OUTER JOIN: LEFT OUTER JOIN의 반대 개념
 - OUTER JOIN은 있으면 있는대로, 없으면 NULL로 조회하는 방식
 - OUTER JOIN을 사용하는 경우에 -> 일반조건이 있는 경우에는
                              -> 무조건 국제표준을 따라야 합니다.
                              -> ON() 안에 일반조건 넣어야 합니다.
 - 문법은 INNER JOIN을 만족하는 문법을 그대로 적용함(관계조건 동일)
   (JOIN 명령만 다름 -> LEFT | RIGHT OUTER JOIN으로 사용)
*/
--[LEFT OUTER JOIN 일반]
SELECT
    Mem_id,
    Mem_name,
    SUM(NVL(Cart_qty, 0)) AS Qty_sum
FROM Member, Cart
WHERE Mem_id = Cart_member(+)
GROUP BY mem_id, mem_name
ORDER BY Qty_sum ASC;

--[LEFT OUTER JOIN 표준]
SELECT
    Mem_id,
    Mem_name,
    SUM(NVL(Cart_qty, 0)) AS Qty_sum
FROM Member
LEFT OUTER JOIN Cart ON Mem_id = Cart_member
GROUP BY mem_id, mem_name
ORDER BY Qty_sum ASC;

/*
회원 전체에 대한 구매수량의 합을 조회
 - 조회컬럼: 회원아이디, 회원이름, 구매수량의 합
 - 단 마일리지의 값이 3000 이상인 회원들에 대해서
 - 일반, 표준 모두 작성
*/
--[LEFT OUTER JOIN 일반]
SELECT
    Mem_id,
    Mem_name,
    SUM(NVL(Cart_qty, 0)) AS Qty_sum
FROM Member, Cart
WHERE Mem_id = Cart_member(+)
  AND Mem_mileage >= 3000
GROUP BY mem_id, mem_name
ORDER BY Qty_sum ASC;

--[LEFT OUTER JOIN 표준]
SELECT
    Mem_id,
    Mem_name,
    SUM(NVL(Cart_qty, 0)) AS Qty_sum
FROM Member
LEFT OUTER JOIN Cart ON Mem_id = Cart_member
WHERE Mem_mileage >= 3000
GROUP BY mem_id, mem_name
ORDER BY Qty_sum ASC;

/*
상품분류 전체에 대한 상품정보갯수를 조회
 - 조회컬럼: 상품분류코드, 상품분류명, 상품정보갯수
 - 단, 상품명에 '삼성'이 포함된 경우만.
*/
--[LEFT OUTER JOIN 일반]
SELECT
    L.Lprod_gu,
    L.Lprod_nm,
    COUNT(Prod_id) AS cnt
FROM Lprod L, Prod P
WHERE L.Lprod_gu = P.Prod_lgu(+)
  AND P.Prod_name LIKE '%삼성%'
GROUP BY
    L.Lprod_gu,
    L.Lprod_nm
ORDER BY cnt DESC;

--[LEFT OUTER JOIN 표준]
SELECT
    L.Lprod_gu,
    L.Lprod_nm,
    COUNT(Prod_id) AS cnt
FROM Lprod L
LEFT OUTER JOIN Prod P ON L.Lprod_gu = P.Prod_lgu
                      AND P.Prod_name LIKE '%삼성%'
GROUP BY
    L.Lprod_gu,
    L.Lprod_nm
ORDER BY cnt DESC;

/*
전체 상품에 대한 상품코드, 상품명, 매입수량의 합 조회하기
 - 입고일자가 2005년 1월에 대해서
*/
--[LEFT OUTER JOIN 일반]
SELECT
    P.Prod_id,
    P.Prod_name,
    SUM(NVL(B.Buy_qty, 0)) AS cnt
FROM Prod P, Buyprod B
WHERE B.Buy_prod = P.Prod_id(+)
  AND TO_CHAR(TO_DATE(B.Buy_Date), 'yyyymm') = '200501' 
GROUP BY
    P.Prod_id,
    P.Prod_name
ORDER BY cnt DESC;

--[LEFT OUTER JOIN 표준]
SELECT
    P.Prod_id,
    P.Prod_name,
    SUM(NVL(B.Buy_qty, 0)) AS cnt
FROM Prod P
LEFT OUTER JOIN Buyprod B On B.Buy_prod = P.Prod_id
                         AND TO_CHAR(TO_DATE(B.Buy_Date), 'yyyymm') = '200501'
GROUP BY
    P.Prod_id,
    P.Prod_name
ORDER BY cnt DESC;

/*
아이디가 a001인 회원의 마일리지보다 큰(이상) 회원들 조회하기
 - 아이디, 이름, 마일리지 조회하기
 - 조인없이 조회, 조인으로 조회
*/
--[서브 쿼리]
SELECT
    Mem_id,
    Mem_name,
    Mem_mileage
FROM Member
WHERE Mem_mileage >= (SELECT Mem_mileage
                      FROM Member
                      WHERE Mem_id = 'a001');

--[JOIN 방식]
/*
<SELF 조인 방식>
 - 자기 자신의 테이블을 여러개로 정의해서 사용하는 경우
 - 테이블은 별칭을 이용해서 사용해야 합니다.
 - 조건이 있는 경우: 특정 테이블 하나를 기준으로 처리
 - 국제표준: INNER JOIN 사용, 관계조건은 빼고 진행
 - 표준보다는 일반방식을 주로 사용합니다.
*/
--[JOIN 일반 방식]
SELECT
    M1.Mem_id,
    M1.Mem_name,
    M1.Mem_mileage
FROM Member M1, Member M2
WHERE M2.Mem_id = 'a001'
  AND M1.Mem_mileage >= M2.Mem_mileage;

--[JOIN 국제 표준 방식]
SELECT
    M1.Mem_id,
    M1.Mem_name,
    M1.Mem_mileage
FROM Member M1
INNER JOIN Member M2 ON M1.Mem_mileage >= M2.Mem_mileage
WHERE M2.Mem_id = 'a001';

-- 서브쿼리를 테이블로 사용: INNER TABLE
SELECT
    Mem_id,
    Mem_name,
    Mem_mileage
FROM 
    Member,
    (SELECT Mem_mileage AS mileage
     FROM Member
     WHERE Mem_id = 'a001') M2
WHERE Mem_mileage >= M2.Mileage;


/*
상품정보 전체에 대한 상품코드, 매입수량의합, 주문수량의합, 조회하기
 - 단, 입고일자가 2005년 4월 16일이고,
       구매일자가 2005년 4월 16일인 경우에 대해서.
 - 정렬: 매입수량의합 기준 오름차순,
        구매수량의합 기준 내림차순
*/
SELECT
    P.Prod_id,
    SUM(NVL(B.Buy_qty, 0)) AS Buy_sum,
    SUM(NVL(C.Cart_qty, 0)) AS Cart_sum
FROM Prod P
LEFT OUTER JOIN Cart C ON P.Prod_id = C.Cart_prod
                      AND TO_CHAR(TO_DATE(SUBSTR(C.Cart_no, 1, 8)), 'yyyymmdd') = '20050416'
LEFT OUTER JOIN Buyprod B ON B.buy_prod = P.Prod_id
                         AND TO_CHAR(TO_DATE(B.Buy_Date), 'yyyymmdd') = '20050416'
GROUP BY P.Prod_id
ORDER BY Buy_sum ASC, Cart_sum DESC;

/*
매입 월별 매입수량의 합, 매입금액의 합 조회하기
 - 매입 년도가 2005년에 대해서..
 - 매입금액 = 매입수량 * 매입단가
*/
SELECT 
    TO_CHAR(TO_DATE(B.Buy_date), 'mm') AS mm,
    SUM(NVL(B.Buy_qty, 0)) AS qty_sum,
    SUM(NVL(B.Buy_qty * B.Buy_cost, 0)) AS cost_sum
FROM Buyprod B
WHERE TO_CHAR(TO_DATE(B.Buy_date), 'yyyy') = '2005'
GROUP BY TO_CHAR(TO_DATE(B.Buy_date), 'mm');

/*
모든 거래처에 대한 [매출금액]의 합계 조회하기
 - 조회컬럼: 거래처코드, 거래처명, 매출금액의합
 - 매출금액 = 구매수량 * 판매가격
 - 단, 구매년도가 2005년에 대한 것
 - 정렬은 거래처코드 기준 오름차순
*/
SELECT
    b.Buyer_id,
    b.Buyer_name,
    SUM(NVL(p.Prod_Sale * c.Cart_qty, 0)) AS income
FROM Buyer b
LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
LEFT OUTER JOIN Cart c ON p.Prod_id = c.Cart_prod
                      AND SUBSTR(c.Cart_no, 1, 4) = '2005'
GROUP BY
    b.Buyer_id,
    b.Buyer_name
ORDER BY b.buyer_id ASC;

/*
모든 거래처에 대한 [매입금액]의 합계 조회하기
 - 조회컬럼: 거래처코드, 거래처명, 매입금액의합
 - 매입금액 = 매입수량 * 매입단가
 - 단, 매입년도가 2005년에 대한 것
 - 정렬은 거래처코드 기준 오름차순
*/
SELECT
    b.Buyer_id,
    b.Buyer_name,
    SUM(NVL(bp.Buy_qty * bp.Buy_cost , 0)) AS Cost_sum
FROM Buyer b
LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
LEFT OUTER JOIN Buyprod bp ON bp.Buy_prod = p.Prod_id
                      AND TO_CHAR(TO_DATE(bp.Buy_Date), 'yyyy') = '2005'
GROUP BY
    b.Buyer_id,
    b.Buyer_name
ORDER BY b.buyer_id ASC;

/*
모든 거래처에 대한 [매출금액]과 [매입금액]의 합계 조회하기
 - 조회컬럼: 거래처코드, 거래처명, 매출금액의합, 매입금액의합
 - 매입금액 = 매입수량 * 매입단가
- 매출금액 = 구매수량 * 판매가격
 - 단, 매입 관련해서는 매입년도가 2005년에 대한 것
       매출 관련해서는 구매년도가 2005년에 대한 것
 - 정렬은 거래처코드 기준 오름차순
*/
-- 성격다른 조회결과를 하나의 결과로 조회하고자 할 경우
-- ** FROM 절에 각각의  조회결과를 INNER TABLE로 만들어서 사용
--    가급적 각각 조회시 사용한 SQL구문으로 그대로 복사 사용
SELECT
    TB_cart.buyer_id,
    TB_cart.buyer_name,
    TB_cart.income,
    TB_buyprod.Cost_sum
FROM (SELECT
          b.Buyer_id,
          b.Buyer_name,
          SUM(NVL(p.Prod_Sale * c.Cart_qty, 0)) AS income
      FROM Buyer b
      LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
      LEFT OUTER JOIN Cart c ON p.Prod_id = c.Cart_prod
                            AND SUBSTR(c.Cart_no, 1, 4) = '2005'
      GROUP BY
          b.Buyer_id,
          b.Buyer_name
      ORDER BY b.buyer_id ASC
) TB_cart,
     (SELECT
          b.Buyer_id,
          b.Buyer_name,
          SUM(NVL(bp.Buy_qty * bp.Buy_cost , 0)) AS Cost_sum
      FROM Buyer b
      LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
      LEFT OUTER JOIN Buyprod bp ON bp.Buy_prod = p.Prod_id
                            AND TO_CHAR(TO_DATE(bp.Buy_Date), 'yyyy') = '2005'
      GROUP BY
          b.Buyer_id,
          b.Buyer_name
      ORDER BY b.buyer_id ASC) TB_buyprod
WHERE TB_cart.buyer_id = TB_buyprod.buyer_id;


SELECT
    b.Buyer_id,
    b.Buyer_name,
    NVL(income.income, 0) AS income,
    NVL(cost.Cost_sum, 0) AS Cost_sum
FROM Buyer b
LEFT OUTER JOIN (
    SELECT
        b.Buyer_id,
        SUM(NVL(p.Prod_Sale * c.Cart_qty, 0)) AS income
    FROM Buyer b
    LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
    LEFT OUTER JOIN Cart c ON p.Prod_id = c.Cart_prod
                          AND SUBSTR(c.Cart_no, 1, 4) = '2005'
    GROUP BY
        b.Buyer_id
) income ON b.Buyer_id = income.Buyer_id
LEFT OUTER JOIN (
    SELECT
        b.Buyer_id,
        SUM(NVL(bp.Buy_qty * bp.Buy_cost, 0)) AS Cost_sum
    FROM Buyer b
    LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
    LEFT OUTER JOIN Buyprod bp ON bp.Buy_prod = p.Prod_id
                               AND TO_CHAR(TO_DATE(bp.Buy_Date), 'yyyy') = '2005'
    GROUP BY
        b.Buyer_id
) cost ON b.Buyer_id = cost.Buyer_id
ORDER BY b.Buyer_id ASC;

/*
회원아이디, 회원이름, 회원지역, 회원성별(남자 or 여자) 조회하기
 - 정렬은 아이디기준 오름차순
*/
SELECT
    Mem_id,
    Mem_name,
    SUBSTR(Mem_add1, 1, 2) AS Area,
    DECODE(MOD(SUBSTR(Mem_regno2, 1, 1), 2),
            0, '여성', 
            1, '남성',
            '외계인') AS sex
FROM Member
ORDER BY mem_id ASC;

-- 조회한 결과(가공한 데이터)를 임의 테이블로 저장하기
CREATE Table Mem_area_sex
AS
    -- 가공을 위해 조회한 결과에 대한 SQL구문을 아래 작성
    SELECT
        Mem_id,
        Mem_name,
        SUBSTR(Mem_add1, 1, 2) AS Area,
        DECODE(MOD(SUBSTR(Mem_regno2, 1, 1), 2),
                0, '여성', 
                1, '남성',
                '외계인') AS sex
    FROM Member;
    
/*
<뷰(View)>
 - 가상테이블이라고 칭하기도 합니다.
 - 외부에 Table에 대한 모든 정보를 공개하지 않고,
   임의 정보만 제공하고자 할 때 주로 사용됩니다.
 - 또는 SQL 구문이 매우 긴 경우로, 자주 사용되야하는 SQL 구문인
   경우에 미리 가공하여 조회결과를 사용할 수 있도록 합니다.
 - 사용밥법: 테이블 사용방법과 SQL구민이 동일하게 사용됨
            SELECT 조회컬럼
            FROM 가상테이블이름
            WHERE 조건
            GROUP BY ...
            HAVING ...
            ORDER BY ...
 - 가상테이블도 테이블과 동일한 객체임
*/
CREATE OR REPLACE VIEW View_in_out_price
AS   
    SELECT
        b.Buyer_id,
        b.Buyer_name,
        NVL(income.income, 0) AS income,
        NVL(cost.Cost_sum, 0) AS Cost_sum
    FROM Buyer b
    LEFT OUTER JOIN (
        SELECT
            b.Buyer_id,
            SUM(NVL(p.Prod_Sale * c.Cart_qty, 0)) AS income
        FROM Buyer b
        LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
        LEFT OUTER JOIN Cart c ON p.Prod_id = c.Cart_prod
                              AND SUBSTR(c.Cart_no, 1, 4) = '2005'
        GROUP BY
            b.Buyer_id
    ) income ON b.Buyer_id = income.Buyer_id
    LEFT OUTER JOIN (
        SELECT
            b.Buyer_id,
            SUM(NVL(bp.Buy_qty * bp.Buy_cost, 0)) AS Cost_sum
        FROM Buyer b
        LEFT OUTER JOIN Prod p ON b.Buyer_id = p.Prod_buyer
        LEFT OUTER JOIN Buyprod bp ON bp.Buy_prod = p.Prod_id
                                   AND TO_CHAR(TO_DATE(bp.Buy_Date), 'yyyy') = '2005'
        GROUP BY
            b.Buyer_id
    ) cost ON b.Buyer_id = cost.Buyer_id
    ORDER BY b.Buyer_id ASC;
    
-- VIEW룰 이용하여 조회하기
SELECT *
FROM View_in_out_price
ORDER BY buyer_id ASC;

/*
함수(UDF; USER DEFINE FUNCTION, 사용자 정의 함수)
 - 구문 조회시 일반 SQL 함수들을 이용해서 데이터 변경이 자주 일어나는 경우 사용
 - (예시: 성별 추출, 지역 추출 등..)
*/
-- 지역을 추출하는 함수 정의하기
Create Or Replace Function udf_getArea
-- <매개변수 정의 영역>
    -- In : 외부에서 값을 넘겨받는 변수(매개변수)라는 의미
    -- %type : 해당 테이블의 컬럼에 대한 타입을 그대로 따르겠다는 의미
    (p_mem_add1 In member.mem_add1%type)
    -- 최종 결과값을 반환(return)할 타입 정의
    Return Varchar2
-- <정의영역>
Is
    -- 반환(return)에 사용할 변수 정의하기
    rtn_mem_area Varchar2(30);
-- <처리영역>
Begin
    -- 전달받은 주소값에서 지역만 추출하는 기능 수행
    rtn_mem_area := Substr(p_mem_add1,1,2);
    -- 호출한 곳으로 반환하기
    return rtn_mem_area;
End;
-- 아래 슬래시를 넣어야 전체 문장의 끝을 알려줄수 있다.
/

-- 함수 사용하기
SELECT Mem_id, Mem_add1, udf_getarea(mem_add1) as area
From Member;


/*
회원 전체 마일리지값의 평균을 조회하기
 - 조회한 결과를 리턴하는 함수 정의하기
 - 함수명: udf_getMemMIleageAvg
*/
-- 회원 전체 마일리지값의 평균을 조회하기
-- 조회컬럼: 회원전체-마일리지평균
-- 함수 정의하기
CREATE OR REPLACE FUNCTION udf_getMemMIleageAvg
-- 1. 리턴타입: 숫자
    RETURN NUMBER
IS
-- 2. 리턴할 변수명: rtn_mileage_avg
     rtn_mileage_avg NUMBER;
BEGIN
-- 3. SELECT 구문 정의
    SELECT ROUND(AVG(NVL(mem_mileage, 0)), 3) INTO rtn_mileage_avg
    FROM Member;
-- 4. 반환(리턴)
    RETURN rtn_mileage_avg;
END;
/

/*
회원 전체 평균 마일리지 이상인 회원들의 정보확인하기
 - 회원아이디, 회원이름, 마일리지 조회...
*/
SELECT Mem_id, Mem_name, Mem_mileage
FROM Member
WHERE Mem_mileage >= udf_getMemMIleageAvg();

/*
<저장프로시저(Stored Procedure)>
 - 기존의 직접 SQL 실행 방식을
  -> SQL 구문 작성 >> 실행 >> DB서버가 해석 >> 결과 반환

 - 저장프로시저를 이용하는 방식
  -> SQL 구문의 결과를 DB서버의 캐시메모리에 올려놓음 >> 결과 반환
*/

-- 아이디 'a001'에 대한 회원아이디, 회원이름, 주소1, 지역(서울, 부산..) 조회하기
SELECT Mem_id, Mem_name, Mem_add1,udf_GetArea(mem_add1) as Area
FROM Member
WHERE Mem_id = 'a001';

-- 저장프로시저 정의하기
-- * 이름: SP_getMemberView
CREATE OR REPLACE PROCEDURE SP_getMemberView
    -- <매겨변수 및 리턴변수 정의하기>
    (
        -- 받아오는 매개변수 정의
        -- IN 사용
        P_mem_id IN member.mem_id%type,
        
        -- 결과를 리턴할 변수 정의
        -- OUT 사용: 최종 결과값을 반환하겠다는 의미
        -- Sys_RefCursor 타입: 조회 결과를 행/열 단위로 담고자 할 때 사용하는 타입
        rs_row Out Sys_RefCursor
    )
IS
BEGIN
    --<처리 영역>
    -- 전달받은 매개변수(회원아이디)에 대한 회원상세조회 후
    -- 결과 rs_row 변수에 담는 기능 수행
    -- OPEN rs_row FOR: 조회결과의 행 하나씩을 변수에 담기
    --  -> OPEN 명령을 통해 rs_row의 메모리 만들기
    --  -> For: SELECT의 행의 결과 갯수만큼 반복하기
    OPEN rs_row FOR
        SELECT Mem_id, Mem_name, Mem_add1,udf_GetArea(mem_add1) as Area
        FROM Member
        WHERE Mem_id = p_mem_id;
END;
/

/*
 - 프로시저 호출하기
 - 프로시저 호출하는 방법은 호출 시점의 프로그램에 따라 다름
   (JAVA, C, PYTHON, 기타 TOOL들 모두 다름)
*/
-- 프로시저로부터 받아올 변수 정의하기
-- Variable: 변수 선언
-- RefCursor: 행/열을 담을 수 있는 주소 개념의 타입
VARIABLE rs_data RefCursor;

-- 프로시저 호출하기
-- Execute : 실행 명령어(SQL 실행 시 사용됩니다.)
Execute SP_getMemberView('a001', :rs_data);

-- 조회결과 출력하기
print rs_data;



CREATE OR REPLACE FUNCTION udf_getNonMileageAvg
-- 1. 리턴타입: 숫자
    RETURN NUMBER
IS
-- 2. 리턴할 변수명: rtn_nonmileage_avg
     rtn_nonmileage_avg NUMBER;
BEGIN
-- 3. SELECT 구문 정의
    SELECT AVG(NVL(Mem_mileage, 0)) INTO rtn_nonmileage_avg
    FROM Member
    WHERE Mem_id NOT IN (SELECT Cart_member
                         FROM Cart
                         WHERE Mem_id = Cart_member);
-- 4. 반환(리턴)
    RETURN rtn_nonmileage_avg;
END;
/

SELECT 
    mem_id,
    mem_name,
    SUM(NVL(Cart_qty, 0)) AS total_qty
FROM Member
INNER JOIN Cart on Mem_id = Cart_member
WHERE Mem_mileage >= udf_getnonmileageavg
GROUP BY mem_id, mem_name;

SELECT *
FROM member2;