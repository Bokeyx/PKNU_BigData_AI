/*
회원의 생일이 75년 1월 1일 부터 76년 12월 31일에 태어난 회원 정보 조회
 - 조회컬럼: 회원아이디, 회원이름, 생일
 - 생일 기준 오름차순
 - 참고로 날짜 포멧은 00000000, 0000-00-00, 0000/00/00, 0000.00.00, 00-00-00, 00/00/00
*/
SELECT 
    Mem_id, 
    Mem_name,
    Mem_bir
FROM Member
WHERE Mem_bir BETWEEN '75/01/01' AND '76/12/31'
ORDER BY Mem_bir ASC;


/*
REPLACE(원본값, 찾을값, 바꿀값): 문자 치환 함수
*/
SELECT 
    'DataBase, 파이썬' as org,
    Replace('DataBase, 파이썬', '파이썬', 'Python') AS change
FROM Dual;

/*
회원의 성씨 중에 '이'씨를 '리'씨로 바꿔서 조회해 주세요
 - 조회컬럼: 회원원래이름, 바뀐성씨
 - 성만 바뀌고, 성 뒤에 이름은 바뀌면 안됩니다.
*/
SELECT
    Mem_name AS Org_name,
    REPLACE(SUBSTR(Mem_name,0, 1), '이', '리') || SUBSTR(Mem_name, 2, 2) AS Cha_name
FROM Member;


/*
ROUND(원본값, 반올림자릿수): 반올림 함수
*/
SELECT
    ROUND(1234.5678, -4) AS r1,
    ROUND(1234.5678, -3) AS r2,
    ROUND(1234.5678, -2) AS r3,
    ROUND(1234.5678, -1) AS r4,
    ROUND(1234.5678, 0) AS r5,
    ROUND(1234.5678, 1) AS r6,
    ROUND(1234.5678, 2) AS r7,
    ROUND(1234.5678, 3) AS r8,
    ROUND(1234.5678, 4) AS r9
FROM dual;


/*
상품명, 원가율 조회하기
 - 원가율 = 매입가/판매가 * 100
 - 원가율은 소숫점 첫째자리까지 표현
 - 단, 상품명에 '삼성'아라는 단어가 포함된 경우만 조회
*/
SELECT
    Prod_name,
    ROUND(((Prod_cost / prod_sale) * 100), 1) AS Org_p 
FROM Prod
WHERE Prod_name LIKE '%삼성%';


/*
나눈 나머지 값: MOD(원래값, 나눌값)
*/
SELECT
    MOD(10,3) AS m1,
    MOD(10,2) as m2
From Dual;

/*
회원이름, 성별(남자는 1로, 여자는 0으로) 조회하기
 - 전제 조건: 1900년도와 2000년도에 태어난 모든 사람 처리되도록
*/
SELECT 
    Mem_name,
    MOD(SUBSTR(Mem_regno2, 1, 1), 2) AS Sex 
FROM Member;

/*
김은대 회원님이 구매한 상품 중에 상품명에 '모니터'가 포함된 상품의 구매일자를 확인하려고 합니다.
 - 조회컬럼: 회원아이디, 회원이름, 성별, (숫자로), 주문일자, 상품명
 - 정렬: 회원아이디 기준 오름차순
 - 주문일자는 0000-00-00 형식으로 조회
 (주문일자의 주문번호의 앞8자리 입니다.)
*/
SELECT
    Cart_member,
    (SELECT Mem_name
     FROM Member
     WHERE Mem_id = Cart_member) AS name,
     (SELECT MOD(SUBSTR(Mem_regno2, 1, 1), 2)
     FROM Member
     WHERE Mem_id = Cart_member) AS sex,
     SUBSTR(Cart_no, 0, 4) || '-' || SUBSTR(Cart_no, 5, 2) || '-' || SUBSTR(Cart_no, 7, 2) AS order_date,
     (SELECT prod_name
     FROM Prod
     WHERE Prod_id = cart_prod) AS p_name
FROM Cart
WHERE Cart_member IN (SELECT mem_id
                      FROM Member
                      WHERE Mem_name = '김은대')
AND Cart_prod IN (SELECT Prod_id
                  FROM Prod
                  WHERE Prod_name LIKE '%모니터%')
ORDER BY Cart_member ASC;

/*
<날짜 관련 함수>
 - 날짜 포맷을 따릅니다.
 - 참고로 날짜 포맷은 00000000, 0000-00-00, 0000/00/00, 0000.00.00, 00-00-00, 00/00/00
 - 날짜 타입의 데이터는 년월일 뒤에 시분초가 함께 존재함
  -> 시간이 정의되지 않은 날짜 타입 데이터는 00:00:00으로 됨
 - 컬럼의 날짜 타입: date 타입을 사용(DB마다 다름)
*/

/* 
시스템 날짜 조회하기 
 - sysdate: oracle에서만 사용가능한 키워드, DB마다 다름
*/

SELECT Sysdate
FROM Dual;

/* 날짜 데이터는 연산이 가능함: 일단위로 연산됨*/
SELECT 
    Sysdate,
    Sysdate + 1,
    Sysdate - 1
FROM Dual;

/*
월 단위로 더하고 빼고자 할 때는 함수를 사용함
 - ADD_MONTHS(원본값, 연산할값)
*/
SELECT
    Sysdate,
    ADD_MONTHS(Sysdate, 1) AS m1,
    ADD_MONTHS(Sysdate, -1) AS m2
FROM Dual;

/* 
가장 빠른 요일의 일자 추출하기
 - NEXT_DAY(원본값, 찾을 요일)
*/
SELECT
    Sysdate,
    NEXT_DAY(Sysdate, '일요일') AS d
FROM Dual;

/* 
해당 월의 마지막 일자 찾기
 - LAST_DAY(원본일자값)
*/
SELECT
    Sysdate,
    LAST_DAY(Sysdate) AS ld,
    LAST_DAY('2025-02-01') AS ld2
FROM Dual;

/* 이번달이 몇일 남았는지 계산해주세요 */
SELECT
    LAST_DAY(Sysdate) - sysdate AS leftday
FROM Dual;


/*
년, 월, 일 각각 추출하기(많이 사용됨)
 - EXTRACT(추출할(년/월/일)중 하나 FROM 원본값)
 - 추출할 (년/월/일): 년(YEAR), 월(MONTH), 일(DAY) 영문 사용
*/
SELECT
    Sysdate,
    EXTRACT(YEAR FROM Sysdate) as yyyy,
    EXTRACT(MONTH FROM Sysdate) as mm,
    EXTRACT(DAY FROM Sysdate) as dd
FROM Dual;

/*
회원의 생일이 3월인 회원에 대한 아이디, 이름, 생일 조회하기
*/
SELECT Mem_id, Mem_name, Mem_bir
FROM Member
WHERE EXTRACT(MONTH FROM Mem_bir) = 3;

/*
<형변환(타입변환)>
 - CAST(원본값 AS 변환할 타입)
  : 데이터의 타입을 변환해야하는 경우 사용
 - TO_CHAR(원본값): 문자 타입으로 변환
 - TO_NUMBER(원본값): 숫자타입으로 변환
 - TO_DATE(원본값): 날짜 타입으로 변환
*/
SELECT
    -- 숫자를 문자 타입으로 변환
    CAST(123 AS CHAR(10)) AS c1,
    CAST(123 AS VARCHAR2(10)) AS vc1,
    -- 숫자만 있는 문자열을 숫자 타입으로 변환
    CAST('123' AS NUMBER(10)) AS n1,
    -- 날짜 포맷의 문자열을 날짜 타압으로 변환
    CAST('20250305' AS DATE) AS d1,
    CAST('2025-03-05' AS DATE) AS d2,
    CAST('2025/03/05' AS DATE) AS d3,
    CAST('2025.03.05' AS DATE) AS d4,
    CAST('250305' AS DATE) AS d5,
    -- 숫자는 사용할 수 없음
    -- CAST(202503 AS DATE) AS d6,
    TO_CHAR(123) AS tc,
    TO_NUMBER('12345') AS tn,
    TO_DATE('2025-03-05') AS td1,
    TO_DATE('20250305') AS td2,
    TO_CHAR(Sysdate) AS td3
FROM Dual;

/*
날짜 포맷으로 추출하기
 TO_CHAR(원본값, '포맷지정') 함수가 주로 사용됨
*/
SELECT
    TO_CHAR(Sysdate) AS tc1,
    TO_CHAR(Sysdate, 'yyyy-mm-dd') AS tc2,
    TO_CHAR(Sysdate, 'yyyy.mm.dd') AS tc3,
    TO_CHAR(Sysdate, 'yyyy/mm/dd') AS tc4,
    TO_CHAR(Sysdate, 'yyyy-mm-dd (am)hh24:mm:ss') AS tc5,
    TO_CHAR(Sysdate, 'yyyy') AS yyyy,
    TO_CHAR(Sysdate, 'mm') AS mm,
    TO_CHAR(Sysdate, 'dd') AS dd,
    TO_CHAR(Sysdate, 'day') AS day,
    -- 문자열을 원본값으로 사용할 수 없음(날짜 타입만 가능함)
    TO_CHAR(TO_DATE('2025-03-05'), 'yyyy-mm-dd') AS tc6
FROM Dual;

/*
주문정보에서 회원아이디, 주문일자(0000-00-00 형태로), 주문수량 조회
*/
SELECT 
    Cart_member,
    TO_CHAR(TO_DATE(SUBSTR(Cart_no, 0, 8)), 'yyyy-mm-dd') AS Od,
    Cart_qty
FROM Cart;

/*
2005년 5월에 주문한 내역 중에
주문 수량이 10개 이상인 회원 정보 조회
 - 회원의 생일이 1974년생인 회원이 주문한 내역
 - 조회 컬럼: 회원아이디, 회원이름, 회원생일(0000-00-00 형태)
*/
SELECT
    Mem_id,
    Mem_name,
    TO_CHAR(Mem_bir, 'yyyy-mm-dd') AS bir
FROM Member
WHERE TO_CHAR(Mem_bir, 'yyyy') = '1974'
  AND Mem_id IN (SELECT Cart_member
                 FROM Cart
                 WHERE Cart_qty >= 10
                   AND SUBSTR(Cart_no, 1, 6) = '200504');
------------------ 위에는 오라클에서 제공하는 일반함수

/*
<그룹함수>
 - 집계함수, 그룹함수, 통계함수.. 여러 이름으로 사용됨
 - 국제 표준에 따름 (모든 DB에서 사용가능)
 - 5개 함수: 
  * COUNT(): 행의 갯수
  * SUM(): 열 데이터의 합산값 (null이 나올 수 있음)
  * AVG(): 열 데이터의 평균값 (null이 나올 수 있음)
  * MIN(): 열 데이터의 최소값
  * MAX(): 열 데이터의 최대값
 - 그룹에 대한 조건: HAVING 절 사용
  (문법)
   SELECT 조회할 컬럼, 그룹함수
   FROM 조회할 테이블
   WEHRE 일반 조건
   GROUP BY 그룹화할 컬럼
   HAVING 그룹함수를 이용한 조건
   ORDER BY 정렬
*/

/*
모든 회원에 대한 회원 수, 마일리지 총합, 마일리지 평균
마일리지 최소값, 최대값 조회
** 조회하고자 하는 컬럼에 일반 컬럼이 없는 경우
   (그룹 함수만 사용하는 경우)
*/
SELECT 
    COUNT(*) AS Cnt,
    SUM(Mem_mileage) AS Mem_sum,
    ROUND(AVG(Mem_mileage)) AS Mem_avg,
    MIN(Mem_mileage) AS Mem_min,
    MAX(Mem_mileage) AS Mem_max
FROM Member;


/*
상품분류별로 상품의 갯수 확인하기
 - 단, 상품의 갯수가 10개 이상인 것에 대해서만 조회
 - 조회컬럼: 상품분류코드, 상품갯수
 - 오류메시지: 단일 그룹의 그룹 함수가 아닙니다
  -> GROUP BY를 사용하라는 의미
*/
SELECT
    prod_lgu,
    COUNT(Prod_id) AS cnt
FROM prod
-- 조회할 컬럼 중 일반컬럼 또는 일반함수를 이용한 컬럼들은
-- 원형 그대로를 GROUP BY절에 작성해야함 (그룹 규칙)
GROUP BY prod_lgu
HAVING COUNT(Prod_id) >= 10;

/*
<그룹 규칙>
 1. SELECT문 뒤에 조회하는 컬럼으로는 일반컬럼, 일반함수, 
    그룹함수가 동시에 사용될 수 있으며, 이때, 일반컬럼 및 일반함수들은 
    모두 GROUP BY뒤에 원형 그대로 작성해야함
 2. 그룹 조건이 발생하는 경우
   - GROUP BY절 다음에 HAVING 절에 그룹 조건을 작성
   - 그룹 조건은 그룹함수를 그대로 사용하여 비교연산자를 이용하여 처리
 3. GROUP BY를 사용한 경우, 정렬에 사용할 수 있는 컬럼
   - SELECT문 뒤에 조회하는 모든 컬럼 가능
   - GROUP BY절 뒤에 작성된 모든 컬럼 가능
*/

/*
회원 취미별로 회원수를 조회해 주세요.
 - 조회컬럼: 취미, 회원수
 - 정렬: 취미 기준 오름차순
*/
SELECT
    Mem_like,
    COUNT(Mem_id) AS cnt
FROM Member
GROUP BY Mem_like
ORDER BY Mem_like ASC;

/*
회원 지역별 (서울, 대선, 부산....)로 회원수 조회하기
 - 단, 상품명에 "삼성"이 포함된 주문을 주문한 회원과
 - 지역별 회원수가 2명 이상인 경우에만 조회
 - 정렬은 회원수 기준 내림차순
 - 조회컬럼: 지역, 회원수
*/
SELECT
     SUBSTR(Mem_add1, 1, 2) AS mem_add,
     COUNT(Mem_id) AS mem_cnt
FROM Member
WHERE Mem_id IN (SELECT Cart_member
                 FROM Cart
                 WHERE Cart_prod IN (SELECT Prod_id
                                     FROM Prod
                                     WHERE Prod_name LIKE '%삼성%'))
GROUP BY SUBSTR(Mem_add1, 1, 2)
HAVING Count(Mem_id) >= 2 
ORDER BY mem_cnt DESC;

/*
회원 전체 마일리지 평균 이상인 회원정보를 조회하기 
 - 회원아이디, 이름, 마일리지 조회하기
*/
SELECT
    Mem_id,
    Mem_name,
    Mem_mileage
FROM Member
WHERE Mem_mileage >= (SELECT AVG(Mem_mileage)
                      FROM Member);
                      
                      
-- a001인 아이디의 마일리지 이상인 회원 조회.
SELECT
    Mem_id,
    Mem_name,
    Mem_mileage
FROM Member
WHERE Mem_mileage >= (SELECT Mem_mileage
                      FROM Member
                      WHERE Mem_id = 'a001');
                      

/*
회원이름, 회원지역(서울, 부산..), 회원생일(년도만),
마일리지 평균, 마일리지합계를 조회
 - 단, 회원의 성씨가 '이'씨인 회원 중에
 - 정렬: 회원지역을 기준으로 내림차순,
         생일의 년도를 기준으로 오름차순
*/
SELECT
    Mem_name,
    SUBSTR(Mem_add1, 0, 2) AS Ads,
    TO_CHAR(Mem_bir, 'yyyy') AS byear,
    ROUND(AVG(mem_mileage), 3) AS Avgm,
    SUM(mem_mileage) AS Summ
FROM Member
WHERE Mem_name LIKE '이%'
GROUP BY 
    Mem_name,
    SUBSTR(Mem_add1, 0, 2),
    TO_CHAR(Mem_bir, 'yyyy')
ORDER BY ads DESC, byear ASC;

/*
상품분류코드, 상품소비자가격의 평균 조회하기
 - 실수값은 소숫점 2자리까지 표현
*/
SELECT
    Prod_lgu,
    ROUND(AVG(Prod_price), 2) AS Avg_price
FROM Prod
GROUP BY prod_lgu;

/*
<NULL 처리하기
 - NULL은 메모리가 존재하지 않는 것을 의미함
*/

/* NULL 데이터 만들기 */
-- 거래자 담당자의 성씨가 '이'씨인 거래처만 조회하기
-- 조회컬럼: 거래처코드, 거래처담당자
SELECT
    Buyer_id,
    Buyer_charger
FROM Buyer
WHERE SUBSTR(buyer_charger, 1, 1) = '이';

-- 거래처 담당자의 성씨가 '이'씨인 거래처담장자의 이름을
-- NULL로 수정해주세요.

UPDATE buyer
   SET buyer_charger = NULL
WHERE SUBSTR(buyer_charger, 1, 1) = '이';

/*
거래처 담당자에 결측치가 있는 경우만 조회하기
 <NULL 확인 명령어(국제 표준)>
 - 결측치 조회: IS NULL
 - 결측치가 없는 경우만 조회: IS NOT NULL
*/
SELECT
    Buyer_id,
    Buyer_charger
FROM Buyer
WHERE Buyer_charger IS NULL;

SELECT
    Buyer_id,
    Buyer_charger
FROM Buyer
WHERE Buyer_charger IS NOT NULL;

/*
결측치 처리 함수 (NVL()함수를 주로 사용함)
** 국제표준 아님. DB마다 다를 수 있음
 - NVL(원본값, 결측값이 있는 경우 대체 할 값)
 - NVL2((원본값, 결측값이 없는 경우 할 값, 결측값이 있는 경우 할 값)
*/
SELECT
    Buyer_id,
    Buyer_charger,
    NVL(Buyer_charger, '넌 누구?') AS Nvl_Val,
    NVL2(Buyer_charger, '있음', '없음') AS Nvl2_Val
From Buyer;

/*
회원 중에 '이'씨가 구매한 상품정보의 소비자가격을 NULL로 처리해 주세요.
 - 1. 먼저 해당 조건의 값을 조회 먼저 합니다. (검증용)
 - 2. 1번에서 사용한 조건을 이용하여 소비자 가격 수정 진행 합니다.
 --> 소비자가격의 컬럼 제약조건(NOT NULL)으로 수정 불가..
*/
SELECT
    (SELECT Mem_name
     FROM Member
     WHERE Mem_id = Cart_member) AS mem_name,
    (SELECT Prod_Price
     FROM Prod
     WHERE Prod_id = Cart_prod) AS Prod_price
FROM Cart
WHERE Cart_member IN (SELECT Mem_id
                      FROM Member
                      Where SUBSTR(Mem_name, 0, 1) = '이');
                      
UPDATE Prod
   SET Prod_price = NULL
WHERE Prod_id IN (SELECT Cart_member
                  FROM Cart
                  WHERE Cart_member IN (SELECT Mem_id
                                        FROM Member
                                        Where SUBSTR(Mem_name, 0, 1) = '이');
                                        
                                        
/* NULL과 숫자의 연산*/
SELECT 
    NULL+10, NULL-10, NULL*10, NULL/10,
    COUNT(*), COUNT(NULL), SUM(NULL), AVG(NULL),
    MIN(NULL), MAX(NULL)
FROM Dual;

SELECT COUNT(prod_id), COUNT(Prod_size) AS cnt1, COUNT(NVL(Prod_size, 0)) AS cnt2
From(prod);

/*
<조건문 사용하기(국제표준 아님)>
 - DECODE() 함수
  : DECODE(원본값, 비교값1, 출력값1, 비교값2, 출력값2, 무조건출력값)
 - CASE 문: CASE WHEN THEN ELSE END
  : CASE WHEN 조건 THEN 출력값 1 THEN 출력값2... ELSE 무조건 END
*/
/* DECODE() 함수 사용 */
SELECT DECODE('홍길동', '홍길동', '출력값', 
                       '홍길동1', '출력값1', 
                       '홍길동2', '출력값2', 
                       '출력값3') 
FROM dual;

/*
회원이름, 회원성별(남성, 여성으로)조회하기
*/
SELECT
    Mem_name,
    DECODE(MOD(SUBSTR(Mem_regno2, 1, 1), 2),
            0, '여성', 
            1, '남성',
            '외계인') AS sex
FROM Member;

-- CASE 문
SELECT
    Mem_name,
    DECODE(MOD(SUBSTR(Mem_regno2, 1, 1), 2),
            0, '여성', 
            1, '남성',
            '외계인') AS sex,
    (CASE
        WHEN MOD(SUBSTR(Mem_regno2, 1, 1), 2) = 0
            THEN '여자'
        WHEN MOD(SUBSTR(Mem_regno2, 1, 1), 2) = 1
            THEN '남자'
        ELSE
            '외계인!'
    END) AS sex_case
FROM Member;

/*
조건으로 사용되는 서브쿼리 함수: EXISTS(서브쿼리)
 - 서브쿼리 결과 규칙: 다중컬럼에 다중행 모두 가능
 - 행이 1개라도 존재하면 조건이 참(TRUE),
 - 행이 0이면 조건은 거짓(FALSE)
*/
-- 주문 내역이 있는 회원 조회하기
SELECT mem_id, mem_name
FROM member
WHERE EXISTS(SELECT *
             FROM Cart
             Where Cart_member = mem_id);
             
-- 주문 내역이 없는 회원 조회하기
SELECT mem_id, mem_name
FROM member
WHERE NOT EXISTS(SELECT *
             FROM Cart
             Where Cart_member = mem_id);