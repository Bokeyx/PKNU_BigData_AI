package com.pknu.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * <DTO(Data Transfer Object)>
 *  - 데이터 전송 객체를 의미함
 *  - 요청 및 응답 데이터들을 담아서 전달하는 역할을 수행
 *  - 실제 모델(엔티티)을 사용하지 않고, 필요한 정보만 담을 수 있는 객체를 생성하여 처리..
 */

@Data // getter, setter, ToString() 모두 한번에 처리
@NoArgsConstructor
@AllArgsConstructor
public class CartMemberDTO {
    /**
     * 담을 컬럼(필드) 정의하기
     *  - 주문내역 및 회원정보를 담아서 사용할 DTO 컬럼들...
     *  - 보통 요청 데이터(입력/수정)를 담거나,
     *  - 응답 데이터(조회)를 담기 위해 사용됨
     */
    private String cartNo;
    private String cartProd;
    private int cartQty;
    private String memberId;
    private String memberName;
}
