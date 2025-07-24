-- 아이디가 a001인 회원의 마일리지 값보다 큰(이상), 
-- 회원들에 대한 회원아이디, 회원이름, 회원마일리지를 조회해 주세요.

SELECT Mem_id, Mem_name, Mem_mileage
FROM Member
WHERE Mem_mileage >= (SELECT Mem_mileage
                      FROM Member
                      WHERE Mem_id = 'a001');


/*
2005년도에 주문내역이 있는 회원 중에 회원별로 
총구매금액이 3천만원 이상인 회원들에 대하여  
마일리지의 값을 1000으로 수정하여 주세요.
총구매금액 = 판매가 x 판매수량
*/ 
UPDATE Member
   SET Mem_mileage = 1000
 WHERE Mem_id IN (
       SELECT Mem_id
         FROM Cart
         LEFT OUTER JOIN Member ON Mem_id = Cart_member
         LEFT OUTER JOIN Prod ON Prod_id = Cart_prod
        WHERE SUBSTR(Cart_no, 1, 4) = '2005'
        GROUP BY Mem_id
       HAVING SUM(NVL(Prod_Sale * Cart_qty, 0)) >= 30000000
);
                       
SELECT Mem_id, Mem_name, Mem_mileage,
       SUM(NVL(Prod_Sale * Cart_qty, 0)) AS income
FROM Cart
LEFT OUTER JOIN Member ON Mem_id = Cart_member
                      AND SUBSTR(Cart_no, 1, 4) = '2005'
LEFT OUTER JOIN Prod ON Prod_id = Cart_prod
GROUP BY Mem_id, Mem_name, Mem_mileage
HAVING SUM(NVL(Prod_Sale * Cart_qty, 0)) >= '30000000';

/*
2005년도에 회원이 주문한 데이터에 대하여, 전체 거래처별  총매출금액의 총합을 조회하려고 합니다. 
조회컬럼 : 거래처코드, 거래처명, 총매출금액
정렬 : 총매출금액 기준 내림차순
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
ORDER BY income DESC;

/*
오늘이 2005년 4월 1일이라고 가정한다. 
오늘 기준으로 구매가 한건 발생할 경우, 
구매(주문)번호를 발급해야 합니다. 
신규 구매(주문)번호를 생성하는 Select 구문을 작하여 주세요.
조회 컬럼은 신규구매(주문)번호 입니다.
*/
SELECT DISTINCT Cart_no
FROM Cart
WHERE SUBSTR(Cart_no, 1, 8) = '20050401';



/*
(테이블1) 5행 5열의 데이터를 가지고 있으며, PK 컬럼은 ID
(테이블2) 5행 5열의 데이터를 가지고 있으며, FK 컬럼은 ID
*/

