/*
상품정보를 조회하려고 합니다.
 - 상품코드, 상품명, 판매가격 조회하기
 - 단, 판매가격이 만원이상, 50만원 이하인 상품에 대해서
*/
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale >= 10000 
  AND Prod_sale <= 500000;
  
-- BETWEEN: 값의 범위 비교를 할때 사용하는 표준명령어
-- 컬러명 BETWEEN 최소값 and 최대값
-- 최소값 이상 ~ 최대값 이하
SELECT Prod_id, Prod_name, Prod_sale
FROM Prod
WHERE Prod_sale BETWEEN 10000 AND 500000;

/*
<검색 명령: LIKE>
 LIKE: 문자 또는 문자열이 포함되어 있는지 조건처리
 NOT LIKE: 문자 또는 문자열이 포함되어 있지 않으면 TRUE
 ** 검색하고자하는 문자 또는 문자열의 왼쪽 오름쪽에 %를 사용할 수 있음
    (%의 의미: 모든것을 의미함)
*/

/* 
회원 이름의 성씨가 이씨인 회원들만 조회하기 
 - 이%: 이로 시작하는 모든 문자 찾기
*/
SELECT Mem_id, Mem_name
FROM Member
WHERE Mem_name LIKE '이%';

/* 이름 마지막이 '이'로 끝나는 회원 이름 조회 */
SELECT Mem_id, Mem_name
FROM Member
WHERE Mem_name LIKE '%이';

/* 이름 중간 또는 마지막 또는 중간에 '쁜'이 있는 회원 이름 조회 */
SELECT Mem_id, Mem_name
FROM Member
WHERE Mem_name LIKE '%쁜%';

/* 
이름의 두번째 단어가 "은"이 포함되어 있는 회원이름 조회 
 - 언더대쉬(_): 자릿수를 의미함
*/
SELECT Mem_id, Mem_name
FROM Member
WHERE Mem_name LIKE '_은%';

/*
75년생에 대한 회원 정보를 조회하려고 합니다.
 - 아이디, 이름, 주민번호앞, 주민번호뒤 조회
 - 주민등록번호를 이용해서 조건처리해주세요.
*/
SELECT Mem_id, Mem_name, Mem_regno1, Mem_regno2
FROM Member
WHERE Mem_regno1 LIKE '75%';

/*
회원의 성씨가 '김'씨인 회원들이 구매한 상품 중에
상품명에 '삼성'이 포함된 상품을 구매한
회원아이디, 회원이름을 조회해 주세요.

- 사용할 테이블 찾기: member, cart, prod
- 관계조건: Mem_id = Cart_member
         : Cart_prod = Prod_id
- 일반조건: Mem_name LIKE '김%'
         : Prod_name LIKE '%삼성%'
- 조회컬럼: Mem_id, Mem_name
*/
SELECT Mem_id, Mem_name
FROM Member
WHERE Mem_name LIKE '김%'
  AND Mem_id IN (SELECT Cart_member
                 FROM Cart
                 WHERE Cart_prod IN(SELECT Prod_id
                                    FROM Prod
                                    WHERE Prod_name LIKE '%삼성%'));


SELECT DISTINCT
    Cart_member AS id,
    (SELECT Mem_name
     FROM Member
     WHERE Mem_id = Cart_member) as name
FROM Cart
WHERE Cart_member IN (SELECT Mem_id
                      FROM Member
                      WHERE Mem_name LIKE '김%')
    AND Cart_prod IN (SELECT Prod_id
                      FROM Prod
                      WHERE Prod_name LIKE '%삼성%');
                      
                      
/*
회원아이디, 회원이름, 상품코드, 주문번호, 주문수량 조회하기
 - 상품 판매가격이 20만원 이상이고 100만원 이하인 상품
 - 거래처명에 '삼성'이 포함된 거래처
 - 상품분류명에 '컴퓨터'가 포함된 상품
*/
SELECT DISTINCT
    Cart_member,
    (SELECT Mem_name
     FROM Member
     WHERE Mem_id = Cart_member) AS Name,
    Cart_prod,
    Cart_No,
    Cart_qty
FROM Cart
WHERE Cart_Prod IN (SELECT Prod_id
                    FROM Prod
                    WHERE Prod_sale BETWEEN 200000 AND 1000000
                    AND Prod_lgu IN (SELECT Lprod_gu
                                     FROM Lprod
                                     WHERE Lprod_nm LIKE '%컴퓨터%')
                    AND Prod_buyer IN (SELECT Buyer_id
                                       FROM Buyer
                                       WHERE Buyer_name LIKE '%삼성%'));
                                       
                                
/*
<일반 함수 사용하기>
 - 데이터베이스 시스템마다 자체적으로 만들어 놓은 함수가 존재함
 - 국제 표준을 따르지 않기에 다른 데이터베이스 시스템에서는
   해당하는 함수들을 찾아서 사용해야함
 - 일반적으로 모든 데이터베이스 시스템에서 사용하는 함수는
   동일한 개념으로 대부분 존재하며, 함수이름만(철자)만 차이가 있음
*/

/* (문자열 합치기) */
-- 오라클에서 제공하는 테스트용 테이블: Dual
-- 문자열 합치는 함수: CONCAT(값1, 값2) or ||
SELECT ('test' || 'test1' || 'test2') as nm,
       CONCAT(CONCAT('test', 'test1'), 'test3') as nm2
From Dual;

-- 주민등록번호를 "000000-0000000" 이렇게 표현해보기
-- 회원이름 회원주민번호앞, 주민번호뒤, 주민번호합친것 조회
SELECT
    Mem_name,
    Mem_regno1,
    Mem_regno2,
    (Mem_regno1 || '-' || Mem_regno2) as regno,
    CONCAT(Mem_regno1, CONCAT('-', Mem_regno2))as regno2
FROM Member;

/*
(대소문자 변환하기
 - 영문자를 모두 대문자로 변환하기: UPPER()
 - 영문자를 모두 소문자로 변환하기: LOWER()
 - 영문자단언의 첫글자만 대문자로 나머지는 소문자로: INITCAP()
 - 주로 사용되는 함수: UPPER(), LOWER()
*/
SELECT 
    Mem_id, 
    UPPER(Mem_id) AS upId,
    LOWER(Mem_id) AS loId
FROM Member;

SELECT
    Cart_member,
    UPPER(Cart_member) AS upId,
    Cart_prod,
    LOWER(Cart_prod) AS loPord,
    InitCap('hello hi') AS icap
FROM Cart;

/*
(공간을 다른 문자로 채우기)
 - Lpad(원본값, 전체자리수, 채울값)
  : 전체자리수(메모리공간)을 기준으로 오른쪽부터 원본값을 채우고,
    남은 공간을 채출값으로 채웁니다.
 - Rpad(원본값, 전체자리수, 채울값)
  : 전체자리수(메모리공간)을 기준으로 왼쪽부터 원본값을 채우고,
    남은 공간을 채출값으로 채웁니다.
*/
SELECT 
    Mem_regno1,
    LPAD(Mem_regno1, 13, '*') AS lpad1,
    RPAD(Mem_regno1, 13, '*') AS rpad1
FROM Member;

-- 주민번호1을 이용해서 '000000-*******" 이렇게 표현되도록
-- 회원주민번호1, 변경된주민번호
SELECT
    Mem_regno1,
    RPAD(Mem_regno1 || '-', 14, '*') AS regno
FROM Member;

/*
(공백(TRIM) 제거하기)
 - LTRIM(원본값): 왼쪽 첫번째 공백 제거
 - RTRIM(원본값): 오른쪽 첫번째 공백 제거
 - TRIM(원본값) : 양쪽 첫번째 공백 제거
 ** 사용되는 경우: 회원아이디 조회할 경우 가끔 사용
               : 회원이 가입 시 공백을 모르고 넣고 가입한 경우
*/
SELECT
    LTRIM(' abcd efg hih ') AS lt,
    RTRIM(' abcd efg hih ') AS rt,
    TRIM(' abcd efg hih ') AS allt
FROM Dual;

/*
(문자열 추출 함수) -> 매우 중요
 - SUBSTR(원본값, 추출할 시작위치, 추출할 갯수)
*/
SELECT 
    'abc def ghi',
    SUBSTR('abc def ghi', 0, 3) AS subMSG1,
    SUBSTR('abc def ghi', 1, 3) AS subMSG2,
    SUBSTR('abc def ghi', 4, 3) AS subMSG3
FROM Member;

-- 주민번호1을 이용해서 '000000-0******" 이렇게 표현되도록
-- 회원주민번호1, 변경된주민번호
SELECT
    Mem_regno1,
    RPAD(SUBSTR(Mem_regno1 || '-' || Mem_regno2, 0, 8), 14, '*') AS regno,
    (Mem_regno1 || '-' || RPAD(SUBSTR(Mem_regno2, 0, 1), 7, '*')) AS regno2
FROM Member;

/*
CART 테이블에서
 - 주문번호 앞 8자리 조회
 - 상품코드 앞 4자리 조회
*/
SELECT
    Cart_no,
    SUBSTR(Cart_no, 0, 8) AS no,
    Cart_prod,
    SUBSTR(Cart_prod, 0, 4) AS prod
FROM Cart;

/*
김씨 성을 가진 회원님들이 구매한 상품이 속한 상품분류정보를 조회
 - 조회컬럼: 상품분류코드, 상품분류명 조회하기
 - 단, 상품정보 테이블은 사용하지 않습니다.
*/
SELECT Lprod_gu, Lprod_nm
FROM Lprod
WHERE Lprod_gu IN (SELECT SUBSTR(Cart_prod, 0, 4)
                   FROM Cart
                   WHERE Cart_member IN (SELECT Mem_id
                                         FROM Member
                                         WHERE Mem_name LIKE '김%'));
                                         

/*
각 조별로,
 - 가장 어렵게.. 누구도 풀 수 없도록..
 - 문제 2개 만들기... 정답도 만들기
*/
SELECT DISTINCT
    RPAD(TRIM(UPPER(buyer_name)), 10, '*') AS name,
    LPAD(LOWER(buyer_comtel), 15, '0') AS comtel,
    RPAD(SUBSTR(buyer_add1, 1, 5), 8, '-') As address
FROM Buyer
WHERE SUBSTR(Buyer_id, 1, 3) IN(SELECT SUBSTR(Prod_id, 1, 3)
                                FROM Prod
                                WHERE Prod_lgu IN(SELECT Lprod_gu
                                                  FROM Lprod))
ORDER BY (SUBSTR(address, 4, 1)) ASC;




/*
이름이 이로 시작하는 당구나 수영이 취미인 편리한 청바지, 여름 셔츠, 봄 자켓 제품을 구매한 사람의 
이름, 생일, 취미, 상품분류 코드, 거래처명, 상품 입고일자 를 조회하시오
*/
SELECT
    (SELECT Mem_name
     FROM Member
     WHERE Mem_id = Cart_member) AS name,
    (SELECT Mem_Memorial
     FROM Member
     WHERE Mem_id = Cart_member) AS memorial,
    (SELECT Mem_Like
     FROM Member
     WHERE Mem_id = Cart_member) AS hobby,
    (SELECT Lprod_gu
     FROM Lprod
     WHERE lprod_gu IN (SELECT Prod_lgu
                        FROM Prod
                        WHERE Prod_id = Cart_member) AS Lprod
--    거래처명
--    상품입고일자
FROM Cart;




/*
이름이 이로 시작하는 당구나 수영이 취미인 편리한 청바지, 여름 셔츠, 봄 자켓 제품을 구매한 사람의 
이름, 생일, 취미, 상품분류 코드, 거래처명 를 조회하시오
*/

Select (Select mem_name From member Where cart_member = mem_id) as name, 
            (Select lprod_gu From lprod Where lprod_gu = 
                (Select prod_lgu From prod Where prod_id = cart_prod)) as prod_lgu,
                    (Select mem_like From member Where cart_member = mem_id) as m_like,
                        (Select mem_bir From member Where cart_member = mem_id) as bir,
                            (Select buyer_name From buyer Where buyer_id = 
                                (Select prod_buyer From prod Where prod_id = cart_prod)) as buyer
From cart
Where cart_prod In(Select prod_id From prod Where 
prod_outline Like '%편리한%' and prod_price >= 66000 and 
(prod_name Like '%청바지%' or prod_name Like '%여름 셔츠%' or prod_name Like '%봄 자켓%') and 
cart_member In(Select mem_id From member Where mem_name Like '이%') 
and ((cart_member In(Select mem_id From member Where mem_like = '수영'))
or (cart_member In(Select mem_id From member Where mem_like = '당구'))));