package com.pknu.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 주문정보(장바구니) 테이블의 데이터 관리
 */
// getter, setter 모두 포함되어 있음
@Data
// 디폴트 생성자 생성
@NoArgsConstructor
// 모든 컬럼을 사용한 생성자 생성
@AllArgsConstructor
// CartService.java 내에서 DTO를 Entity로, Entity를 DTO로 변환할 때 사용
@Builder
public class CartDTO {
    // 주문번호 : PK(복합키1)
    private String cart_no;
    // 상품코드 : PK(복합키2)
    private String cart_prod;
    // 회원아이디    
    private String cart_member;
    // 주문수량
    private int    cart_qty;
}
